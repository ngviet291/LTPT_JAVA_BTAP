package com.ngviet291.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DiscriminatorValue("Student")
public class Student extends Person {
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;

    //OneToMany la 1 cai Set
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<StudentGrade> studentGrades;
}
