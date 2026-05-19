package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "skills")
public class Skill {
    @Id
    @Column(name = "skill_id")
    private String id;
    private String name;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Job> jobs;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Candidate> candidates;
}
