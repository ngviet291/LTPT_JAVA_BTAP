package com.ngviet291.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Supplier {
    @JsonProperty("supplier_id")
    private String supplierId;
    @JsonProperty("company_name")
    private String companyName;
    @JsonProperty("contact_name")
    private String contactName;
    private String country;
}
