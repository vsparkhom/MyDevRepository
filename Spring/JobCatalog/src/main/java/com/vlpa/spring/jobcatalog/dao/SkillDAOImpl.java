package com.vlpa.spring.jobcatalog.dao;

import com.vlpa.spring.jobcatalog.model.Company;
import com.vlpa.spring.jobcatalog.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDAOImpl implements SkillDAO {

    private Logger logger = LoggerFactory.getLogger(SkillDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addSkill(Skill skill) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(skill);
        logger.debug("Skill added. Info: " + skill);
    }

    @Override
    public void updateSkill(Skill skill) {
        logger.info("Skill info before update: " + skill);
        Session session = sessionFactory.getCurrentSession();
        session.update(skill);
        logger.debug("Skill updated. Info: " + skill);
    }

    @Override
    public void removeSkill(int id) {
        Session session = sessionFactory.getCurrentSession();
        Skill skill = (Skill)session.load(Skill.class, id);
        if (skill != null){
            session.delete(skill);
        }
        logger.debug("Skill removed. Info: " + skill);
    }

    @Override
    public Skill getSkillById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Skill skill = (Skill)session.load(Skill.class, id);
        logger.debug("Skill loaded. Info: " + skill);
        return skill;
    }

    @Override
    public List<Skill> listSkills() {
        Session session = sessionFactory.getCurrentSession();
        List<Skill> skillList = session.createQuery("from Skill").list();

        logger.debug("Skills:");
        for(Skill skill: skillList){
            logger.debug("- " + skill);
        }

        return skillList;
    }
}
