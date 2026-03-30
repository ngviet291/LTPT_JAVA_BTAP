/*
 * @ (#) Quiz.java     1.0    3/23/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package entity;


import lombok.*;

import java.util.List;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/23/2026
 * @version:    1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Quiz {
    private String quiz_id;
    private String name;
    private int score;
    private List<Question> questions;
    private Category category;
}
