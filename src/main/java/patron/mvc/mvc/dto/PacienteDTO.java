package patron.mvc.mvc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PacienteDTO {
    Long id;
    String nombre;
    String apellido;
    String email;
    String cedula;
    LocalDate fechaIngreso;
    DomicilioDTO domicilio;
}
