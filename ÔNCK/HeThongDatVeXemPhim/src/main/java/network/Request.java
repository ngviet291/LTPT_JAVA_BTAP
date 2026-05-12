/*
 * @ (#) Request.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package network;


import lombok.*;

import java.io.Serializable;

/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class Request implements Serializable {
private SELECTED selected;
private Object data;
}
