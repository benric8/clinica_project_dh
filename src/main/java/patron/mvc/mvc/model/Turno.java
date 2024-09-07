package patron.mvc.mvc.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Turno {
    Integer id;
    Paciente paciente;
    Odontologo odontologo;
    LocalDate fecha;
}
