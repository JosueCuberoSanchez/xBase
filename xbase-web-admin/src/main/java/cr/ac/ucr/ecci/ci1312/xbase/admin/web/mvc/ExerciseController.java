package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.answer.report.service.AnswerReportService;
import cr.ac.ucr.ecci.ci1312.xbase.core.answer.service.AnswerService;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.topic.service.TopicService;
import cr.ac.ucr.ecci.ci1312.xbase.enums.Difficulty;
import cr.ac.ucr.ecci.ci1312.xbase.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Josue Cubero on 25/06/2017.
 */
@Controller
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private AnswerReportService answerReportService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/admin/ejercicioxBase.html", method = RequestMethod.GET)
    public ModelAndView generatePdf(HttpServletRequest request,
                                    HttpServletResponse response, @RequestParam("id") String exId) throws Exception {
        Exercise exercise = this.exerciseService.findById(exId);
        List<Answer> answers = this.answerService.getAnswersOfExercise(exercise);
        ModelAndView modelAndView = new ModelAndView("pdfView");
        modelAndView.addObject("exercise",exercise);
        modelAndView.addObject("answers",answers);
        return modelAndView;
    }

    @RequestMapping("/admin/exercisesByRating.html")
    public ModelAndView showExercisesByRating(@RequestParam(value = "rating", required = false) Integer rating) {
        ModelAndView model = new ModelAndView("exercisesByRating");
        if(rating == null)
            model.addObject("exercises", this.exerciseService.getExercisesOrderedByRating());
        else
            model.addObject("exercises", this.exerciseService.getExercisesByRating(rating));

        return model;
    }

    @RequestMapping("/admin/exercisesByTheme.html")
    public ModelAndView showExercisesByTheme(@RequestParam(value = "theme", required = false) String theme) {
        ModelAndView model = new ModelAndView("exercisesByTheme");
        List<Exercise> exercises;
        if(theme != null) {
            theme = theme.replace('_', ' ');
            exercises = this.exerciseService.getExercisesByTopic(theme);
        }
        else{
            exercises = null;
        }
        model.addObject("exercises", exercises);
        return model;
    }

    @RequestMapping("/admin/report.html")
    public ModelAndView showReport(@RequestParam("reportId") String reportId){
        ModelAndView model = new ModelAndView("report");
        AnswerReport report =  answerReportService.findById(reportId);
        Answer answer = report.getReportedAnswer();
        Exercise exercise = answer.getExercise();
        model.addObject("report",report);
        model.addObject("answer",answer);
        model.addObject("exercise",exercise);
        return model;
    }

    @RequestMapping("/admin/reportList.html")
    public ModelAndView showReportList() {
        ModelAndView model = new ModelAndView("reportList");
        List<AnswerReport> reports = this.answerReportService.getReports();
        Map<String,AnswerReport> pair = new HashMap<>();
        for(AnswerReport ar: reports){
            String snippet = this.exerciseService.getExerciseSnippetFromReport(this.answerReportService.findById(ar.getId()).getReportedAnswer());
            pair.put(snippet,ar);
        }
        model.addObject("reports",pair);
        return model;
    }

    @RequestMapping("/admin/adminExercise.html")
    public ModelAndView showAdminExercise(@RequestParam("exercise") String id) {
        ModelAndView model = new ModelAndView("adminExercise");
        Exercise exercise = this.exerciseService.findById(id);
        List<Answer> answers = this.answerService.getAnswersOfExercise(exercise);
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
        model.addObject("exercise", exercise);
        model.addObject("answers", answers);
        model.addObject("bookReferences", bookReferences);
        model.addObject("bookletReferences", bookletReferences);
        model.addObject("ownReferences", ownReferences);
        return model;
    }

    @RequestMapping("/admin/search.html")
    public ModelAndView showExercisesByKeyword(@RequestParam("search") String keyword){
        ModelAndView model = new ModelAndView("exercisesByKeyword");
        List<Exercise> foundExercises = this.exerciseService.getExercisesByKeyword(keyword);
        model.addObject("exercises", foundExercises);
        return model;
    }

    @RequestMapping("/admin/answers.html")
    public ModelAndView showManageAnswers(@RequestParam("exercise") String id){
        ModelAndView model = new ModelAndView("answers");
        Exercise exercise = this.exerciseService.findById(id);
        List<Answer> answers = exercise.getAnswers();
        model.addObject("exerciseId", id);
        model.addObject("results", answers);
        return model;
    }

    @RequestMapping("/admin/createExercise.html")
    public ModelAndView showExerciseCreation(){
        ModelAndView model = new ModelAndView("createExercise");
        model.addObject("topics", this.topicService.getAll());
        return model;
    }

    @RequestMapping("/admin/editExercise.html")
    public ModelAndView showExerciseEdit(@RequestParam("exerciseId") String exerciseId){
        ModelAndView model = new ModelAndView("editExercise");
        model.addObject("exercise", this.exerciseService.findById(exerciseId));
        return model;
    }

    @RequestMapping("/admin/answerList.html")
    public ModelAndView showAnswerList(@RequestParam("exerciseId") String exerciseId){
        Exercise exercise = this.exerciseService.findById(exerciseId);
        ModelAndView model = new ModelAndView();
        model.addObject("answers", exercise.getAnswers());
        return model;
    }

    @RequestMapping("/admin/saveExercise.html")
    public ModelAndView answerRedirect(@RequestParam(value = "exerciseId", required = false) String exerciseId,
                                       @RequestParam("problem") String problem,
                                       @RequestParam("difficulty") String difficulty,
                                       @RequestParam(value = "visible", required = false, defaultValue = "false") String visible,
                                       @RequestParam(value = "published", required = false, defaultValue = "false") String published){
        Exercise exercise;
        if(exerciseId != null){
            exercise = this.exerciseService.findById(exerciseId);
        }else{
            exercise = new Exercise();
        }
        exercise.setProblem(problem);
        switch (difficulty){
            case "EASY":
                exercise.setDifficulty(Difficulty.EASY);
                break;
            case "MEDIUM":
                exercise.setDifficulty(Difficulty.MEDIUM);
                break;
            case "ADVANCED":
                exercise.setDifficulty(Difficulty.ADVANCED);
                break;
            case "COMPLEX":
                exercise.setDifficulty(Difficulty.COMPLEX);
                break;
        }
        exercise.setVisible(Boolean.parseBoolean(visible.split(",")[0]));
        exercise.setPublished(Boolean.parseBoolean(published.split(",")[0]));
        if(exerciseId != null){
            this.exerciseService.update(exercise);
        }else{
            this.exerciseService.create(exercise);
        }
        return new ModelAndView("redirect:/admin/answers.html?exercise=" + exercise.getId());
    }
}



