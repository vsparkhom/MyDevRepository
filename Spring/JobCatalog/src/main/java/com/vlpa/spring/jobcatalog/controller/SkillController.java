package com.vlpa.spring.jobcatalog.controller;

import com.vlpa.spring.jobcatalog.model.Position;
import com.vlpa.spring.jobcatalog.model.Skill;
import com.vlpa.spring.jobcatalog.model.Skill;
import com.vlpa.spring.jobcatalog.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class SkillController {

    private Logger logger = LoggerFactory.getLogger(SkillController.class);

    private SkillService skillService;

    @Autowired
    @Qualifier(value = "skillService")
    public void setSkillService(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(value = "/skill/list*", method = RequestMethod.GET)
    public String listSkills(Model model){
        logger.debug("<listSkills> List skills");
        model.addAttribute("listSkills", skillService.listSkills());
        return "/skill/list";
    }

    @RequestMapping(value = "/skill/add*", method = RequestMethod.GET)
    public String setupAddSkillForm(Model model){
        model.addAttribute("skill", new Skill());
        return "/skill/add";
    }

    @RequestMapping(value = "/skill/add", method = RequestMethod.POST)
    public String addSkill(@ModelAttribute("skill") Skill skill){
        logger.debug("<listSkills> Add skill: {}", skill);
        skillService.addSkill(skill);
        return "redirect:/skill/list";
    }

    @RequestMapping("/skill/remove")
    public String removeSkill(@RequestParam("id") int id){
        skillService.removeSkill(id);
        return "redirect:/skill/list";
    }

    @RequestMapping(value = "/skill/edit*", method = RequestMethod.GET)
    public String editSkill(@RequestParam("id") int id, Model model) {
        model.addAttribute("editMode", true);
        Skill selectedSkill = skillService.getSkillById(id);
        model.addAttribute("skill", selectedSkill);
        return "/skill/id";
    }

    @RequestMapping(value = "/skill/edit", method = RequestMethod.POST)
    public String editSkill(@ModelAttribute("skill") Skill skill) {
        logger.debug("<editSkill> Update skill: {}", skill);
        skillService.updateSkill(skill);
        return "redirect:/skill/list";
    }
}
