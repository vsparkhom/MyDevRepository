package com.vlpa.spring.jobcatalog.converter;

import com.vlpa.spring.jobcatalog.model.Company;
import com.vlpa.spring.jobcatalog.service.CompanyService;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

public class StringToCompanyConverter implements Converter<String, Company> {

    private CompanyService companyService;

    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public Company convert(String companyIdString) {
        int companyId = -1;
        try{
            companyId = Integer.parseInt(companyIdString);
        } catch (NumberFormatException e) {
            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(Company.class), companyIdString, null);
        }
        return companyService.getCompanyById(companyId);
    }
}
