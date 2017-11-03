package cr.ac.ucr.ecci.ci1312.xbase.build.dataLoader;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.service.AnswerService;
import cr.ac.ucr.ecci.ci1312.xbase.core.author.service.AuthorService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.ExerciseProcedureService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.reference.service.ReferenceService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.AdministratorService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;

import cr.ac.ucr.ecci.ci1312.xbase.core.topic.service.TopicService;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;


import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.*;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Proyecto de Bases de Datos 1
 * xBase
 * Autores:
 * Alemán Ramírez Esteban
 * Borchgrevink Leiva Alexia
 * Cubero Sánchez Josué
 * Durán Gregory Ian
 * Garita Centeno Alonso
 * Hidalgo Campos Jose
 * Mellado Xatruch Carlos
 * Muñoz Miranda Roy
 *
 * Primer ciclo 2017
 */
public class DataLoader {

    private transient final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String[] locations = new String[]{
            "/xbase-hibernate.spring.xml",
            "/xbase-core.spring.xml",
            "/xbase-security.spring.xml"
    };

    protected final ClassPathXmlApplicationContext appCtx;

    private XMLLoader xmlLoader;

    public DataLoader() {
        this.appCtx = new ClassPathXmlApplicationContext(locations);
        this.xmlLoader = new XMLLoader();
    }

    public void load() {
        System.out.println("DATA LOAD START");
        try {
            List<Administrator> adminList = xmlLoader.parseAdministrators("xbase-core/src/main/resources/administratorData.xml");
            List<Student> studentList = xmlLoader.parseStudents("xbase-core/src/main/resources/studentData.xml");
            List<Exercise> exerciseList = xmlLoader.parseExercises("xbase-core/src/main/resources/exerciseData.xml");
            List<Author> authorList = xmlLoader.parseAuthors("xbase-core/src/main/resources/authorsData.xml");
            List<BibliographicReference> referenceList = xmlLoader.parseReferences("xbase-core/src/main/resources/referencesData.xml");
            List<Topic> topicList = this.xmlLoader.parseTopics("xbase-core/src/main/resources/topicsData.xml");
            List<Pair<BibliographicReference, List<Author>>> authorReferences = this.xmlLoader.parseAuthorsReferences("xbase-core/src/main/resources/authorsRelations.xml");

            /*Pertinent services to insert into database*/
            AdministratorService adminService = this.appCtx.getBean("administratorService", AdministratorService.class);
            StudentService studentService = this.appCtx.getBean("studentService", StudentService.class);
            ExerciseService exerciseService = this.appCtx.getBean("exerciseService", ExerciseService.class);
            TopicService topicService = this.appCtx.getBean("topicService", TopicService.class);
            ReferenceService referenceService = this.appCtx.getBean("referenceService", ReferenceService.class);
            AnswerService answerService = this.appCtx.getBean("answerService", AnswerService.class);
            ExerciseProcedureService exerciseProcedureService = this.appCtx.getBean("exerciseProcedureService", ExerciseProcedureService.class);
            AuthorService authorService = this.appCtx.getBean("authorService", AuthorService.class);


            for (Administrator anAdminList : adminList) {
                adminService.create(anAdminList);
            }

            for (Student aStudentList : studentList) {
                studentService.create(aStudentList);
            }


            for(Topic topic : topicList){
                Topic newTopic = new Topic();
                newTopic.setName(topic.getName());
                topicService.create(newTopic);
            }

            Topic children;
            Topic parent;
            for(Topic topic : topicList){
                if(topic.getParentTopic() != null){
                    parent = topicService.getTopicByName(topic.getParentTopic().getName());
                      if(parent != null){
                        children = topicService.getTopicByName(topic.getName());
                        children.setParentTopic(parent);
                        topicService.update(children);
                    }
                }
            }

            for(Author author : authorList){
                authorService.create(author);
            }

            for(BibliographicReference reference : referenceList){
                referenceService.create(reference);
            }

            for(Pair<BibliographicReference, List<Author>> pair : authorReferences){
                BibliographicReference ref;
                List<Author> authors;
                Author author;
                List<BibliographicReference> references;


                if(pair.getKey().getClass() == Book.class){
                    ref = this.searchReferenceByTitle(((Book) pair.getKey()).getTitle(), referenceList);
                }
                else if(pair.getKey().getClass() == Booklet.class){
                    ref = searchReferenceByTitle(((Booklet) pair.getKey()).getTitle(), referenceList);
                }else{
                    ref = searchReferenceByYear( pair.getKey().getPublicationYear(), referenceList);
                }

                authors = new LinkedList<>();
                for(Author auth : pair.getValue()){
                    references = new LinkedList<>();
                    author = this.searchAuthorByName(auth.getName(), auth.getLastName(), authorList);
                    references.add(ref);
                    author.setReferences(references);
                    authors.add(author);
                    authorService.update(author);
                }
                ref.setAuthors(authors);

                referenceService.update(ref);
            }
            int testingCounter = 0;

            for(Exercise dummyExercise : exerciseList){
                List<Topic> oldTopics = dummyExercise.getTopics();
                dummyExercise.setTopics(new LinkedList<>());
                List<BibliographicReference> exerciseReferences = dummyExercise.getReferences();
                dummyExercise.setReferences(new LinkedList<>());

                exerciseService.create(dummyExercise);

                for(Answer ans: dummyExercise.getAnswers()){  //for
                    ans.setExercise(dummyExercise);
                    if(ans.getClass() == PracticalAnswer.class){
                        PracticalAnswer pA = (PracticalAnswer) ans;
                        ExerciseProcedure p = pA.getExerciseProcedure();
                        answerService.create(pA);
                        if(p != null){
                            p.setAnswer(pA);
                            exerciseProcedureService.create(p);
                        }
                    }
                    else{
                        answerService.create(ans);
                    }
                }
                  List<Exercise> exerciseTopics = new LinkedList<>();
                List<Topic> topicsList = new LinkedList<>();
                for(Topic top : oldTopics){
                    Topic topicToExercise = topicService.getTopicByName(top.getName());
                    if(topicToExercise != null){
                        exerciseTopics.add(dummyExercise);
                        topicToExercise.setAssociatedExercises(exerciseTopics);
                        topicService.update(topicToExercise);
                        topicsList.add(topicToExercise);
                        System.out.println(dummyExercise.getSnippet() + " ---- tiene el tema: " + topicToExercise.getName() );
                    }
                }
                dummyExercise.setTopics(topicsList);
                exerciseService.update(dummyExercise);
                ++testingCounter;
                System.out.println("termina ciclo de topics: " + testingCounter);
                List<BibliographicReference> referencesToUpdate = new LinkedList<>();
                for(BibliographicReference bibRef : exerciseReferences){
                    BibliographicReference auxRef;

                    if(bibRef.getClass() == Book.class){
                        auxRef = this.searchReferenceByTitle(((Book)bibRef).getTitle(), referenceList);
                        referencesToUpdate.add(auxRef);
                    }
                    else if(bibRef.getClass() == Booklet.class){
                        auxRef = searchReferenceByTitle(((Booklet)bibRef).getTitle(), referenceList);
                        referencesToUpdate.add(auxRef);

                    }else{
                        auxRef = searchReferenceByYear( bibRef.getPublicationYear(), referenceList);
                        referencesToUpdate.add(auxRef);
                    }
                }
                dummyExercise.setReferences(referencesToUpdate);
                exerciseService.update(dummyExercise);
            }
            System.out.println("DATA LOAD FINISHED");
        }catch (Exception e){
                e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private BibliographicReference searchReferenceByTitle(String inputTitle, List<BibliographicReference> referenceList){
        for(BibliographicReference bibRef : referenceList){
            if(bibRef.getClass() == Book.class){
                if(((Book) bibRef).getTitle().equals(inputTitle)){
                    return bibRef;
                }
            }
            else if (bibRef.getClass() == Booklet.class){
                if(((Booklet) bibRef).getTitle().equals(inputTitle)){
                    return bibRef;
                }
            }
        }
        return null;
    }

    public BibliographicReference searchReferenceByYear(Integer inputYear, List<BibliographicReference> referenceList){
        for(BibliographicReference bibRef : referenceList){
            if( bibRef.getClass() == OwnElaboration.class){
                if(((bibRef).getPublicationYear() == inputYear)){
                    return bibRef;
                }
            }
        }
        return null;
    }

    public Author searchAuthorByName(String firstName, String lastName, List<Author> authorList){
        for(Author a : authorList){
            if( a.getName().equals(firstName) && a.getLastName().equals(lastName)){
                return a;
            }
        }
        return null;
    }



    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        loader.load();
        System.exit(0);
    }
}
