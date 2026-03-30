package com.ngviet291.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Set<Involvement> involvements;
    private Department department;
}
