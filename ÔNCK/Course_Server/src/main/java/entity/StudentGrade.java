package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class StudentGrade implements Serializable {
    @Id
    private int enrollmentId;
    @Column(name = "Grade")
    private Double grade;
    @ManyToOne
    @JoinColumn(name = "CourseID")
    @ToString.Exclude
    private Course course;
    @ManyToOne
    @JoinColumn(name = "StudentID")
    @ToString.Exclude
    private Student student;
}
