package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "treatments")
public class Department {
    @Id
    private String id;
    private String name;
    private String location;
    @OneToMany(mappedBy = "department")
    private Set<Treatment> treatments;
}
