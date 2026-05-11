package dto;

import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDTO implements Serializable {
    private String id;
    private String name;
    private String phone;
    private  String speciality;
}
