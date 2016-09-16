package com.vlpa.spring.jobcatalog.dao;

import com.vlpa.spring.jobcatalog.model.Company;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

    private Logger logger = LoggerFactory.getLogger(CompanyDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCompany(Company company) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(company);
        logger.debug("Company added. Info: " + company);
    }

    @Override
    public void updateCompany(Company company) {
        logger.info("Company info before update: " + company);
        Session session = sessionFactory.getCurrentSession();
        session.update(company);
        logger.debug("Company updated. Info: " + company);
    }

    @Override
    public void removeCompany(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company)session.load(Company.class, id);
        if (company != null){
            session.delete(company);
        }
        logger.debug("Company removed. Info: " + company);
    }

    @Override
    public Company getCompanyById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Company company = (Company)session.load(Company.class, id);
        logger.debug("Company loaded. Info: " + company);
        return company;
    }

    @Override
    public Company getCompanyByName(String name){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Company where name = :name");
        query.setParameter("name", name);
        Iterator<Company> iterator = query.list().iterator();
        Company company = null;
        if (iterator.hasNext()) {
            company = iterator.next();
            logger.debug("Company loaded. Info: " + company);
        } else {
            logger.debug("Company with name '{}' not found", name);
        }
        return company;
    }

    @Override
    public List<Company> listCompanies() {
        Session session = sessionFactory.getCurrentSession();
        List<Company> companyList = session.createQuery("from Company").list();

        logger.debug("Companies:");
        for(Company company: companyList){
            logger.debug("- " + company);
        }

        return companyList;
    }
}
