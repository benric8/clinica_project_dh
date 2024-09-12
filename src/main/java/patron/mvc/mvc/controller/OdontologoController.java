package patron.mvc.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patron.mvc.mvc.dto.OdontologoDTO;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.mapper.OdontologoMapper;
import patron.mvc.mvc.service.OdontologoService;

import java.util.List;

@RestController
@RequestMapping(value = "odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<OdontologoDTO> saveOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        Odontologo odontologo= OdontologoMapper.INSTANCE.odontologoDTOtoOdontologo(odontologoDTO);
        Odontologo odontologoSaved = odontologoService.saveOdontologo(odontologo);
        //odontologoDTO.setId(odontologoSaved.getId());
        return new ResponseEntity<>(odontologoDTO, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAllOdontologos(){
        return new ResponseEntity<>(OdontologoMapper.INSTANCE.odontologosToOdontologoDtos(odontologoService.getAllOdontologos()), HttpStatus.OK);
    }

}
