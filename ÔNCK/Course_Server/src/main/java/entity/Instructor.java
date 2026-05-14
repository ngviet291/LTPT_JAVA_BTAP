package entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder

@Entity
@DiscriminatorValue("Instructor")
public class Instructor extends Person{
    @Column(name = "HireDate")
    private LocalDateTime hireDate;
    @ManyToMany
    @JoinTable(
            name = "CourseInstructor",
            joinColumns = @JoinColumn(name = "PersonID"),
            inverseJoinColumns = @JoinColumn(name = "CourseID")
    )
    @ToString.Exclude
    private Set<Course> courses;
    @ToString.Exclude
    @OneToOne(mappedBy = "instructor")
    private OfficeAssignment officeAssignment;
}
