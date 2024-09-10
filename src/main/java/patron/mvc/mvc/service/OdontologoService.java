package patron.mvc.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patron.mvc.mvc.dao.OdontologoDAOH2;
import patron.mvc.mvc.model.Odontologo;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OdontologoService {
    
    private final OdontologoDAOH2 odontologoDao;

    public Odontologo guardar(Odontologo odontologo) {
        return odontologoDao.guardar(odontologo);
    }

    public Odontologo Actualizar(Odontologo odontologo) {
        return odontologoDao.actualizar(odontologo);
    }

    public void eliminar(int id) {
        odontologoDao.eliminar(id);
    }

    public List<Odontologo> buscarTodas() {
        return odontologoDao.listarTodos();
    }

    public Odontologo buscarPorId(Integer id) {
        return odontologoDao.buscarPorId(id);
    }
}
