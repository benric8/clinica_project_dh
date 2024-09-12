package patron.mvc.mvc.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TurnoDTO {
    Long id;
     LocalDate fecha;
     Long pacienteId;
     Long odontologoId;
}
