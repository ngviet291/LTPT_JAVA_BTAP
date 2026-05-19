package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Table(name = "records")
public class Record {
    @Id
    @Column(name = "record_id")
    private long id;
    @Column(name = "injection_date")
    private LocalDate injectionDate;
    @Column(name = "dose_number")
    private int doseNumber;
    @Enumerated(EnumType.STRING)
    private DoseStatus status;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "person_id")
    private Person person;
}
