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

            turno = turnoService.saveTurno(turnoDTO);


        return new ResponseEntity<>(TurnoMapper.INSTANCE.turnoToTurnoResponseDTO(turno),HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<TurnoResponseDTO> updateTurno(@RequestBody TurnoDTO turnoDTO) {
        Turno turno = null;
        turno = turnoService.updateTurno(turnoDTO);
        return new ResponseEntity<>(TurnoMapper.INSTANCE.turnoToTurnoResponseDTO(turno),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnos(@RequestParam(required = false) Long idPaciente,
                                                    @RequestParam(required = false) Long idOdontologo) {
        List<Turno> turnos = turnoService.getTurnos(idPaciente, idOdontologo);

        return new ResponseEntity<>(TurnoMapper.INSTANCE.turnoToTurnoResponseDTO(turnos), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TurnoResponseDTO> buscarTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(TurnoMapper.INSTANCE.turnoToTurnoResponseDTO(turnoService.getTurnoById(id)),HttpStatus.OK );
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.deleteTurno(id)?"se elimino":"no se elimino",HttpStatus.OK);
    }




}
