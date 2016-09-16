package com.vlpa.spring.jobcatalog.service;

import com.vlpa.spring.jobcatalog.dao.CompanyDAO;
import com.vlpa.spring.jobcatalog.model.Company;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyDAO companyDAO;

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    @Override
    @Transactional
    public void addCompany(Company company) {
        companyDAO.addCompany(company);
    }

    @Override
    @Transactional
    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    @Override
    @Transactional
    public void removeCompany(int id) {
        companyDAO.removeCompany(id);
    }

    @Override
    @Transactional
    public Company getCompanyById(int id) {
        return companyDAO.getCompanyById(id);
    }

    @Override
    @Transactional
    public Company getCompanyByName(String name){
        return companyDAO.getCompanyByName(name);
    }

    @Override
    @Transactional
    public List<Company> listCompanies() {
        return companyDAO.listCompanies();
    }
}
