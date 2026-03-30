package core.entity;

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

public class Instructor extends Person {
    private LocalDateTime hireDate;

    private OfficeAssignment officeAssignment;
    private Set<Course> courses;
}