package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
@Builder
@ToString
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
    private Set<Ticket> tickets;
}
