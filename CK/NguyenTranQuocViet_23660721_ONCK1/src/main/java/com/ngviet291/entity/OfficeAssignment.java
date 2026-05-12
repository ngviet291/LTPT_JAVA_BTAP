package com.ngviet291.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeAssignment {
    @Id
    @Column(name = "InstructorID")
    private int instructorId;
    //quan he 1-1 la co mapID
    @OneToOne
    @MapsId
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;
    @Column(name = "Location")
    private String location;
    @Column(name = "Timestamp")
    private Timestamp timestamp;
}
