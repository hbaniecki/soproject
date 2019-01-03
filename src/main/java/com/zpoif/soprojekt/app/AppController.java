package com.zpoif.soprojekt.app;

import com.zpoif.soprojekt.form.FormTags;
import com.zpoif.soprojekt.visualisation.VisualiseStats;
import com.zpoif.soprojekt.visualisation.VisualiseTags;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AppController {

    @Value("${visualisation.message}")
    private String message;

    @Value("${home.message}")
    private String message2;

    @RequestMapping("/home")
    public String welcome(Map<String, Object> model) {

        message2 = VisualiseStats.visualise();
        model.put("message2", this.message2);
        return "/home";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("form", "formTags", new FormTags());
    }

    @RequestMapping(value = "/visualisation", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("formTags") FormTags formTags,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "formError";
        }

        message = VisualiseTags.visualise(formTags.getFromDate(),formTags.getToDate(), "wykresTagi");

        model.addAttribute("message", this.message);

        return "/visualisation";
    }
}