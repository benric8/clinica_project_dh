package patron.mvc.mvc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter @Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Domicilio {
    Integer id;
    String calle;
    Integer numero;
    String localidad;
    String provincia;
}
