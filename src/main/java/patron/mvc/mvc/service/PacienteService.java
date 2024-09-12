package patron.mvc.mvc.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.repository.PacienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PacienteService {
    @Autowired
    private  PacienteRepository pacienteRepository;

    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public Paciente updatePaciente(Paciente paciente) throws Exception {
        Optional<Paciente> pacienteToUpdate = pacienteRepository.findById(paciente.getId());
        Paciente pacienteUpdated;
        if(pacienteToUpdate.isPresent()) {
            pacienteUpdated= pacienteRepository.save(paciente);
        }else {
            throw new Exception("Paciente no encontrado");
        }
        return pacienteUpdated;
    }
    public boolean deletePaciente(Long id) {
        boolean deleted = false;
        pacienteRepository.deleteById(id);
        deleted = true;
        return deleted;
    }
    public Paciente getPacienteById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.orElse(null);
    }
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

}
