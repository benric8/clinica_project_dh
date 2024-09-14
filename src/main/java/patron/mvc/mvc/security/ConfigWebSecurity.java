package patron.mvc.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import patron.mvc.mvc.service.UsuarioService;

import java.util.regex.Pattern;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ConfigWebSecurity {
    @Autowired private UsuarioService usuarioService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(usuarioService);
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                authorizeRequests
                        .requestMatchers("/turnos","turnos/").hasRole("USER")
                        .requestMatchers("/odontologos","odontologos/").hasRole("ADMIN")
                        .requestMatchers("/pacientes","pacientes/").hasRole("ADMIN")
                        .anyRequest().authenticated())
                        .formLogin(withDefaults())
                        .logout(withDefaults());
        return http.build();
    }
}
