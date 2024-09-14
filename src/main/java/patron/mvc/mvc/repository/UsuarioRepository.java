package patron.mvc.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patron.mvc.mvc.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByEmail(String email);
}
