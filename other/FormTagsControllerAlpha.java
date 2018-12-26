package com.zpoif.soprojekt.app;

import com.zpoif.soprojekt.form.FormTags;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FormTagsControllerAlpha {

    @RequestMapping(value = "/formTags", method = RequestMethod.GET)
    public ModelAndView showForm() {
        return new ModelAndView("formTagsHome", "formTags", new FormTags());
    }

    @RequestMapping(value = "/addFormTags", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("formTags") FormTags formTags,
                         BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        model.addAttribute("fromDate", formTags.getFromDate());
        model.addAttribute("toDate", formTags.getToDate());
        return "formTagsView";
    }
}