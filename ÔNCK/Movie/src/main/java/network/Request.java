package network;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
    private Object data;
    private CommandType commandType;
}
