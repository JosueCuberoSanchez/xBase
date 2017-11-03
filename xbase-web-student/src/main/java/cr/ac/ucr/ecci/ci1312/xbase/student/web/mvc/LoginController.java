package cr.ac.ucr.ecci.ci1312.xbase.student.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rodrigo A. Bartels
 */
@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/login.html")
    public ModelAndView showLoginPage(@RequestParam(value = "name",required = false) String name,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "sLastName", required = false) String sLastName,
                                      @RequestParam(value = "username", required = false) String username,
                                      @RequestParam(value = "pass", required = false) String password,
                                      @RequestParam(value = "studentId", required = false) String studentId,
                                      @RequestParam(value = "bDay", required = false) Integer bDay,
                                      @RequestParam(value = "bMonth", required = false) Integer bMonth,
                                      @RequestParam(value = "bYear", required = false) Integer bYear){

        if(name != null && lastName != null && sLastName != null && username != null && password != null && studentId != null
                && bDay != null && bMonth != null && bYear != null){
            Student newStudent = new Student();
            newStudent.setName(name);
            newStudent.setLastName(lastName);
            newStudent.setSecondLastName(sLastName);
            newStudent.setUsername(username);
            newStudent.setPassword(password);
            newStudent.setStudentId(studentId);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date bDate = format.parse(bDay + "-" + bMonth + "-" + bYear);
                newStudent.setBirthDate(bDate);
            }catch(Exception e){
                e.printStackTrace();
            }
            this.studentService.create(newStudent);
        }
        return new ModelAndView("login");
    }

    @RequestMapping("/SignupStudents.html")
    public ModelAndView showSignUpPage(){
        ModelAndView model = new ModelAndView("SignupStudents");
        model.addObject("newStudent", new Student());
        return model;
    }

    @RequestMapping(value="/forgotPassword.html", method= RequestMethod.GET)
    public ModelAndView showForgotPasswordPage(){
        return new ModelAndView("forgotPassword");
    }

    @RequestMapping(value="/forgotPassword.html", method=RequestMethod.POST)
    public ModelAndView processForgotPasswordPage(@RequestParam(value = "username") String username){
        ModelAndView model = new ModelAndView("forgotPassword");
        if (username != null){
            studentService.sendPasswordReset(username);
            model.addObject("sent",true);
        }
        return model;
    }
}