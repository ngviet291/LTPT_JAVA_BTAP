package com.ngviet291.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnsiteCourse extends Course{
    @Column(name = "Days")
    private String days;
    @Column(name = "Location")
    private String location;
    @Column(name = "Time")
    private LocalDateTime time;
}
