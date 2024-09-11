package patron.mvc.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patron.mvc.mvc.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
