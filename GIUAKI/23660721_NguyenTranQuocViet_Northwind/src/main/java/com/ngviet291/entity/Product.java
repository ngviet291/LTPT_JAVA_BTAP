package com.ngviet291.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("product_name")
    private String productName;
    private String unit;
    @JsonProperty("unit_price")
    private double unitPrice;
    @JsonProperty("units_in_stock")
    private int unitInStock;
    @ToString.Exclude
    private Supplier supplier;
}
