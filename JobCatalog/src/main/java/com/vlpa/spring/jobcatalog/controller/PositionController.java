package com.vlpa.spring.jobcatalog.controller;

import com.vlpa.spring.jobcatalog.model.Company;
import com.vlpa.spring.jobcatalog.model.Position;
import com.vlpa.spring.jobcatalog.service.CompanyService;
import com.vlpa.spring.jobcatalog.service.PositionService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PositionController {

    private Logger logger = LoggerFactory.getLogger(PositionController.class);

    private PositionService positionService;

    private CompanyService companyService;

    @Autowired(required = true)
    @Qualifier(value = "positionService")
    public void setPositionService(PositionService positionService) {
        this.positionService = positionService;
    }

    @Autowired(required = true)
    @Qualifier(value = "companyService")
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/position/list*", method = RequestMethod.GET)
    public String listPositions(Model model){
        logger.debug("<listPositions> List positions");
        model.addAttribute("listPositions", positionService.listPositions());
        return "/position/list";
    }

    @RequestMapping(value = "/position/add*", method = RequestMethod.GET)
    public String setupAddPositionForm(Model model){
        model.addAttribute("position", new Position());
        model.addAttribute("listCompanies", getSelectData(companyService.listCompanies()));
        return "/position/add";
    }

    protected Map<String, String> getSelectData(List<Company> listCompanies){
        Map<String, String> companySelectData = new HashMap<>();
        for(Company company: listCompanies) {
            companySelectData.put(company.getId()+"", company.getName());
        }
        return companySelectData;
    }

    @RequestMapping(value = "/position/add", method = RequestMethod.POST)
    public String addPosition(@ModelAttribute("position") Position position){
        logger.debug("<listPositions> Add position: {}", position);
        positionService.addPosition(position);
        return "redirect:/position/list";
    }

    @RequestMapping("/position/remove")
    public String removePosition(@RequestParam("id") int id){
        positionService.removePosition(id);
        return "redirect:/position/list";
    }

    @RequestMapping("/position/get*")
    public String getPositionById(@RequestParam("id") int id, Model model) {
        model.addAttribute("position", positionService.getPositionById(id));
        return "/position/id";
    }

    @RequestMapping(value = "/position/edit*", method = RequestMethod.GET)
    public String editPosition(@RequestParam("id") int id, Model model) {
        model.addAttribute("editMode", true);
        model.addAttribute("position", positionService.getPositionById(id));
        model.addAttribute("listCompanies", getSelectData(companyService.listCompanies()));
        return "/position/id";
    }

    @RequestMapping(value = "/position/edit", method = RequestMethod.POST)
    public String editPosition(@ModelAttribute("position") Position position) {
        positionService.updatePosition(position);
        return "redirect:/position/list";
    }
}
