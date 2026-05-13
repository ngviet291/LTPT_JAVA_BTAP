package com.ngviet291.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
public abstract class Person {
    @Column(name = "FirstName")
    protected String firstName;
    @Id
    @Column(name = "PersonID")
    protected int id;
    @Column(name = "LastName")
    protected String lastName;
}
