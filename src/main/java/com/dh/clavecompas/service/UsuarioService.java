package com.dh.clavecompas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.dh.clavecompas.entity.Usuario;
import com.dh.clavecompas.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }else {
            throw new UsernameNotFoundException("El usuario no existe"+username);
        }
    }
}
