package patron.mvc.mvc.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class Paciente {
    Integer id;
    String nombre;
    String apellido;
    String cedula;
    LocalDate fechaIngreso;
    Domicilio domicilio;
    String email;
}
