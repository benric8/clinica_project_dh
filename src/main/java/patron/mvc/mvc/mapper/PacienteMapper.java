package patron.mvc.mvc.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import patron.mvc.mvc.dto.OdontologoDTO;
import patron.mvc.mvc.dto.PacienteDTO;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.entity.Paciente;

import java.util.List;

@Mapper(uses = DomicilioMapper.class)
public interface PacienteMapper {
    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);
    @Mapping(source = "domicilio",target = "domicilio")
    PacienteDTO pacienteToPacienteDTO(Paciente paciente);
    @Mapping(source = "domicilio",target = "domicilio")
    Paciente pacienteDTOToPaciente(PacienteDTO pacienteDTO);
    List<PacienteDTO> pacienteToPacientesDTO(List<Paciente> allPacientes);
}
