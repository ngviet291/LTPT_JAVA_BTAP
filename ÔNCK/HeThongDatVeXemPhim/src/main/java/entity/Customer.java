package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")

public class Customer implements Serializable {
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
    private Set<Ticket> tickets = new HashSet<>();
}