package com.ngviet291.entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"enrollments"})
@SuperBuilder
@Entity

public class Student extends Person {
    private LocalDateTime enrollmentDate;

//    private Set<Enrollment> enrollments;
}
