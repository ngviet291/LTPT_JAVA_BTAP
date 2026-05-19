package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @Column(name = "person_id")
    protected String id;
    @Column(name = "full_name")
    protected String fullname;
    protected LocalDate dob;
    protected String gender;
    protected double height;
    protected double weight;
    @OneToMany(mappedBy = "person")
    @ToString.Exclude
    protected Set<Record> records;
}
