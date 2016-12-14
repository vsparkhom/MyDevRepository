package com.vlpa.spring.jobcatalog.model;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "POSITION")
public class Position {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Skill.class)
    @JoinTable(name = "PS_CATALOG", joinColumns = {@JoinColumn(name = "POSITION_ID")}, inverseJoinColumns = {@JoinColumn(name = "SKILL_ID")})
    private Set<Skill> skills = new HashSet<>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", company=" + company +
                ", skills=" + (skills == null ? "null" : Arrays.asList(skills.toArray())) +
                '}';
    }
}
