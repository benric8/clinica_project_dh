package patron.mvc.mvc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import patron.mvc.mvc.entity.Odontologo;

import java.time.LocalDate;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TurnoResponseDTO {
    Long id;
    LocalDate fecha;
    PacienteDTO pacienteDTO;
    OdontologoDTO odontologoDTO;
}
