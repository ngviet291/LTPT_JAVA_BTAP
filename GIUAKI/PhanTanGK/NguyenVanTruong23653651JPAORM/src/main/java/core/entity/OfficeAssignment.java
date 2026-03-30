package core.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"instructor"})
@Builder

public class OfficeAssignment {
    private String location;
    private LocalDateTime timestamp;

    private Instructor instructor;
}