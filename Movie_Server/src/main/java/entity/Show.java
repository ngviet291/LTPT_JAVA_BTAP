package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "shows")
@ToString
public class Show {
    @Id
    @Column(name = "show_id")
    private String id;
    @Column(name = "show_date_time")
    private LocalDateTime showDateTime;
    @Column(name = "hall_name")
    private String hallName;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @OneToMany(mappedBy = "show")
    @ToString.Exclude
    private Set<Ticket> tickets;

}
