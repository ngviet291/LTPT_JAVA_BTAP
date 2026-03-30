/*
 * @ (#) Address.java     1.0    3/17/2026
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder
public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private Long postalCode;

}
