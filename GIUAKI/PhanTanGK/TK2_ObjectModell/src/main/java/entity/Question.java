/*
 * @ (#) Question.java     1.0    3/23/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package entity;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/23/2026
 * @version:    1.0
 */

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Question {
    private String question_id;
    private String text;
    private List<String> options;
    private String correct_answer;

}
