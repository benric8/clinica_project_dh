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
        odontologoDTO.setId(odontologoSaved.getId());
        return new ResponseEntity<>(odontologoDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OdontologoDTO> updateOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        Odontologo odontologo = OdontologoMapper.INSTANCE.odontologoDTOtoOdontologo(odontologoDTO);
       try {
        Odontologo odontologoUpdated = odontologoService.updateOdontologo(odontologo);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       return new ResponseEntity<>(odontologoDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteOdontologo(@PathVariable Long id) {
        if (odontologoService.deleteOdontologo(id)) {
            return new ResponseEntity<>("Odontologo eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Odontologo no encontrado", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OdontologoDTO> findOdontologoById(@PathVariable Long id) {
        return new ResponseEntity<>(OdontologoMapper.INSTANCE.odontologoToOdontologoDTO(odontologoService.getOdontologoById(id)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDTO>> getAllOdontologos(){
        return new ResponseEntity<>(OdontologoMapper.INSTANCE.odontologosToOdontologosDTO(odontologoService.getAllOdontologos()), HttpStatus.OK);
    }

}
