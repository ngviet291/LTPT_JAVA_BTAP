/**
 * @ (#) Question.java   1.0     23/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.entity;


import java.util.List;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 23/3/2026
 */
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    private String question_id;
    private String text;
    private List<String> options;
    private String correct_answer;

}
