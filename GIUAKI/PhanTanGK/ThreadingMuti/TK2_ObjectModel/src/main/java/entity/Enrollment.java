package entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Enrollment {
    private Student student;
    private Course course;
    private Double grade;

    public Enrollment(Course course, Double grade) {
        this.course = course;
        this.grade = grade;
    }
}
