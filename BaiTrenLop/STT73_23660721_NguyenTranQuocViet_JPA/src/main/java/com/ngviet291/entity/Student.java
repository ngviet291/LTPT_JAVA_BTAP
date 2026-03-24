/**
 * @ (#) Student.java   1.0     10/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 10/3/2026
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@Builder
@Table(name = "students") // Optional: specify the table name if different from the class name
public class Student {//default: Tablename is Student
    @Id private  String id; // default: Column name is id, primary key
    @Column(name = "first_name")
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    @ElementCollection
    @CollectionTable(name = "phone_numbers",
            joinColumns = @JoinColumn(name = "id")
    )
    @Column(nullable = false, name = "phone_number")

    private Set<String> phoneList;
   @ManyToOne
   @JoinColumn(name="classId")
    private Clazz clazz;
}
