package com.ngviet291.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "StudentGrade")
public class StudentGrade {
    //ManyToOne la 1 thuoc tinh
    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;
    @Id
    @Column(name = "EnrollmentID")
    private int enrollmentID;
    @Column(name = "Grade")
    private double grade;
    //ManyToOne la 1 thuoc tinh
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;
}
