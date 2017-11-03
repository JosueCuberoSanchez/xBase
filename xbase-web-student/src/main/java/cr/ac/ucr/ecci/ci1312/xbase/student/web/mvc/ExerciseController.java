package cr.ac.ucr.ecci.ci1312.xbase.student.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.service.AnswerService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.procedure.ExerciseProcedureService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.service.ExerciseStateChangeService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Josue Cubero on 30/06/2017.
 */
@Controller
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseStateChangeService exerciseStateChangeService;

    @Autowired
    private ExerciseProcedureService procedureService;

    @Autowired
    private ExerciseProcedureService exerciseProcedureService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/student/ejercicioxBase.html", method = RequestMethod.GET)
    public ModelAndView generatePdf(HttpServletRequest request,
                             HttpServletResponse response,@RequestParam("id") String exId) throws Exception {
        Exercise exercise = this.exerciseService.findById(exId);
        List<Answer> answers = this.answerService.getAnswersOfExercise(exercise);
        ModelAndView modelAndView = new ModelAndView("pdfView");
        modelAndView.addObject("exercise",exercise);
        modelAndView.addObject("answers",answers);
        return modelAndView;
    }

    @RequestMapping("/student/exercise.html")
    public ModelAndView showExercise(@RequestParam("exercise") String exId) {
        Exercise exercise = this.exerciseService.findById(exId);
        List<Book> bookReferences = new LinkedList<>();
        List<Booklet> bookletReferences = new LinkedList<>();
        List<OwnElaboration> ownReferences = new LinkedList<>();
        List<BibliographicReference> references = exercise.getReferences();
        for(int i = 0; i < references.size(); i++){
            if(references.get(i) instanceof Book)
                bookReferences.add((Book) references.get(i));

            else if (references.get(i) instanceof Booklet)
                bookletReferences.add((Booklet) references.get(i));

            else if(references.get(i) instanceof OwnElaboration)
                ownReferences.add((OwnElaboration) references.get(i));
        }
        ModelAndView model = new ModelAndView("exercise");
        model.addObject("exercise", exercise);
        model.addObject("bookReferences", bookReferences);
        model.addObject("bookletReferences", bookletReferences);
        model.addObject("ownReferences", ownReferences);
        return model;
    }

    @RequestMapping("/student/search.html")
    public ModelAndView getExercisesByKeyword(@RequestParam("search") String keyword){
        ModelAndView model = new ModelAndView("exercisesByKeyword");
        List<Exercise> foundExercises = this.exerciseService.getVisibleExercisesByKeyword(keyword);
        model.addObject("exercises", foundExercises);
        return model;
    }

    @RequestMapping("/student/answers.html")
    public ModelAndView showManageAnswers(@RequestParam("exercise") String id){
        ModelAndView model = new ModelAndView("answers");
        Exercise exercise = this.exerciseService.findById(id);
        List<Answer> answers = this.answerService.getAnswersOfExercise(exercise);
        model.addObject("results", answers);
        model.addObject("exercise", exercise);
        return model;

    }

    @RequestMapping("/student/answer.html")
    public ModelAndView showAnswer(@RequestParam("answer") String id){
        ModelAndView model = new ModelAndView("answer");
        Answer answer = this.answerService.findById(id);

        model.addObject("isPractical", answer.isPractical());
        if (answer.isPractical()){
            ExerciseProcedure procedure = ((PracticalAnswer) answer).getExerciseProcedure();
            String proc;
            if (procedure instanceof ImageExerciseProcedure){
                proc = ((ImageExerciseProcedure)procedure).getImage();
            }
            else {
                proc = ((TextExerciseProcedure)procedure).getText();
            }
            model.addObject("procedure", proc);
        }
        model.addObject("type", answer.getType().toString());
        model.addObject("answer", answer);
        return model;
    }
}
