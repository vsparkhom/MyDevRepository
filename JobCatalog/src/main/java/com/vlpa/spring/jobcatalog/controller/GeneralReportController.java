package com.vlpa.spring.jobcatalog.controller;

import com.vlpa.spring.jobcatalog.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GeneralReportController {

    private Logger logger = LoggerFactory.getLogger(GeneralReportController.class);

    private CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(value = "/report/company/list*", method = RequestMethod.GET)
    public String reportCompanyList(Model model) {//TODO
        logger.debug("<reportCompanyList> Generating report");
        model.addAttribute("listCompanies", companyService.listCompanies());
        return "/report/company/list";
    }
}
