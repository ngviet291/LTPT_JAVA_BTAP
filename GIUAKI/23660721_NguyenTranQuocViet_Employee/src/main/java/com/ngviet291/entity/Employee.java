package com.ngviet291.entity;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Employee {
    private String id;
    private String lastName;
    private  String firstName;
    private String gender;
    private String phone;
    private Float salaryCoefficient;
    @ToString.Exclude
    private Set<Involvement> involvements;
    @ToString.Exclude
    private Department department;
}
