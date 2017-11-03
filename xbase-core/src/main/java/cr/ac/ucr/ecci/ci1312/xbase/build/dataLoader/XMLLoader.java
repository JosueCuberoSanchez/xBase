package cr.ac.ucr.ecci.ci1312.xbase.build.dataLoader;

import cr.ac.ucr.ecci.ci1312.xbase.enums.AnswerType;
import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import javafx.util.Pair;
import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Universidad de Costa Rica
 * Facultad de Ingenierías
 * Escuela de Ciencias de la Computación e Informática
 * Proyecto de Bases de Datos 1
 * xBase
 * Autores:
 * Aguilar Corrales Vladimir
 * Garita Centeno Alonso
 *
 * Primer ciclo 2017
 */
public class XMLLoader {

    private File file;
    private Builder parser;

    public XMLLoader() {
        parser = new Builder();
    }

    /**
     * @param inputFilePath xml file path
     * @return List including every Administrator object present in xml input file
     */
    public List<Administrator> parseAdministrators(String inputFilePath){
        file = new File (inputFilePath);
        try{
            Document document = parser.build(file);
            List<Administrator> adminList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.ADMIN_ADMIN_LABEL);

            for (int i = 0; i < children.size(); i++) {
                Administrator admin = extractAdmin(children.get(i));
                adminList.add(admin);
            }
            return adminList;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param inputElement XML tag element containing a complete exercise
     * @return returns XML input administrator in java object form
     */
    public Administrator extractAdmin(Element inputElement){
        Administrator admin = new Administrator();

        admin.setAdminID(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_ADMINID_LABEL, false));
        admin.setUsername(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_USERNAME_LABEL,false));
        admin.setPassword(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_PASSWORD_LABEL,false));
        admin.setName(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_NAME_LABEL, false));
        admin.setLastName(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_FIRST_LAST_NAME_LABEL, false));
        admin.setSecondLastName(XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_SECOND_LAST_NAME_LABEL, false));
        admin.setEnabled(XOMUtils.extractBooleanValue(inputElement, StringConstants.ADMIN_IS_ENABLED_LABEL, false));
        admin.setBirthDate(XOMUtils.parseDateFromString((XOMUtils.extractStringValue(inputElement, StringConstants.ADMIN_BIRTHDATE_LABEL, false))));

        return admin;
    }

    /**
     * @param inputFilePath XML file path
     * @return list containing every student present in XML file
     */
    public List<Student> parseStudents(String inputFilePath){
        try {
            Document document = parser.build(inputFilePath);
            List<Student> studentList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.STUDENT_STUDENT_LABEL);

            for (int i = 0; i < children.size(); i++) {
                Student student = extractStudent(children.get(i));
                studentList.add(student);
            }
            return studentList;
        }catch (IOException | ParsingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param inputElement XOM element containing all of a students information
     * @return Student java object that represents its XML couterpart
     */
    private Student extractStudent(Element inputElement) {
        Student student = new Student();

        student.setStudentId(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_STUDENTID_LABEL, true));
        student.setUsername(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_USERNAME_LABEL,true));
        student.setPassword(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_PASSWORD_LABEL, true));
        student.setName(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_NAME_LABEL, true));
        student.setLastName(XOMUtils.extractStringValue(inputElement,StringConstants.STUDENT_FIRST_LAST_NAME_LABEL, true));
        student.setSecondLastName(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_SECOND_LAST_NAME_LABEL, false));
        student.setBirthDate(XOMUtils.parseDateFromString(XOMUtils.extractStringValue(inputElement, StringConstants.STUDENT_BIRTHDATE_LABEL, false)));
        student.setEnabled(XOMUtils.extractBooleanValue(inputElement, StringConstants.STUDENT_IS_ENABLED_LABEL, false));

        return student;
    }

    /**
     * @param inputFilePath input XML file path containing all exercises to be inserted to database
     * @return List containing all exercises contained in "inputFilePath"
     */
    public List<Topic> parseTopics(String inputFilePath){
        file = new File(inputFilePath);
        try {
            Document document = parser.build(file);
            List<Topic> topicList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.TOPIC_TOPIC_LABEL);
            Topic topic =  null;
            for (int i = 0; i < children.size(); i++) {
                topic = extractTopic(children.get(i));
                topicList.add(topic);
            }
            return topicList;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param inputFilePath input XML file path containing all exercises to be inserted to database
     * @return List containing all exercises contained in "inputFilePath"
     */
    public List<Exercise> parseExercises(String inputFilePath){
        file = new File(inputFilePath);
        try {
            Document document = parser.build(file);
            List<Exercise> exerciseList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.EXERCISE_EXERCISE_LABEL);

            for (int i = 0; i < children.size(); i++) {
                Exercise exercise = extractExercise(children.get(i));
                exerciseList.add(exercise);
            }
            return exerciseList;
        }catch (IOException | ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param inputFilePath input XML file path containing all authors to be inserted to database
     * @return List containing all authors contained in "inputFilePath"
     */
    public List<Author> parseAuthors(String inputFilePath){
        file = new File(inputFilePath);
        try {
            Document document = parser.build(file);
            List<Author> authorList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.AUTHOR_AUTHOR_LABEL);
            Author author =  null;
            for (int i = 0; i < children.size(); i++) {
                author = extractAuthor(children.get(i));
                authorList.add(author);
            }
            return authorList;
        }catch (IOException | ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param inputFilePath input XML file path containing all exercises to be inserted to database
     * @return List containing all exercises contained in "inputFilePath"
     */
    public List<BibliographicReference> parseReferences(String inputFilePath){
        file = new File(inputFilePath);
        try {
            Document document = this.parser.build(file);
            List<BibliographicReference> referenceList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.BOOK_BOOK_LABEL);
            BibliographicReference bibliographicReference =  null;
            for (int i = 0; i < children.size(); i++) {

                bibliographicReference = this.extractBook(children.get(i));
                referenceList.add(bibliographicReference);
            }
            children = root.getChildElements(StringConstants.BOOKLET_BOOKLET_LABEL);
            for (int i = 0; i < children.size(); i++) {

                bibliographicReference = this.extractBooklet(children.get(i));
                referenceList.add(bibliographicReference);
            }
            children = root.getChildElements(StringConstants.OWN_ELABORATION_OWN_ELABORATION_LABEL);
            for (int i = 0; i < children.size(); i++) {

                bibliographicReference = this.extractOwnElaboration(children.get(i));
                referenceList.add(bibliographicReference);
            }
            return referenceList;
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }catch (ParsingException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * @param inputFilePath input XML file path containing all exercises to be inserted to database
     * @return List containing all exercises contained in "inputFilePath"
     */
    public List<Pair<BibliographicReference, List<Author>>> parseAuthorsReferences(String inputFilePath){
        file = new File(inputFilePath);
        try {
            Document document = this.parser.build(file);
            List<Pair<BibliographicReference, List<Author>>> authorsReferencesList = new LinkedList<>();
            Element root = document.getRootElement();
            Elements children = root.getChildElements(StringConstants.BOOK_BOOK_LABEL);
            Element authors;
            Elements authorsChildren;
            BibliographicReference bibliographicReference =  null;
            List<Author> authorList;
            for (int i = 0; i < children.size(); i++) {
                bibliographicReference = this.extractBook(children.get(i));
                authorList = new LinkedList<>();
                authors = children.get(i).getFirstChildElement(StringConstants.BIBLIOGRAPHIC_REFERENCE_AUTHORS_LABEL);
                authorsChildren = authors.getChildElements(StringConstants.AUTHOR_AUTHOR_LABEL);
                for (int j = 0; j < authorsChildren.size(); j++) {
                    authorList.add(this.extractAuthor(authorsChildren.get(j)));
                }
                authorsReferencesList.add(new Pair<>(bibliographicReference, authorList));
            }


            children = root.getChildElements(StringConstants.BOOKLET_BOOKLET_LABEL);
            for (int i = 0; i < children.size(); i++) {
                bibliographicReference = this.extractBooklet(children.get(i));
                authorList = new LinkedList<>();
                authors = children.get(i).getFirstChildElement(StringConstants.BIBLIOGRAPHIC_REFERENCE_AUTHORS_LABEL);
                authorsChildren = authors.getChildElements(StringConstants.AUTHOR_AUTHOR_LABEL);
                for(int j = 0; j < authorsChildren.size(); j++){
                    authorList.add(this.extractAuthor(authorsChildren.get(j)));
                }
                authorsReferencesList.add(new Pair<>(bibliographicReference,authorList));
            }

            children = root.getChildElements(StringConstants.OWN_ELABORATION_OWN_ELABORATION_LABEL);
            for (int i = 0; i < children.size(); i++) {
                bibliographicReference = this.extractOwnElaboration(children.get(i));
                authorList = new LinkedList<>();
                authors = children.get(i).getFirstChildElement(StringConstants.BIBLIOGRAPHIC_REFERENCE_AUTHORS_LABEL);
                authorsChildren = authors.getChildElements(StringConstants.AUTHOR_AUTHOR_LABEL);
                for(int j = 0; j < authorsChildren.size(); j++){
                    authorList.add(this.extractAuthor(authorsChildren.get(j)));
                }
                authorsReferencesList.add(new Pair<>(bibliographicReference,authorList));
            }
            return authorsReferencesList;
        }catch (IOException | ParsingException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }



    /**
     * Extract a single exercise from provided XOM element
     * Traverses element to extract its references, topics and answers
     * Basically executes a pre-order run through the element extracting its inner XOM elements
     *
     * @param inputElement XOM element representing a single exercise from XML file
     * @return Exercise java object corresponding to XML data
     */
    public Exercise extractExercise(Element inputElement){
        Exercise exercise = new Exercise();
        exercise.setProblem(XOMUtils.extractStringValue(inputElement, StringConstants.EXERCISE_PROBLEM_LABEL, true));
        exercise.setVisible(XOMUtils.extractBooleanValue(inputElement, StringConstants.EXERCISE_IS_VISIBLE_LABEL, false));
        exercise.setPublished(XOMUtils.extractBooleanValue(inputElement, StringConstants.EXERCISE_IS_PUBLISHED_LABEL, false));
        exercise.setDeleted(XOMUtils.extractBooleanValue(inputElement, StringConstants.EXERCISE_IS_DELETED_LABEL, false));
        exercise.setDifficulty(Difficulty.valueOf(XOMUtils.extractStringValue(inputElement, StringConstants.EXERCISE_DIFFICULTY_LABEL, false)));

        this.extractExerciseTopics(inputElement,exercise);
        this.extractExerciseReferences(inputElement, exercise);
        this.extractExerciseAnswers(inputElement, exercise);

        return exercise;
    }

    /**
     * Called by extractExercise() to set a given exercise its answers list
     *
     * @param inputElement  XOM element containing all of inputExercises answers
     * @param inputExercise parent's java reference to maintain object integrity
     */
    private void extractExerciseAnswers(Element inputElement, Exercise inputExercise) {
        Element answerElement = inputElement.getFirstChildElement(StringConstants.EXERCISE_ANSWERS_LABEL);
        Elements answersGroup = answerElement.getChildElements();
        List<Answer> answerSet = new LinkedList<>();

        for (int i = 0; i < answersGroup.size(); i++) {
            Answer answer = extractExerciseAnswer(answersGroup.get(i), inputExercise);
            answerSet.add(answer);
        }
        inputExercise.setAnswers(answerSet);
    }

    /**
     * Extracts single answer from a given XOM element
     *
     * @param inputElement XOM element containing a single answer to be extracted
     * @param inputExercise java Exercise reference to be set at this answers parent
     * @return Answer java object corresponding to its XML equivalent
     */
    private Answer extractExerciseAnswer(Element inputElement, Exercise inputExercise) {
        /*First check if exercise is practical*/
        boolean practical = XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_PRACTICAL_LABEL,false);
        /*If practical then create according type*/
        if(practical){
            PracticalAnswer pAnswer = new PracticalAnswer();

            pAnswer.setCorrect(XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_CORRECT_LABEL, false));
            pAnswer.setDeleted(XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_DELETED_LABEL, false));
            pAnswer.setVisible(XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_VISIBLE_LABEL, false));
            pAnswer.setNumericalAnswer(XOMUtils.extractDoubleValue(inputElement, StringConstants.ANSWER_NUMBER_ANSWER_LABEL, false));
            pAnswer.setUnit(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_UNIT_LABEL, false));
            pAnswer.setContent(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_TEXT_ANSWER_LABEL, false));
            pAnswer.setImagePath(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_IMAGE_ANSWER_LABEL, false));
            pAnswer.setSourceCode(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_CODE_ANSWER_LABEL, false));
            pAnswer.setProgrammingLanguage(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_PROG_LANGUAGE_LABEL, false));
            String str = XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_ANSWER_TYPE_LABEL, false);
            AnswerType type = AnswerType.valueOf(str);
            pAnswer.setType(type);
            pAnswer.setExercise(inputExercise);

            /*If practical, then it may have a procedure, so we need to check its type*/
            Element element;
            if ((element = inputElement.getFirstChildElement(StringConstants.PROCEDURE_PROCEDURE_LABEL)) != null) { /*Check if it exists*/
                /*If it does get its type*/
                Boolean hasImage = XOMUtils.extractStringValue(element, StringConstants.PROCEDURE_PROCEDURE_TYPE_LABEL, false).equals("IMAGE");
                if (hasImage) {
                    ImageExerciseProcedure proc = new ImageExerciseProcedure();
                    proc.setImage(XOMUtils.extractStringValue(element, StringConstants.PROCEDURE_IMAGE_LABEL, false));
                    pAnswer.setExerciseProcedure(proc);
                } else {
                    TextExerciseProcedure proc = new TextExerciseProcedure();
                    proc.setText(XOMUtils.extractStringValue(element, StringConstants.PROCEDURE_TEXT_LABEL, false));
                    pAnswer.setExerciseProcedure(proc);
                }
            }
            return pAnswer;
        }else{
            /*If not create a simple answer*/
            Answer answer = new Answer();

            answer.setCorrect(XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_CORRECT_LABEL, false));
            answer.setDeleted(XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_DELETED_LABEL, false));
            answer.setVisible(XOMUtils.extractBooleanValue(inputElement, StringConstants.ANSWER_IS_VISIBLE_LABEL, false));
            answer.setNumericalAnswer(XOMUtils.extractDoubleValue(inputElement, StringConstants.ANSWER_NUMBER_ANSWER_LABEL, false));
            answer.setUnit(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_UNIT_LABEL, false));
            answer.setContent(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_TEXT_ANSWER_LABEL, false));
            answer.setImagePath(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_IMAGE_ANSWER_LABEL, false));
            answer.setSourceCode(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_CODE_ANSWER_LABEL, false));
            answer.setProgrammingLanguage(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_PROG_LANGUAGE_LABEL, false));
            answer.setType(AnswerType.valueOf(XOMUtils.extractStringValue(inputElement, StringConstants.ANSWER_ANSWER_TYPE_LABEL, false)));
            answer.setExercise(inputExercise);
            return answer;
        }
    }

    /**
     * @param inputElement XOM element containing all of an exercises topics
     * @param inputExercise Java Exercise object to be set as this topics parent
     */
    private void extractExerciseTopics(Element inputElement, Exercise inputExercise) {
        Element topicElement = inputElement.getFirstChildElement(StringConstants.EXERCISE_TOPICS_LABEL);
        Elements topicsGroup = topicElement.getChildElements();
        List<Topic> topicSet = new LinkedList<>();

        for (int i = 0; i < topicsGroup.size(); i++) {
            Topic topic = extractTopic(topicsGroup.get(i));
            topicSet.add(topic);
        }
        inputExercise.setTopics(topicSet);
    }

    /**
     *
     * @param inputElement XOM element containing a single topic
     * @return
     */
    private Topic extractTopic(Element inputElement) {

        Topic topic = new Topic();
        topic.setName(XOMUtils.extractStringValue(inputElement, StringConstants.TOPIC_NAME_LABEL, false));
        String fatherName = (XOMUtils.extractStringValue(inputElement, StringConstants.TOPIC_PARENT_TOPIC_LABEL, false));
        if(fatherName != null){
            Topic father = new Topic();
            father.setName(fatherName);
            topic.setParentTopic(father);
        }
        return topic;
    }

    /**
     *
     * @param inputElement XOM element containing all of a given exercise references
     * @param inputExercise java Exercise element used to maintain object integrity
     */
    private void extractExerciseReferences(Element inputElement, Exercise inputExercise) {
        Element referenceElement = inputElement.getFirstChildElement(StringConstants.EXERCISE_REFERENCES_LABEL);
        Elements referencesGroup = referenceElement.getChildElements();
        List<BibliographicReference> referenceSet = new LinkedList<>();
        for (int i = 0; i < referencesGroup.size(); i++) {
            BibliographicReference reference = this.extractExerciseReference(referencesGroup.get(i), inputExercise);
            referenceSet.add(reference);
        }
        inputExercise.setReferences(referenceSet);
    }

    /**
     *
     * @param inputElement XOM element containing a single references data
     * @param inputExercise java Exercise object to be set as this references parent
     * @return bibliographic reference instance (book, booklet, ownElaboration)
     */
    private BibliographicReference extractExerciseReference(Element inputElement, Exercise inputExercise) {
        /*This is a special case because we need to return a specific BibliographicReference implementation depending on the elements type*/
        switch (inputElement.getLocalName()){
            case "book":
                return this.extractBook(inputElement, inputExercise);

            case "booklet":
                return extractBooklet(inputElement, inputExercise);

            case "ownElaboration":
                return extractOwnElaboration(inputElement, inputExercise);

        }
        return null;
    }


    /**
     * @param inputElement XOM element containing a ownElaboration reference
     * @return java OwnElaboration object containing its corresponding XML data
     */
    private OwnElaboration extractOwnElaboration(Element inputElement) {
        OwnElaboration elaboration = new OwnElaboration();
        elaboration.setExercises(new ArrayList<>());

        elaboration.setPublicationYear(XOMUtils.extractIntegerValue(inputElement, StringConstants.OWN_ELABORATION_PUB_YEAR_LABEL, false));
        return elaboration;
    }

    /**
     * @param inputElement XOM element containing a Booklet reference
     * @return java Booklet object containing its corresponding XML data
     */
    private Booklet extractBooklet(Element inputElement) {
        Booklet booklet = new Booklet();
        booklet.setExercises(new ArrayList<>());
        booklet.setTitle(XOMUtils.extractStringValue(inputElement, StringConstants.BOOKLET_TITLE_LABEL, false));
        booklet.setPublicationYear(XOMUtils.extractIntegerValue(inputElement, StringConstants.BOOK_PUBLICATION_YEAR_LABEL, false));
        return booklet;
    }


    /**
     * @param inputElement XOM element containing a Book reference
     * @return java Book object containing its corresponding XML data
     */
    private Book extractBook(Element inputElement) {
        Book book = new Book();
        book.setExercises(new ArrayList<>());
        book.setIsbn(XOMUtils.extractStringValue(inputElement, StringConstants.BOOK_ISBN_LABEL, false));
        book.setEditorial(XOMUtils.extractStringValue(inputElement, StringConstants.BOOK_EDITORIAL_LABEL, false));
        book.setTitle(XOMUtils.extractStringValue(inputElement, StringConstants.BOOK_TITLE_LABEL, false));
        book.setPublicationYear(XOMUtils.extractIntegerValue(inputElement, StringConstants.BOOK_PUBLICATION_YEAR_LABEL, false));

        return book;
    }


    /**
     * @param inputElement XOM element containing a ownElaboration reference
     * @param inputExercise to be set at this references parent
     * @return java OwnElaboration object containing its corresponding XML data
     */
    private OwnElaboration extractOwnElaboration(Element inputElement, Exercise inputExercise) {
        OwnElaboration elaboration = new OwnElaboration();
        elaboration.setExercises(new ArrayList<>());
        elaboration.setPublicationYear(XOMUtils.extractIntegerValue(inputElement, StringConstants.OWN_ELABORATION_PUB_YEAR_LABEL, false));

        Element element;
        if((element = inputElement.getFirstChildElement(StringConstants.BIBLIOGRAPHIC_REFERENCE_AUTHORS_LABEL)) != null) {
            extractAuthors(element, elaboration);
        }
        elaboration.getExercises().add(inputExercise);
        return elaboration;
    }

    /**
     * @param inputElement XOM element containing a Booklet reference
     * @param inputExercise to be set at this references parent
     * @return java Booklet object containing its corresponding XML data
     */
    private Booklet extractBooklet(Element inputElement, Exercise inputExercise) {
        Booklet booklet = new Booklet();
        booklet.setExercises(new ArrayList<>());
        booklet.setTitle(XOMUtils.extractStringValue(inputElement, StringConstants.BOOKLET_TITLE_LABEL, false));
        Element element;
        if((element = inputElement.getFirstChildElement(StringConstants.BIBLIOGRAPHIC_REFERENCE_AUTHORS_LABEL)) != null) {
            extractAuthors(element, booklet);
        }
        booklet.getExercises().add(inputExercise);
        return booklet;

    }


    /**
     * @param inputElement XOM element containing a Book reference
     * @param inputExercise to be set at this references parent
     * @return java Book object containing its corresponding XML data
     */
    private Book extractBook(Element inputElement, Exercise inputExercise) {
        Book book = new Book();
        book.setExercises(new ArrayList<>());
        book.setIsbn(XOMUtils.extractStringValue(inputElement, StringConstants.BOOK_ISBN_LABEL, false));
        book.setEditorial(XOMUtils.extractStringValue(inputElement, StringConstants.BOOK_EDITORIAL_LABEL, false));
        book.setTitle(XOMUtils.extractStringValue(inputElement, StringConstants.BOOK_TITLE_LABEL, false));
        book.setPublicationYear(XOMUtils.extractIntegerValue(inputElement, StringConstants.BOOK_PUBLICATION_YEAR_LABEL, false));

        book.getExercises().add(inputExercise);
        return book;
    }



    /**
     * If a reference has an author, extract it here
     * @param element XOM element containing an authors information
     * @param reference java BibliographicReference object to be set as this authors parent
     */
    private void extractAuthors(Element element, BibliographicReference reference) {
        Elements authorsGroup = element.getChildElements(StringConstants.BIBLIOGRAPHIC_REFERENCE_AUTHOR_LABEL);
        List<Author> authorList = new LinkedList<>();

        for (int i = 0; i < authorsGroup.size(); i++) {
            Author author = extractAuthor(authorsGroup.get(i), reference);
            authorList.add(author);
        }
        reference.setAuthors(authorList);
    }

    private Author extractAuthor(Element element) {
        Author author = new Author();
        author.setReferences(new LinkedList<>());
        author.setName(XOMUtils.extractStringValue(element, StringConstants.AUTHOR_NAME_LABEL, false));
        author.setLastName(XOMUtils.extractStringValue(element, StringConstants.AUTHOR_LAST_NAME_LABEL, false));
        author.setSecondLastName(XOMUtils.extractStringValue(element, StringConstants.AUTHOR_SECOND_LAST_NAME_LABEL, false));
        return author;
    }

    private Author extractAuthor(Element element, BibliographicReference reference) {
        Author author = new Author();
        author.setReferences(new LinkedList<>());
        author.setName(XOMUtils.extractStringValue(element, StringConstants.AUTHOR_NAME_LABEL, false));
        author.setLastName(XOMUtils.extractStringValue(element, StringConstants.AUTHOR_LAST_NAME_LABEL, false));
        author.setSecondLastName(XOMUtils.extractStringValue(element, StringConstants.AUTHOR_SECOND_LAST_NAME_LABEL, false));

        author.getReferences().add(reference);

        return author;
    }

    // Currently not being used
    public void setFile(String input) {
        this.file = new File(input);
    }

}

