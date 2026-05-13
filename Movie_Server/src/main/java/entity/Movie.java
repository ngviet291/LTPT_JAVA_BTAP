package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
@Builder
@ToString
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
    @ElementCollection()
    @ToString.Exclude
    @CollectionTable(name = "movie_actors",joinColumns = @JoinColumn(name = "movie_id"))
    private Set<String> actors;
    @ToString.Exclude
    @OneToMany(mappedBy = "movie")
    private Set<Show> show;

}
