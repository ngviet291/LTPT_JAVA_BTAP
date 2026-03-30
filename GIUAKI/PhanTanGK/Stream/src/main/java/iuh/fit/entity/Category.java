/*
 * @ (#) Category.java     1.0    3/16/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package iuh.fit.entity;


import lombok.*;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/16/2026
 * @version:    1.0
 */
@NoArgsConstructor
@Builder
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Category {
    private String id;
    private String name;

}
