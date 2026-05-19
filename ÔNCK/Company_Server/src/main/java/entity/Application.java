package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(ApplicationID.class)
@Table(name = "applications")
public class Application {
    @Id
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
    @Id
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "job_id")
    private Job job;
    @Column(name = "applied_date")
    private LocalDate appliedDate;
    @Enumerated(EnumType.STRING)
    private AppStatus status;
}
