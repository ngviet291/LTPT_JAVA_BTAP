package dto;

import entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobDTO implements Serializable {
    private String id;
    private String title;
    private String description;
    private double salary;
    private JobStatus status;
    private String companyId;
}
