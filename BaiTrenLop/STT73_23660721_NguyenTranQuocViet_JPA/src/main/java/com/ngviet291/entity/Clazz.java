/**
 * @ (#) Class.java   1.0     10/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 10/3/2026
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Clazz {
    @Id
    @Column(name="classId")
    private String classId;
    @Column(name="className",columnDefinition = "nvarchar(100)",unique = true,nullable = false)
    private  String className;
    @Column(name="numbers")
    private int numbers;

    @OneToMany(mappedBy = "clazz")
    private List<Student> students;
}
