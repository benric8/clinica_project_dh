package patron.mvc.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.dao.TurnoDAOH2;
import patron.mvc.mvc.model.Turno;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TurnoService {

    private final TurnoDAOH2 turnoDAO;

    public Turno guardar(Turno turno) {
        return turnoDAO.guardar(turno);
    }

    public Turno Actualizar(Turno turno) {
        return turnoDAO.actualizar(turno);
    }

    public void eliminar(int id) {
        turnoDAO.eliminar(id);
    }

    public List<Turno> buscarTodas() {
        return turnoDAO.listarTodos();
    }

    public Turno buscarPorId(Integer id) {
        return turnoDAO.buscarPorId(id);
    }
}
