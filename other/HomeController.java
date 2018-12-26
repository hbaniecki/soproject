package com.zpoif.soprojekt.app;

import java.util.Map;

import com.zpoif.soprojekt.visualisation.VisualiseTags;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    @Value("${home.message}")
    private String message;

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {

        message = new VisualiseTags().visualise("01-12-2018","25-12-2018", "wykresTagi");

        model.put("message", this.message);
        return "/home";
    }

}