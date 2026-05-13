package dto;


import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
@Builder
public class MovieDTO implements Serializable {
    private String id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private int duration;
}
