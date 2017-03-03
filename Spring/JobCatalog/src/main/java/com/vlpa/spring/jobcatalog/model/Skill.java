package com.vlpa.spring.jobcatalog.model;

import javax.persistence.*;

@Entity
@Table(name = "SKILL")
public class Skill {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="NAME")
    private String name;

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

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
