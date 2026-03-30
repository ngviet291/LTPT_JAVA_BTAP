package com.ngviet291.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    private String id;
    private String title;
    private double price;
    private int yearOfRelease;
    private String downloadLink;
    private Genre genre;
    private Set<Artist> artists;
}
