package patron.mvc.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patron.mvc.mvc.dto.TurnoDTO;
import patron.mvc.mvc.dto.TurnoResponseDTO;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.entity.Turno;
import patron.mvc.mvc.mapper.PacienteMapper;
import patron.mvc.mvc.mapper.TurnoMapper;
import patron.mvc.mvc.service.TurnoService;

import java.util.List;

@RestController
@RequestMapping(value = "turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> guardarTurno(@RequestBody TurnoDTO turnoDTO) {
        Turno turno = null;
        try{
            turno = turnoService.saveTurno(turnoDTO);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(TurnoMapper.INSTANCE.turnoToTurnoResponseDTO(turno),HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        return null;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable int id) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable int id) {

        return null;
    }

    @PutMapping
    public ResponseEntity<Turno> updateTurno(@RequestBody Turno turno) {
        return null;
    }
}
