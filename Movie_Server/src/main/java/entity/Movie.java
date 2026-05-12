package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie {
    @Id
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private int duration;
    @ElementCollection
    @CollectionTable(name = "movie_actors",joinColumns = @JoinColumn(name = "movie_id"))
    private Set<String> actors;

    @OneToMany(mappedBy = "movie")
    private Set<Show> show;

}
