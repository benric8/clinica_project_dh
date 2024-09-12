package patron.mvc.mvc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BodyException {
    private int statusCode;
    private String message;

}
