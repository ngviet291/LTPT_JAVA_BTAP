package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @Column(name = "vaccine_id")
    private String id;
    @Column(name = "vaccine_name")
    private String name;
    private String manufacturer;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @OneToMany(mappedBy = "vaccine")
    @ToString.Exclude
    private Set<Record> records;
}
