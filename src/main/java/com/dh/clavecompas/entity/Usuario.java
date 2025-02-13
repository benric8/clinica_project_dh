package com.dh.clavecompas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity @Table(name="usuarios")
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NonNull
    private String nombre;
    @NonNull
    @Column(unique = true, nullable = false)
    private String userName;
    @NonNull
    private String password; //admin
    @NonNull
    @Column(unique = true, nullable = false)
    private String email;
    @NonNull
    private UsuarioRole usuarioRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(usuarioRole.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return email;
    }
}
