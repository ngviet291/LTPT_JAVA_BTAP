package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
