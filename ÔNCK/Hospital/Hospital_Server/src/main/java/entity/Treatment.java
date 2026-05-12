package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString(exclude = {"patient","department","doctor"})
@NoArgsConstructor
//Lấy treatmentID làm id
@IdClass(TreatmentID.class)
public class Treatment {
    @Id
    private LocalDate startDate;
    private LocalDate endDate;
    private String diagnosis;
    @Id
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Id
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
