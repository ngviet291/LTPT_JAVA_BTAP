package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "candidates")
public class Candidate {
    @Id
    @Column(name = "cand_id")
    private String id;
    private String name;
    private String email;
    private int experience;

    @ManyToMany
    @JoinTable(
            name = "candidates_skills",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @ToString.Exclude
    private Set<Skill> skills;

    @OneToMany(mappedBy = "candidate")
    @ToString.Exclude
    private Set<Application> applications;

}
