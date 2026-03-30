package core.entity;

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

public class Student extends Person {
    private LocalDateTime enrollmentDate;

    private Set<Enrollment> enrollments;
}
