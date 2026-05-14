package entity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Entity
public class OnlineCourse extends Course implements Serializable {
    @Column(name = "URL")
    private String url;
}
