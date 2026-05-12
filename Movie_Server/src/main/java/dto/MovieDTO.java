package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieDTO implements Serializable {
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private int duration;
    private Set<String> actors;
}
