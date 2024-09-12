package patron.mvc.mvc.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import patron.mvc.mvc.dto.PacienteDTO;
import patron.mvc.mvc.entity.Paciente;

@Mapper
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    PacienteDTO pacienteToPacienteDTO(Paciente paciente);
    Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO);
}
