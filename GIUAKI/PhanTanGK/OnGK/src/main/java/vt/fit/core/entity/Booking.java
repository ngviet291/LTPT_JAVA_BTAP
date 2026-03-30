package vt.fit.core.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Booking {
    @JsonProperty("id")
    private String id;
    private String startDate;
    private String endDate;
    private double deposit;
    private String note;


}