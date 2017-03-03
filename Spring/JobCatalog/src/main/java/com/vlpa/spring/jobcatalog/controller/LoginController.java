package com.vlpa.spring.jobcatalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping(value = "/login*", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        return "/login";
    }
}
