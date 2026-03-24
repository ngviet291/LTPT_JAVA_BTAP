package com.ngviet291.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    protected String id;
    protected String firstName;
    protected String lastName;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}