package core.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder

public abstract class Person {
    protected String id;
    protected String firstName;
    protected String lastName;
}