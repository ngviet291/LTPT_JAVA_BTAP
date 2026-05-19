package dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowDTO implements Serializable {
    private String id;
    private LocalDateTime showDateTime;
    private String hallName;
}
