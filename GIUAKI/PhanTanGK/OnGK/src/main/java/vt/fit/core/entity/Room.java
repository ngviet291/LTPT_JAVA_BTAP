/*
 * @ (#) Room.java     1.0    3/8/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Room {
    @JsonProperty("id")
    private String id;
    private String description;
    private String type;
    private String bedOptions;
    private int sleepsCount;
    private boolean smokingAllowed;
    private double price;


}
