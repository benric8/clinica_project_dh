package patron.mvc.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import patron.mvc.mvc.entity.Usuario;
import patron.mvc.mvc.entity.UsuarioRole;
import patron.mvc.mvc.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatosIniciales implements ApplicationRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passCifrado = bCryptPasswordEncoder.encode("admin");
        Usuario usuarioAdmin = new Usuario("Richard", "benric", passCifrado, "benric@digitalhouse.com", UsuarioRole.ROLE_ADMIN);
        Usuario usuarioUser = new Usuario("Cinthya", "pam07", passCifrado, "pam07@digitalhouse.com", UsuarioRole.ROLE_USER);
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuarioAdmin);
        usuarios.add(usuarioUser);
        System.out.println("pass: " + passCifrado);
        usuarioRepository.saveAll(usuarios);


    }
}