package entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Department implements Serializable {
    @Id
    @Column(name = "DepartmentID")
    private int id;
    @Column(name = "Administrator")
    private int administrator;
    @Column(name = "Budget")
    private double budget;
    @Column(name = "Name")
    private String name;
    @Column(name = "StartDate")
    private LocalDateTime startDate;
    @OneToMany(mappedBy = "department")
    @ToString.Exclude
    private Set<Course> courses;
}
