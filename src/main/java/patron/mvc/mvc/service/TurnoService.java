package patron.mvc.mvc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.entity.Turno;
import patron.mvc.mvc.repository.TurnoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TurnoService {
    private final TurnoRepository turnoRepository;

    public Turno saveTurno(Turno turno) {
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
