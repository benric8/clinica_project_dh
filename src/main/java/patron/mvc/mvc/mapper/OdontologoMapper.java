package patron.mvc.mvc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import patron.mvc.mvc.dto.OdontologoDTO;
import patron.mvc.mvc.entity.Odontologo;

import java.util.List;

@Mapper
public interface OdontologoMapper {
    OdontologoMapper INSTANCE  = Mappers.getMapper(OdontologoMapper.class);
    OdontologoDTO odontologoToOdontologoDTO(Odontologo odontologo);
    Odontologo odontologoDTOtoOdontologo(OdontologoDTO odontologoDTO);
    List<OdontologoDTO> odontologosToOdontologoDtos(List<Odontologo> odontologos);
}
