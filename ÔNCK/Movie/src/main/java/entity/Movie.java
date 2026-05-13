package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
@Builder
@Table(name = "movies")
public class Movie {
    @Id
    @Column(name = "movie_id")
    private String id;
    private String title;
    private String genre;
    @Column(name = "release_year")
    private int releaseYear;
    private String director;
    private int duration;
    @ElementCollection
    @CollectionTable(name = "movie_actors",joinColumns = @JoinColumn(name = "movie_id"))
    @ToString.Exclude
    @Column(name = "actor")
    private Set<String> actors;
    @ToString.Exclude
    @OneToMany(mappedBy = "movie")
    private Set<Show> shows;
}
