package patron.mvc.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patron.mvc.mvc.dto.PacienteDTO;
import patron.mvc.mvc.dto.PacienteDTO;
import patron.mvc.mvc.entity.Domicilio;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.mapper.DomicilioMapper;
import patron.mvc.mvc.mapper.PacienteMapper;
import patron.mvc.mvc.mapper.PacienteMapper;
import patron.mvc.mvc.service.DomicilioService;
import patron.mvc.mvc.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping(value = "pacientes")

public class PacienteController {

    @Autowired
    private  PacienteService pacienteService;

    @Autowired
    private DomicilioService domicilioService;

    @PostMapping
    public ResponseEntity<PacienteDTO> savePaciente(@RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente= PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
        Domicilio domicilio = DomicilioMapper.INSTANCE.domicilioDTOToDomicilio(pacienteDTO.getDomicilio());
        Domicilio domicilioSaved = domicilioService.save(domicilio);
        Paciente pacienteSaved = null;
        if (domicilioSaved == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            paciente.setDomicilio(domicilioSaved);
            pacienteSaved = pacienteService.savePaciente(paciente);
            if (pacienteSaved == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteSaved), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PacienteDTO> updatePaciente(@RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente= PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
        Domicilio domicilio = DomicilioMapper.INSTANCE.domicilioDTOToDomicilio(pacienteDTO.getDomicilio());
        Domicilio domicilioUpdated = domicilioService.actualizarDomicilio(domicilio);
        Paciente pacienteUpdated = null;
        if (domicilioUpdated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            paciente.setDomicilio(domicilioUpdated);
            try{
                pacienteUpdated = pacienteService.updatePaciente(paciente);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
        }

        return new ResponseEntity<>(PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteUpdated), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) {
        if (pacienteService.deletePaciente(id)) {
            return new ResponseEntity<>("Paciente eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<>("Paciente no encontrado", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PacienteDTO> findPacienteById(@PathVariable Long id) {
        return new ResponseEntity<>(PacienteMapper.INSTANCE.pacienteToPacienteDTO(pacienteService.getPacienteById(id)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> getAllPacientes(){
        return new ResponseEntity<>(PacienteMapper.INSTANCE.pacienteToPacientesDTO(pacienteService.getAllPacientes()), HttpStatus.OK);
    }

}
