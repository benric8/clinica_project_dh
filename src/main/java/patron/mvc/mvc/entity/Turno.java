package patron.mvc.mvc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="turnos")
public class Turno {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne @JoinColumn(name = "paciente_id", referencedColumnName = "id")
    Paciente paciente;
    @ManyToOne @JoinColumn(name = "odontologo_id", referencedColumnName = "id")
    Odontologo odontologo;
    LocalDate fecha;

}