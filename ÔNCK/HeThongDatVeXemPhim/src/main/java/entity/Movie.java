package entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "movies")

public class Movie implements Serializable {

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
            joinColumns = @JoinColumn(name = "movie_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id","actor"})
    )
    @Column(name = "actor")
    private Set<String> actors = new HashSet<>();
    @ToString.Exclude
    @OneToMany(mappedBy = "movie")
    private Set<Show> shows;
    // Getter Setter
}