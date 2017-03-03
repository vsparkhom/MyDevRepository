package com.vlpa.spring.jobcatalog.dao;

import com.vlpa.spring.jobcatalog.model.Skill;

import java.util.List;

public interface SkillDAO {

    public void addSkill(Skill skill);

    public void updateSkill(Skill skill);

    public void removeSkill(int id);

    public Skill getSkillById(int id);

    public List<Skill> listSkills();
}
