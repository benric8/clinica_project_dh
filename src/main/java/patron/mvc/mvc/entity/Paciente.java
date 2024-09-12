package patron.mvc.mvc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="pacientes")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String apellido;
    @Column(unique=true)
    String email;
    String cedula;
    LocalDate fechaIngreso;
    @OneToOne(cascade=CascadeType.ALL) @JoinColumn(name="domicilio_id", referencedColumnName = "id")
    Domicilio domicilio;


}