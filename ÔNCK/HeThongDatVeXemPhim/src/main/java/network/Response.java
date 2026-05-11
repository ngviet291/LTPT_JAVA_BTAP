/*
 * @ (#) Response.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package network;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Setter
public class Response implements Serializable {
    private boolean success;
    private Object data;
}
