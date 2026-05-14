package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "authors")
public class Author {
    @Id
    @Column(name = "author_id")
    private String id;
    private String fullName;
    private String email;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "institution_id")
    private Institution institution;
    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private List<Article> articles;
}
