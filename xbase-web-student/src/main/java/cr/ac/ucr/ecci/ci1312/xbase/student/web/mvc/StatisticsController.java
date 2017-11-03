package cr.ac.ucr.ecci.ci1312.xbase.student.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Josue Cubero on 29/06/2017.
 */
@Controller
public class StatisticsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "/student/StatisticsStudents.html",method= RequestMethod.GET)
    public ModelAndView showStatisticsStudent(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student st = this.studentService.findById(user.getId());
        ModelAndView mav = new ModelAndView("StatisticsStudents");
        mav.addObject("student",st);
        mav.addObject("exerciseNumber", this.exerciseService.getAll().size());
        return mav;
    }
}
