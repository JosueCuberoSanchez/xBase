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
 * @author Rodrigo A. Bartels
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/student/student.html")
    public ModelAndView showStudent(){
        ModelAndView model = new ModelAndView("student");
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = this.studentService.findById(user.getId());
        model.addObject("currentStudent", student);
        return model;
    }

    @RequestMapping("/student/editStudentInfo.html")
    public ModelAndView showEditStudentInfo(){
        ModelAndView model = new ModelAndView("editStudentInfo");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student st = this.studentService.findById(user.getId());
        model.addObject("currentStudent", st);
        return model;
    }
}
