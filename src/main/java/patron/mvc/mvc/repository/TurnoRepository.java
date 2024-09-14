package patron.mvc.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.entity.Turno;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    public List<Turno> findByPacienteIdAndOdontologoId(Long idPaciente, Long idOdontologo);
    public List<Turno> findByPacienteId(Long idPaciente);
    public List<Turno> findByOdontologoId(Long idOdontologo);

}
