package network;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Response implements Serializable {
    private boolean success;
    private Object data;
    private String message;
}
