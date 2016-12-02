package com.vlpa.spring.jobcatalog.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="COMPANY")
public class Company {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="EMPLOYEE_COUNT")
    private int employeeCount;

    @Column(name="ADDRESS")
    private String address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company", targetEntity = Position.class, cascade = CascadeType.ALL)
    private Set<Position> positions = new HashSet<>();

//    public Company(String name, String description, Integer employeeCount, String address) {
//        this.name = name;
//        this.description = description;
//        this.employeeCount = employeeCount;
//        this.address = address;
//    }

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

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", employeeCount=" + employeeCount +
                ", address='" + address + '\'' +
                '}';
    }
}
