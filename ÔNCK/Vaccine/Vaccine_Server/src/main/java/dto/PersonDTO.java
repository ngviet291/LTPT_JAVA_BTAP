package dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PersonDTO implements Serializable {
    private String id;
    private String fullname;
    private LocalDate dob;
    private String gender;
    private double height;
    private double weight;
}
