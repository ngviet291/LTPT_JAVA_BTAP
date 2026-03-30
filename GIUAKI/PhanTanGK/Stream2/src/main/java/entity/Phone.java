/*
 * @ (#) Phone.java     1.0    3/17/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package entity;


import lombok.*;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/17/2026
 * @version:    1.0
 */
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
@Getter
@Builder
public class Phone {
    private String type;
    private String number;
}
