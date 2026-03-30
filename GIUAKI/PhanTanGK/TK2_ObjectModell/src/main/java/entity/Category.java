/*
 * @ (#) Category.java     1.0    3/23/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package entity;


import lombok.*;

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
public class Category {
    private String category_id;
    private String name;
}
