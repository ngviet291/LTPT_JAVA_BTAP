package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "shows")
@ToString
public class Show  implements Serializable {
@Id
@Column(name = "show_id")
    private String id;
@Column(name = "show_date_time")
    private LocalDateTime showDateTime;
@Column(name = "hall_name")
    private String hallName;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
      private Movie movie;
    @OneToMany(mappedBy = "show")
    @ToString.Exclude
    private Set<Ticket> tickets = new HashSet<>();
}