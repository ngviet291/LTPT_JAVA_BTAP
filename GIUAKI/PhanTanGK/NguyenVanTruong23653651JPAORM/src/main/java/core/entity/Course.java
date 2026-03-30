package core.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"instructors", "prerequisites", "enrollments", "department"})
@SuperBuilder
public abstract class Course {
    protected String id;
    protected String title;
    protected Integer credits;

    private Set<Instructor> instructors;
    private Set<Course> prerequisites;
    private Set<Enrollment> enrollments;
    private Department department;

}

