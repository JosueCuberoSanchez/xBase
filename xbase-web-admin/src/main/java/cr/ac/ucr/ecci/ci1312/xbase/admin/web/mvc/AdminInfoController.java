package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

/**
 * Created by Carlos on 20/7/2017.
 */

import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.AdministratorService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Administrator;
import cr.ac.ucr.ecci.ci1312.xbase.model.User;
import cr.ac.ucr.ecci.ci1312.xbase.support.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AdminInfoController {

    @Autowired
    AdministratorService administratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping("/admin/saveAdminInfo.html")
    public ModelAndView saveAdminInfo(@RequestParam(value = "fistName" , required = false) String fN, @RequestParam(value = "lastName" , required = false) String lN,
                                      @RequestParam(value = "secLastName", required = false) String sLN, @RequestParam(value = "userEmail", required = false) String email,
                                      @RequestParam(value = "bDay", required = false) String birthDay, @RequestParam(value = "state", required = false) String state,
                                      @RequestParam(value = "password", required = false) String password, @RequestParam(value = "repeatedPassword", required = false) String repeatedPassword,
                                      @RequestParam(value = "currentPassword", required = false) String currentPassword ) {

        ModelAndView model = new ModelAndView("redirect:/admin/admin.html");
        Administrator administrator = administratorService.findById( SecurityUtils.getCurrentLoggedAdmin().getId() );
        if (fN != null)
            administrator.setName(fN);
        if (!lN.equals(""))
            administrator.setLastName(lN);
        if (!sLN.equals(""))
            administrator.setSecondLastName(sLN);
        if (!email.equals(""))
            administrator.setUsername(email);

        User.Status status;
        if (!state.equals("")) {
            switch (state) {
                case "INACTIVE":
                    status = User.Status.INACTIVE;
                    break;
                case "SUSPENDED":
                    status = User.Status.SUSPENDED;
                    break;
                default: //ACTIVE
                    status = User.Status.ACTIVE;
                    break;
            }
            administrator.setStatus(status);
        }

        if (!birthDay.equals("")) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            try {
                administrator.setBirthDate(dateFormat.parse(birthDay));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(password != null && repeatedPassword != null && currentPassword != null) {
            if (!password.equals("") && !repeatedPassword.equals("") && !currentPassword.equals("")) {
                if (passwordEncoder.encode(currentPassword).equals(administrator.getPassword()) && password.equals(repeatedPassword)) {
                    try {
                        SecurityUtils.validatePassword(password);
                        administrator.setPassword(passwordEncoder.encode(password));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid Password, passwords must contain at least a number, can not start with a number, and must be at least 8 characters long.");
                    }
                } else {
                    System.out.println("Passwords do not match.");
                }
            } else {
                System.out.println("Missing password information.");
            }
        }

        administratorService.update(administrator);
        return model;
    }

    @RequestMapping("/admin/saveAdminChanges.html")
    public ModelAndView changeAdminEnable(@RequestParam("id") String adminId,
                                          @RequestParam("enabled")String enabled){
        Administrator admin = this.administratorService.findById(adminId);
        admin.setEnabled(Boolean.parseBoolean(enabled.split(",")[0]));
        this.administratorService.update(admin);
        return new ModelAndView("redirect:/admin/admin.html?id=" + adminId);
    }
}
