/**
 * @ (#) Quizze.java   1.0     23/3/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.entity;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 23/3/2026
 */
import  lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Quizz {
    private String quiz_id;
    private String name;
    private  int score;
    private List<Question>  questions;
    private Category category;
}
