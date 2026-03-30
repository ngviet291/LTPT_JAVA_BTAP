package core.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Enrollment {
    private Student student;
    private Course course;
    private String semester;
    private Double grade;

}