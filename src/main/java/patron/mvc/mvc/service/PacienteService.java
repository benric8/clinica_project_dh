package patron.mvc.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.dao.IDao;
import patron.mvc.mvc.dao.PacienteDAOH2;
import patron.mvc.mvc.model.Paciente;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteDAOH2 pacienteDAO;

    public Paciente guardar(Paciente paciente) {
        return pacienteDAO.guardar(paciente);
    }

    public Paciente Actualizar(Paciente paciente) {
        return pacienteDAO.actualizar(paciente);
    }

    public void eliminar(int id) {
        pacienteDAO.eliminar(id);
    }

    public List<Paciente> buscarTodas() {
        return pacienteDAO.listarTodos();
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteDAO.buscarPorId(id);
    }

}
