/**
 * @ (#) Student.java   1.0     27/1/2026
 * <p>
 * Copyright (c) 2026 IUH. All rights reserved
 */
package com.ngviet291.entity;




import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description
 * @author: Nguyen Tran Quoc Viet 
 * @version: 1.0
 * @created: 27/1/2026
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private boolean isActive;
    private List<String> phones;

}
