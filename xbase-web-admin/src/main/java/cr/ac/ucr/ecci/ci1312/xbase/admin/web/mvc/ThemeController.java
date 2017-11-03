package cr.ac.ucr.ecci.ci1312.xbase.admin.web.mvc;

import cr.ac.ucr.ecci.ci1312.xbase.core.topic.service.TopicService;
import cr.ac.ucr.ecci.ci1312.xbase.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
/**
 * Created by Josue Cubero on 25/06/2017.
 */
@Controller
public class ThemeController {

    @Autowired
    private TopicService topicService;

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping("/admin/theme.html")
    public ModelAndView showTheme(){
        return new ModelAndView("theme");
    }

    @RequestMapping(value="/admin/manageThemes.html",method=RequestMethod.GET)
    public ModelAndView showManageThemes(@RequestParam(value = "search", required = false) String theme) {
        ModelAndView model = new ModelAndView("manageThemes");
        List<Topic> subtopics;
        if (theme!=null) {
            model.addObject("parentTopic", theme);
            if (theme != null) {
                theme = theme.replace('_', ' ');
                subtopics = this.topicService.getRelatedTopics(theme);
                model.addObject("showParent", true);
                model.addObject("subtopics",subtopics);
            } else {
                model.addObject("showParent", false);
                subtopics = null;
            }
        }
        return model;
    }

    @RequestMapping(value="/admin/addTopic.html")
    public ModelAndView addTopic(@RequestParam(value="newTopic",required=false)String newTopic, @RequestParam(value="result", required = false) String parentTopic){
        ModelAndView model = new ModelAndView("manageThemes");
        if (newTopic != null && parentTopic != null) {
            Topic topicAdd = new Topic();
            topicAdd.setName(newTopic);
            Topic parent = topicService.getTopicByName(parentTopic);
            topicAdd.setParentTopic(parent);
            this.topicService.create(topicAdd);
            model.addObject("added", true);
        }
        else{
            model.addObject("added", false);
        }
        return model;
    }

    @RequestMapping(value="/admin/editTopic.html")
    public ModelAndView editTopic(@RequestParam(value="search",required=false)String parentTopic, @RequestParam(value="editTopic",required = false)String newName){
        ModelAndView model = new ModelAndView("manageThemes");
        if (parentTopic != null){
            Topic topicEdit = this.topicService.getTopicByName(parentTopic);
            topicEdit.setName(newName);
            if(topicEdit.getName().equals(newName)){
                model.addObject("edited",true);
            }
        }
        return model;
    }

}
