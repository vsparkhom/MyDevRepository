package com.vlpa.spring.jobcatalog.service;

import com.vlpa.spring.jobcatalog.dao.SkillDAO;
import com.vlpa.spring.jobcatalog.model.Skill;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private SkillDAO skillDAO;

    public void setSkillDAO(SkillDAO skillDAO) {
        this.skillDAO = skillDAO;
    }

    @Override
    @Transactional
    public void addSkill(Skill skill) {
        skillDAO.addSkill(skill);
    }

    @Override
    @Transactional
    public void updateSkill(Skill skill) {
        skillDAO.updateSkill(skill);
    }

    @Override
    @Transactional
    public void removeSkill(int id) {
        skillDAO.removeSkill(id);
    }

    @Override
    @Transactional
    public Skill getSkillById(int id) {
        return skillDAO.getSkillById(id);
    }

    @Override
    @Transactional
    public List<Skill> listSkills() {
        return skillDAO.listSkills();
    }
}
