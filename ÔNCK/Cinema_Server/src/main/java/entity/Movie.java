package entity;

import jakarta.persistence.*;
import jdk.jfr.Experimental;
import lombok.*;

import java.util.Set;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @CollectionTable(
            name = "movie_actors",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "actor")
    private Set<String> actors;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private Set<Show> shows;
}
