package com.ngviet291.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Artist {
    private String id;
    private String name;
    private LocalDate birthDate;
    private String url;
}
