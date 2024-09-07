package patron.mvc.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patron.mvc.mvc.model.Paciente;
import patron.mvc.mvc.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping(value = "pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        Paciente pacienteSave= null;
        try {
            pacienteSave=pacienteService.guardar(paciente);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(pacienteSave);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes() {
        return ResponseEntity.ok(pacienteService.buscarTodas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable int id) {
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable int id) {
        pacienteService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Paciente> updatePaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.Actualizar(paciente));
    }
}
