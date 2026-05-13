package com.ngviet291.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("Instructor")
public class Instructor extends Person {
    @Column(name = "HireDate")
    private LocalDateTime hireDate;
    //cascade là thay đổi bất kỳ gì ở cha thì ở con cx thay đổi, lazy là load dữ liệu khi cần
    @OneToOne(mappedBy = "instructor",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private OfficeAssignment officeAssignment;

    @ManyToMany
    @JoinTable(name = "CourseInstructor",joinColumns =@JoinColumn(name = "PersonID"),inverseJoinColumns =@JoinColumn(name = "CourseID"))
    private Set<Course> courses;
}
