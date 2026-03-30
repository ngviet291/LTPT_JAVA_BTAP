package core.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"courses"})
@Builder
public class Department {
    private String id;
    private String name;
    private Double budget;
    private LocalDateTime startDate;
    private String administrator;

    private Set<Course> courses;
}

