package com.vlpa.spring.jobcatalog.dao;

import com.vlpa.spring.jobcatalog.model.Company;

import java.util.List;

public interface CompanyDAO {

    public void addCompany(Company company);
    public void updateCompany(Company company);
    public void removeCompany(int id);
    public Company getCompanyById(int id);
    public List<Company> listCompanies();
}
