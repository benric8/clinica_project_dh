package patron.mvc.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patron.mvc.mvc.model.Odontologo;
import patron.mvc.mvc.service.OdontologoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo odontologoSave= null;
        try {
            odontologoSave=odontologoService.guardar(odontologo);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(odontologoSave);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodas());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable int id) {
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable int id) {
        odontologoService.eliminar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Odontologo> updateOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.Actualizar(odontologo));
    }
}
