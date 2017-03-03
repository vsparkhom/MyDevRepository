package com.vlpa.spring.jobcatalog.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class DuplicateCompanyNameException extends RuntimeException {

    //@Value("#{messageSource.getMessage('exception.duplicate.company.name',null, LocaleContextHolder.getLocale())}")
    private String companyName;

    public DuplicateCompanyNameException(String companyName) {
        super();
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
