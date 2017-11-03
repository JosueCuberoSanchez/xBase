package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.service.AnswerService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Answer;
import cr.ac.ucr.ecci.ci1312.xbase.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping("/admin/editAnswer.html")
    public ModelAndView showEditAnswer(@RequestParam("answer") String answerId) {
        ModelAndView model = new ModelAndView("editAnswer");
        Answer currentAnswer = this.answerService.findById(answerId);
        Exercise currentExercise = currentAnswer.getExercise();
        model.addObject("answer",currentAnswer);
        model.addObject("exercise",currentExercise);
        return model;
    }

    @RequestMapping("/admin/createAnswer.html")
    public ModelAndView showCreateAnswer(@RequestParam("exerciseId") String exerciseId){
        ModelAndView model = new ModelAndView("createAnswer");
        model.addObject("exercise", this.exerciseService.findById(exerciseId));
        return model;
    }
}
