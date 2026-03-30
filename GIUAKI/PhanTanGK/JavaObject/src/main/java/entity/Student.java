package entity;


import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private boolean active;
    private List<String> phones;
   private List<Enrollment> enrollments = new ArrayList<>();
}
