package com.vlpa.spring.jobcatalog.controller;

import com.vlpa.spring.jobcatalog.exception.DuplicateCompanyNameException;
import com.vlpa.spring.jobcatalog.model.Company;
import com.vlpa.spring.jobcatalog.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {

    private Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private CompanyService companyService;

    @Autowired(required = true)
    @Qualifier(value = "companyService")
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/company/list*", method = RequestMethod.GET)
    public String listCompanies(Model model){
        logger.debug("<listCompanies> List companies");

        Collection<Company> listCompanies = companyService.listCompanies();
        model.addAttribute("listCompanies", listCompanies);

        Map<Integer, Integer> companyPositionsCountMap =  new HashMap<>();
        for(Company currentCompany : listCompanies) {
            companyPositionsCountMap.put(currentCompany.getId(), currentCompany.getPositions().size());
        }
        model.addAttribute("companyPositionsCountMap", companyPositionsCountMap);

        return "/company/list";
    }

    @RequestMapping(value = "/company/add*", method = RequestMethod.GET)
    public String addCompanyFormBacking(Model model){
        model.addAttribute("company", new Company());
        return "/company/add";
    }

    @RequestMapping(value = "/company/add", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute("company") Company company){
        Company presentCompany = companyService.getCompanyByName(company.getName());
        if (presentCompany != null) {
            throw new DuplicateCompanyNameException(company.getName());
        }
        companyService.addCompany(company);
        return "redirect:/company/list";
    }

    @RequestMapping("/company/remove")
    public String removeCompany(@RequestParam("id") int id){
        companyService.removeCompany(id);
        return "redirect:/company/list";
    }

    @RequestMapping("/company/get*")
    public String getCompanyById(@RequestParam("id") int id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        model.addAttribute("listPositions", company.getPositions());
        return "/company/id";
    }

    @RequestMapping(value = "/company/edit*", method = RequestMethod.GET)
    public String editCompany(@RequestParam("id") int id, Model model) {
        model.addAttribute("editMode", true);
        model.addAttribute("company", companyService.getCompanyById(id));
        return "/company/id";
    }

    @RequestMapping(value = "/company/edit", method = RequestMethod.POST)
    public String editCompany(@ModelAttribute("company") Company company) {
        companyService.updateCompany(company);
        return "redirect:/company/list";
    }

    @RequestMapping(value = "/company/find", method = RequestMethod.GET)
    public String findCompanyFormBaking() {
        return "/company/find";
    }

    @RequestMapping(value = "/company/find", method = RequestMethod.POST)
    public String findCompany(@RequestParam("wantedCompanyName") String wantedCompanyName, Model model) {
        logger.debug("<findCompany> wantedCompanyName: " + wantedCompanyName);
        List<Company> wantedCompaniesList = new ArrayList<>();
        for (Company c : companyService.listCompanies()) {
            if (c.getName().toUpperCase().contains(wantedCompanyName.toUpperCase())) {
                logger.debug("<findCompany> - found: " + c);
                wantedCompaniesList.add(c);
            }
        }

        if (wantedCompaniesList.size() == 1) {
            model.addAttribute("id", wantedCompaniesList.get(0).getId());
            return "redirect:/company/get";
        }

        model.addAttribute("listCompanies", wantedCompaniesList);
        return "/company/list";
    }

}
