package patron.mvc.mvc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="odontologos")
public class Odontologo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String apellido;
    String matricula;
}
