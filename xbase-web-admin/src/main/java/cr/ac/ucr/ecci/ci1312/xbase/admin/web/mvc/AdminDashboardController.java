package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;


import cr.ac.ucr.ecci.ci1312.xbase.core.topic.service.TopicService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by Ian on 6/25/2017.
 */
@Controller
public class AdminDashboardController {

    @Autowired
    private TopicService topicService;

    @RequestMapping("/admin/dashboard.html")
    public ModelAndView showDashboard() {
        ModelAndView model = new ModelAndView("dashboard");
        List<Topic> parentTopics = this.topicService.getParentTopics();
        model.addObject("parentTopics",parentTopics);
        return model;
    }
}
