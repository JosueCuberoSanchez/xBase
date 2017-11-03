package cr.ac.ucr.ecci.ci1312.xbase.student.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.stateChange.service.ExerciseStateChangeService;
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
 * @author Rodrigo A. Bartels
 */
@Controller
public class StudentDashboardController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseStateChangeService exerciseStateChangeService;


    @RequestMapping(value="/student/dashboard.html", method= RequestMethod.GET)
    public ModelAndView showDashboard(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student st = this.studentService.findById(user.getId());
        ModelAndView mav = new ModelAndView("dashboard");
        mav.addObject("student",st);
        return mav;
    }
}
