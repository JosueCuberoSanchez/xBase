package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Rodrigo A. Bartels
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public ModelAndView showIndex(){
        return new ModelAndView("index");
    }

}
