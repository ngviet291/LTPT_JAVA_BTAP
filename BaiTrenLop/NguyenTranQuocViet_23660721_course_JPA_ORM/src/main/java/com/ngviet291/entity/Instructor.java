package com.ngviet291.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"officeAssignment", "courses"})
@SuperBuilder
@Entity
public class Instructor extends Person {
    private LocalDateTime hireDate;

    private OfficeAssignment officeAssignment;
//    private Set<Course> courses;
}