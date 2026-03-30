/*
 * @ (#) Person.java     1.0    3/17/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package entity;


import lombok.*;
import entity.*;
import java.util.List;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/17/2026
 * @version:    1.0
 */
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
@Getter
@Builder
public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private Address address;
    private List<Phone> phoneNumbers;


}
