package patron.mvc.mvc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor @Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="domicilios")
public class Domicilio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String calle;
    Integer numero;
    String localidad;
    String provincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

