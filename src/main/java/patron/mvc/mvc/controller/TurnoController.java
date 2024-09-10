package patron.mvc.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patron.mvc.mvc.model.Turno;
import patron.mvc.mvc.service.TurnoService;

import java.util.List;

@RestController
@RequestMapping(value = "turnos")
@RequiredArgsConstructor
public class TurnoController {

    private final TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {
        Turno turnoSave= null;
        try {
            turnoSave=turnoService.guardar(turno);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(turnoSave);
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable int id) {
        return ResponseEntity.ok(turnoService.buscarPorId(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable int id) {
        turnoService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Turno> updateTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.Actualizar(turno));
    }
}
