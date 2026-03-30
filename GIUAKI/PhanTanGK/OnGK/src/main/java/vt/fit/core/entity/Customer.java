/*
 * @ (#) Customer.java     1.0    3/8/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.core.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/8/2026
 * @version:    1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
    @JsonProperty("id")
    private String id;
    private String customerName;
    private String phoneNumber;
    private String email;


}
