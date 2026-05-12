package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString(exclude = "treatments")
@SuperBuilder
@NoArgsConstructor
public class Patient extends Person{
    private  String gender;
    private LocalDate dateOfBirth;
    private  String address;
    @OneToMany(mappedBy = "patient")
    private Set<Treatment> treatments;
}
