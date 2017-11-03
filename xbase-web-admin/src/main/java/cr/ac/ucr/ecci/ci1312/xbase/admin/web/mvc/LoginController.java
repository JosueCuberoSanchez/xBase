package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.security.admin.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rodrigo A. Bartels
 */
@Controller
public class LoginController {

    @Autowired
    private AdministratorService administratorService;
    
    @RequestMapping("/login.html")
    public ModelAndView showLoginPage(){return new ModelAndView("login");
    }


    @RequestMapping(value="/forgotPassword.html", method=RequestMethod.GET)
    public ModelAndView showForgotPasswordPage(){
        return new ModelAndView("forgotPassword");
    }

    @RequestMapping(value="/forgotPassword.html", method=RequestMethod.POST)
    public ModelAndView processForgotPasswordPage(@RequestParam(value = "username") String username){
        ModelAndView model = new ModelAndView("forgotPassword");
        if (username != null){
            this.administratorService.sendPasswordReset(username);
            model.addObject("sent",true);
        }
        return model;
    }


}
