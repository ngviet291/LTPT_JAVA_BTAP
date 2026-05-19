package dto;

import entity.DoseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UpdateStatusDTO implements Serializable {
    private Long recordId;
    private DoseStatus doseStatus;
}
