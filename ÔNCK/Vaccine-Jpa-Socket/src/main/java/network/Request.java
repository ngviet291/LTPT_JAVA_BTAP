package network;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Request implements Serializable {
    private CommandType commandType;
    private Object data;
}
