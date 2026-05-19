package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String id;
    private String name;
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer")
    @ToString.Exclude
    private Set<Ticket> tickets;
}
