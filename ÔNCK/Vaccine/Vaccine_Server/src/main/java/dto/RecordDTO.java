package dto;

import entity.DoseStatus;
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
public class RecordDTO implements Serializable {
    private long id;
    private LocalDate injectionDate;
    private int doseNumber;
    private DoseStatus status;
    private String personId;
    private String vaccineId;
}
