package patron.mvc.mvc.unittest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import patron.mvc.mvc.dto.TurnoDTO;
import patron.mvc.mvc.entity.Domicilio;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.entity.Turno;
import patron.mvc.mvc.service.OdontologoService;
import patron.mvc.mvc.service.PacienteService;
import patron.mvc.mvc.service.TurnoService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TurnoServiceTest {
        @Autowired
        private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
        @Order(1)
        public void guardarTurno(){
            Domicilio domicilio = Domicilio.builder()
                    .calle("pizarro")
                    .numero(369)
                    .localidad("Ayacucho")
                    .provincia("Huamanga")
                    .build();
            Paciente paciente=Paciente.builder().nombre("Richard")
                    .apellido("bendezu")
                    .cedula("46300972")
                    .fechaIngreso(LocalDate.of(2024,9,10))
                    .email("benric.366@gmail.com")
                    .domicilio(domicilio)
                    .build();

            Paciente pacienteGuardado = pacienteService.savePaciente(paciente);

        Odontologo odontologo=Odontologo.builder().nombre("Richard")
                .nombre("Gabriel")
                .apellido("Castillo")
                .matricula("01242")
                .build();

        Odontologo odontologoGuardado = odontologoService.saveOdontologo(odontologo);

        TurnoDTO turno = TurnoDTO.builder()
                .pacienteId(pacienteGuardado.getId())
                .odontologoId(odontologoGuardado.getId())
                .fecha(LocalDate.of(2024,9,17))
                .build();

            Turno turnoGuardado = turnoService.saveTurno(turno);

            assertNotNull(turnoGuardado.getId());
            assertEquals(turno.getPacienteId(), turnoGuardado.getPaciente().getId());
        }
//
//        @Test
//        @Order(2)
//        public void buscarTurno(){
//            Turno turnoBUscado = turnoService.getTurnoById(1L);
//            assertNotNull(turnoBUscado);
//        }
//
//        @Test
//        @Order(3)
//        public void actualizarTurno(){
//
//            Turno turno=Turno.builder()
//                    .id(1L)
//                    .apellido("Bendezu Villena")
//                    .build();
//            Turno turnoActualizado = turnoService.updateTurno(turno);
//            assertEquals(turno.getApellido(), turnoActualizado.getApellido());
//        }
//        @Test
//        @Order(4)
//        public void ListarTodosTurnos(){
//            Domicilio domicilio = Domicilio.builder()
//                    .calle("Vencedroes")
//                    .numero(45)
//                    .localidad("Ayacucho")
//                    .provincia("Huamanga")
//                    .build();
//            Turno turno=Turno.builder().nombre("Cinthya")
//                    .apellido("Bolivia Bautista")
//                    .cedula("47112951")
//                    .fechaIngreso(LocalDate.of(2024,8,10))
//                    .email("pamela.07@gmail.com")
//                    .domicilio(domicilio)
//                    .build();
//            turnoService.saveTurno(turno);
//            List<Turno> turnos = turnoService.getAllTurnos();
//            assertNotEquals(0, turnos.size());
//            assertEquals(2,turnos.size());
//        }
//
//        @Test
//        @Order(5)
//        public void eliminarTurno(){
//            assertEquals(true,turnoService.deleteTurno(1L));
//        }
//



}
