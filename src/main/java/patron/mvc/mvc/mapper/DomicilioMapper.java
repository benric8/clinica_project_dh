package patron.mvc.mvc.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import patron.mvc.mvc.dto.DomicilioDTO;
import patron.mvc.mvc.entity.Domicilio;

@Mapper
public interface DomicilioMapper {
    DomicilioMapper INSTANCE = Mappers.getMapper(DomicilioMapper.class);

    DomicilioDTO domicilioToDomicilioDTO(Domicilio domicilio);

    Domicilio domicilioDTOToDomicilio(DomicilioDTO domicilioDTO);
}
