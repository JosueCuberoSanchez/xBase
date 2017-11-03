package cr.ac.ucr.ecci.ci1312.xbase.student.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Josue Cubero on 30/06/2017.
 */
@Controller
public class AboutController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/aboutLoginRegister.html")
    public ModelAndView showAboutLR(){
        return new ModelAndView("aboutLoginRegister");
    }

    @RequestMapping("/student/about.html")
    public ModelAndView showAbout(){
        return new ModelAndView("about");
    }
}
