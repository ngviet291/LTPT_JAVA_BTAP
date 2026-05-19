package dto;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO implements Serializable {
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private int duration;
    private Set<String> actors;
}
