package com.ngphthinh.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quizz
{
    private String id;
    private String name;
    private int score;
    private List<Question> questions;
    private Category category;
}
