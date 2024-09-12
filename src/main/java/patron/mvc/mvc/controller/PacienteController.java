package patron.mvc.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import patron.mvc.mvc.dto.PacienteDTO;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.mapper.PacienteMapper;
import patron.mvc.mvc.service.PacienteService;

@RestController
@RequestMapping(value = "pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> savePaciente(@RequestBody PacienteDTO pacienteDTO) {
        Paciente paciente= PacienteMapper.INSTANCE.pacienteDTOToPaciente(pacienteDTO);
        Paciente pacienteSaved = pacienteService.savePaciente(paciente);

        return new ResponseEntity<>(pacienteDTO,HttpStatus.OK);
    }

}
