package com.ngviet291.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Column(name = "Administrator")
    private int administrator;
    @Column(name = "Budget")
    private double budget;
    @Id
    @Column(name = "DepartmentID")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    private LocalDateTime startDate;
    @OneToMany(mappedBy = "department")
    private Set<Course> courses;
}
