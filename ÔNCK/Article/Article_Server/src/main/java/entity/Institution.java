package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "institutions")
public class Institution {
    @Id
    @Column(name = "institution_id")
    private String id;
    private String name;
    private String country;
    @OneToMany(mappedBy = "institution")
    @ToString.Exclude
    private List<Author> authors;
}
