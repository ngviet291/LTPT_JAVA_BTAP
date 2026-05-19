package dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VaccineDTO implements Serializable {
    private String id;
    private String name;
    private String manufacturer;
    private LocalDate expirationDate;
}
