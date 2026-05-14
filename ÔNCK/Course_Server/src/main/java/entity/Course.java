package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
@Entity
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Course implements Serializable {
    @Id
    @Column(name = "CourseID")
    private int id;
    @Column(name = "Credits")
    private int credits;
    @Column(name = "Title")
    private String title;
    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private Set<StudentGrade> studentGrades;
    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;
    @ManyToMany(mappedBy = "courses")
    @ToString.Exclude
    private Set<Instructor> instructors;
}
