package com.ngviet291.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @JsonProperty("order_id")
    private String orderID;
    @JsonProperty("order_date")
    private LocalDate orderDate;
    @JsonProperty("customer_name")
    private String customerName;
    @JsonProperty("employee_name")
    private String employeeName;
    private Status status;
}
