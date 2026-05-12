/*
 * @ (#) ShowDTO.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package dto;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
@AllArgsConstructor
@Builder@Setter@Getter
@ToString
public class ShowDTO implements Serializable {
    private String id;
    private LocalDateTime localDateTime;
}
