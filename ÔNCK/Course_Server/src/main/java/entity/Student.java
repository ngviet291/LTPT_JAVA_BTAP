package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
@DiscriminatorValue("Student")
@ToString(callSuper = true)

public class Student extends Person implements Serializable {
    @Column(name = "EnrollmentDate")
    private LocalDateTime enrollmentDate;
    @OneToMany(mappedBy = "student",fetch = FetchType.EAGER)

    @ToString.Exclude
    private Set<StudentGrade> studentGrades;
}
