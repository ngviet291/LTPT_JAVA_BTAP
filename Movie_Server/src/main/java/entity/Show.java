package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "shows")
public class Show {
    @Id
    private String id;
    private LocalDateTime showDateTime;
    private String hallName;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @OneToMany(mappedBy = "show")
    private Set<Ticket> tickets;
}
