package entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "shows")
public class Show {
    @Id
    @Column(name = "show_id")
    private String id;
    @Column(name = "show_date_time")
    private LocalDateTime showDateTime;
    @Column(name = "hall_name")
    private String hallName;
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "movie_id")
    private Movie movie;
    @OneToMany(mappedBy = "show",fetch = FetchType.EAGER)
    @ToString.Exclude
    private Set<Ticket> tickets;
}
