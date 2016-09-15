package com.vlpa.spring.jobcatalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test*it")
public class WelcomeController {

    private Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Value("#{messageSource.getMessage('test.email',null,'en')}")
    private String testEmail;

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model){
        logger.debug("<setupForm> Started");
        logger.debug("<setupForm> testEmail: " + testEmail);
        model.addAttribute("testEmail", testEmail);
        return "test";
    }

}
