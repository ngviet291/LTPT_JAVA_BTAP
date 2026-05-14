package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@Getter
@Builder
public class ShowDTO implements Serializable{
    private String id;
    private LocalDateTime showDateTime;

    private MovieDTO movieDTO;
}
