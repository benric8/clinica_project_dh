package patron.mvc.mvc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="odontologos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Odontologo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String apellido;
    String matricula;


}
