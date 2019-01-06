package com.zpoif.soprojekt.app;

import com.zpoif.soprojekt.form.FormTags;
import com.zpoif.soprojekt.visualisation.VisualiseStats;
import com.zpoif.soprojekt.visualisation.VisualiseTags;
import com.zpoif.soprojekt.visualisation.VisualiseTimeline;
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

    @Value("${visualisation.wykres1}")
    private String wykres1;

    @Value("${visualisation.wykres2}")
    private String wykres2;

    @Value("${home.tabela}")
    private String tabela;

    @RequestMapping("/home")
    public String welcome(Map<String, Object> model) {

        tabela = VisualiseStats.visualise();
        model.put("tabela", this.tabela);
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

        System.out.println(formTags.getFromDate());
        System.out.println(formTags.getToDate());
        wykres1 = VisualiseTags.visualise(formTags.getFromDate(),formTags.getToDate(), "wykresTagi");
        wykres2 = VisualiseTimeline.visualise(formTags.getFromDate(),formTags.getToDate(), "wykresPytania");

        model.addAttribute("wykres1", this.wykres1);
        model.addAttribute("wykres2", this.wykres2);

        return "/visualisation";
    }
}