package patron.mvc.mvc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DomicilioDTO {
    Long id;
    String calle;
    Integer numero;
    String localidad;
    String provincia;
}
