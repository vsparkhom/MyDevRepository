package com.vlpa.spring.jobcatalog.service;

import com.vlpa.spring.jobcatalog.model.Company;

import java.util.List;

public interface CompanyService {

    public void addCompany(Company company);
    public void updateCompany(Company company);
    public void removeCompany(int id);
    public Company getCompanyById(int id);
    public Company getCompanyByName(String name);
    public List<Company> listCompanies();

}
