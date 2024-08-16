package patron.mvc.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.dao.IDao;
import patron.mvc.mvc.dao.PacienteDAOH2;
import patron.mvc.mvc.model.Paciente;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteDAOH2 pacienteDAO;

    public Paciente guardar(Paciente paciente) {
        return pacienteDAO.guardar(paciente);
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteDAO.buscarPorId(id);
    }

}
