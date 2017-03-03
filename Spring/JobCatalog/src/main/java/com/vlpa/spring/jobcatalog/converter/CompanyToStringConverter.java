package com.vlpa.spring.jobcatalog.converter;

import com.vlpa.spring.jobcatalog.model.Company;
import com.vlpa.spring.jobcatalog.service.CompanyService;
import org.springframework.core.convert.converter.Converter;

public class CompanyToStringConverter  implements Converter<Company, String> {

    private CompanyService companyService;

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public String convert(Company company) {
        return "" + company.getId();
    }
}
