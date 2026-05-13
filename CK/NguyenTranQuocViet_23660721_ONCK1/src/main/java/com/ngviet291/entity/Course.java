package com.ngviet291.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Course {
    @Column(name = "Credits")
    protected int credits;
    @Id
    @Column(name = "CourseID")
    protected int id;
    @Column(name = "Title")
    protected String title;

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Instructor> instructors;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<StudentGrade> studentGrades;
    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;

}
