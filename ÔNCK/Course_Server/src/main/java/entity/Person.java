package entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="Discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Person implements Serializable {
    @Id
    @Column(name = "PersonID")
    protected int id;
    @Column(name = "LastName")
    protected String lastName;
    @Column(name = "FirstName")
    protected String firstName;
}
