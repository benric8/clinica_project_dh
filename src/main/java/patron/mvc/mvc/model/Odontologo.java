package patron.mvc.mvc.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Odontologo {
    Integer id;
    String nombre;
    String apellido;
    String matricula;
}
