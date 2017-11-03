package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import com.amazonaws.services.dynamodbv2.xspec.NULL;
import cr.ac.ucr.ecci.ci1312.xbase.core.exercise.service.ExerciseService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.AdministratorService;
import cr.ac.ucr.ecci.ci1312.xbase.core.security.student.service.StudentService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.model.Student;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Josue Cubero on 25/06/2017.
 */
@Controller
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping("/admin/admin.html")
    public ModelAndView showAdmin(@RequestParam(value = "id", required = false) String id) {
        ModelAndView model = new ModelAndView("admin");
        if(id != null) {
            model.addObject("admin", this.administratorService.findById(id));
            model.addObject("canEdit", false);
        } else {
            User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Administrator admin = this.administratorService.findById(user.getId());
            model.addObject("admin", admin);
            model.addObject("canEdit", true);
        }

        return model;
    }

    @RequestMapping("/admin/student.html")
    public ModelAndView showStudentInfo(@RequestParam("id") String studentId
                                        ){
        ModelAndView model = new ModelAndView("student");
        model.addObject("student", this.studentService.findById(studentId));
        return model;
    }

    @RequestMapping("/admin/editStudentInfo.html")
    public ModelAndView showEditStudentInfo(@RequestParam("id") String studentId) {
        ModelAndView mav = new ModelAndView("editStudentInfo");
        mav.addObject("student",studentService.findById(studentId));
        return mav;
    }

    @RequestMapping("/admin/saveStudentChanges.html")
    public ModelAndView showSaveStudentChange(@RequestParam("id") String studentId,
                                              @RequestParam(value="enabled",required = false)String enabled,
                                              @RequestParam (value="status",required = false)String status){
        Student st = this.studentService.findById(studentId);
        st.setEnabled(Boolean.parseBoolean(enabled.split(",")[0]));
        switch (status) {
            case "ACTIVE":
                st.setStatus(User.Status.ACTIVE);
                break;
            case "INACTIVE":
                st.setStatus(User.Status.INACTIVE);
                break;
            case "SUSPENDED":
                st.setStatus(User.Status.SUSPENDED);
                break;
        }
        this.studentService.update(st);
        return new ModelAndView("redirect:/admin/student.html?id=" + studentId);
    }

    @RequestMapping("/admin/StatisticsStudentsInfo.html")
    public ModelAndView showStatisticsStudentInfo(@RequestParam("id") String studentId){
        ModelAndView model = new ModelAndView("StatisticsStudentsInfo");
        model.addObject("student", this.studentService.findById(studentId));
        return model;
    }

    @RequestMapping("/admin/editAdminInfo.html")
    public ModelAndView showEditAdminInfo() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Administrator admin = this.administratorService.findById(user.getId());
        ModelAndView model = new ModelAndView("editAdminInfo");
        model.addObject("admin", admin);
        return model;
    }

    @RequestMapping("/admin/studentList.html")
    public ModelAndView showStudent(@RequestParam(value = "search", required = false) String search) {
        ModelAndView model = new ModelAndView("studentList");
        if(search != null){
            model.addObject("studentList", this.studentService.getStudentsByName(search));
        }else{
            model.addObject("studentList", this.studentService.getAll());
        }

        return model;
    }

    @RequestMapping("/admin/adminList.html")
    public ModelAndView showAccount(@RequestParam(value = "search", required = false) String search) {
        ModelAndView model = new ModelAndView("adminList");
        if(search != null){
            model.addObject("adminList", this.administratorService.getAdminByName(search));
        }else {
            model.addObject("adminList", this.administratorService.getAll());
        }
        return model;
    }

    @RequestMapping("/admin/SignupAdmin.html")
    public ModelAndView showSignupAdmin(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "lastName", required = false) String lastName,
                                        @RequestParam(value = "sLastName", required = false) String sLastName,
                                        @RequestParam(value = "username", required = false) String username,
                                        @RequestParam(value = "pass", required = false) String password,
                                        @RequestParam(value = "id", required = false) String id,
                                        @RequestParam(value = "bDay", required = false) Integer bDay,
                                        @RequestParam(value = "bMonth", required = false) Integer bMonth,
                                        @RequestParam(value = "bYear", required = false) Integer bYear){

        ModelAndView model = new ModelAndView("SignupAdmin");
        if(name != null && lastName != null && sLastName != null && username != null && password != null && id != null && bDay != null
                && bMonth != null && bYear != null){
            model.addObject("added", true);
            Administrator newAdmin = new Administrator();
            newAdmin.setName(name);
            newAdmin.setLastName(lastName);
            newAdmin.setSecondLastName(sLastName);
            newAdmin.setUsername(username);
            newAdmin.setPassword(password);
            newAdmin.setAdminID(id);
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date bDate = format.parse(bDay + "-" + bMonth + "-" + bYear);
                newAdmin.setBirthDate(bDate);
            }catch(Exception e){
                e.printStackTrace();
            }
            this.administratorService.create(newAdmin);
        }
        model.addObject("added", false);
        return model;
    }
}
