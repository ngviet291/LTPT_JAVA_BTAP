package com.ngviet291.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Song {
    private String id;
    private  String name;
    private  String runtime;
    private  String lyric;
    private String fileLink;
    private Set<Album> albums;
}
