package entity;

import jakarta.persistence.IdClass;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TreatmentID {
    private LocalDate startDate;
    private String patient;
    private String department;
}
