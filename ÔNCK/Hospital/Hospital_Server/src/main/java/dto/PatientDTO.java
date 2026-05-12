package dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientDTO implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
}
