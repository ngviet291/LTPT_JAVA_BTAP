package com.ngviet291.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Project {
    private String id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private double budget;
    private Set<Involvement> involvements;
}
