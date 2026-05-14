package entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class OnsiteCourse extends Course implements Serializable {
    @Column(name = "Days")
    private String days;
    @Column(name = "Location")
    private  String location;
    @Column(name = "Time")
    private LocalDateTime time;
}
