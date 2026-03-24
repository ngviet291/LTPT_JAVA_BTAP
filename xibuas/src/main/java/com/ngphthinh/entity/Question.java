package com.ngphthinh.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    private String id;
    private String text;
    private List<String> options;
    private String correctAnswer;
}
