package entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OfficeAssignment implements Serializable {
    @Column(name = "Location")
    private String location;
    @Column(name = "Timestamp")
    private Timestamp timestamp;
    @Id
    @Column(name = "InstructorID")
    private int id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;
}
