package patron.mvc.mvc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import patron.mvc.mvc.dto.TurnoDTO;
import patron.mvc.mvc.dto.TurnoResponseDTO;
import patron.mvc.mvc.entity.Turno;

import java.util.List;

@Mapper(uses = {PacienteMapper.class,OdontologoMapper.class})
public interface TurnoMapper {
    TurnoMapper INSTANCE = Mappers.getMapper(TurnoMapper.class);

    @Mapping(source = "pacienteDTO" ,target = "paciente")
    @Mapping(source = "odontologoDTO" ,target = "odontologo")
    Turno turnoResponseDTOToTurno(TurnoResponseDTO turnoResponseDTO);

    @Mapping(source = "paciente" ,target = "pacienteDTO")
    @Mapping(source = "odontologo" ,target = "odontologoDTO")
    TurnoResponseDTO turnoToTurnoResponseDTO(Turno turno);

    List<TurnoResponseDTO> turnoToTurnoResponseDTO(List<Turno> turnos);

}
