package patron.mvc.mvc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.dto.TurnoDTO;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.entity.Turno;
import patron.mvc.mvc.repository.OdontologoRepository;
import patron.mvc.mvc.repository.PacienteRepository;
import patron.mvc.mvc.repository.TurnoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TurnoService {

    @Autowired
    private  TurnoRepository turnoRepository;

    @Autowired private OdontologoService odontologoService ;
    @Autowired private PacienteService pacienteService;

    public Turno saveTurno(TurnoDTO turnoDTO) throws Exception{
        Paciente paciente = pacienteService.getPacienteById(turnoDTO.getPacienteId());
        Odontologo odontologo = odontologoService.getOdontologoById(turnoDTO.getOdontologoId());
        if (paciente == null || odontologo == null) {
            throw new Exception("el odontoologo o el paciente no existen");
        }
        Turno turno = new Turno();
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(turnoDTO.getFecha());
        return turnoRepository.save(turno);
    }
    public Turno updateTurno(Turno turno) throws Exception {
        Optional<Turno> turnoToUpdate = turnoRepository.findById(turno.getId());
        Turno turnoUpdated;
        if(turnoToUpdate.isPresent()) {
            turnoUpdated= turnoRepository.save(turno);
        }else {
            throw new Exception("Turno no encontrado");
        }
        return turnoUpdated;
    }
    public boolean deleteTurno(Long id) {
        boolean deleted = false;
        turnoRepository.deleteById(id);
        deleted = true;
        return deleted;
    }
    public Turno getTurnoById(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        return turno.orElse(null);
    }
    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }
}
