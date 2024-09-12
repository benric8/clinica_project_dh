package patron.mvc.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patron.mvc.mvc.entity.Turno;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    public List<Turno> findByPacienteAndOdontologo(Long idPaciente, Long idOdontologo);
    public List<Turno> findByPaciente(Long idPaciente);
    public List<Turno> findByOdontologo(Long idOdontologo);

}
