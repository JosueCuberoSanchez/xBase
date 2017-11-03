package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rodrigo A. Bartels
 */
@Controller
public class AboutController {

    @RequestMapping("/admin/about.html")
    public ModelAndView showAbout() {
        return new ModelAndView("about");
    }

    @RequestMapping("/aboutLoginRegister.html")
    public ModelAndView showAboutLoginRegister() {
        return new ModelAndView("aboutLoginRegister");
    }


}
