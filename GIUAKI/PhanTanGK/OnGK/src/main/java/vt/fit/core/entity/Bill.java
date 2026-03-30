/*
 * @ (#) Bill.java     1.0    3/8/2026
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
public class Bill {
    @JsonProperty("id")
    private String id;
    private Booking booking;
    private int days;
    private double serviceFee;
    private double totalAmount;
}
