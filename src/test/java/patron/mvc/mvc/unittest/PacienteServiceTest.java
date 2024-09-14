package patron.mvc.mvc.unittest;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import patron.mvc.mvc.entity.Domicilio;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.service.PacienteService;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired private PacienteService pacienteService;
    @Test
    @Order(1)
    public void guardarPaciente(){
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

        assertNotNull(pacienteGuardado.getId());
        assertEquals(paciente.getNombre(), pacienteGuardado.getNombre());
    }

    @Test
    @Order(2)
    public void buscarPaciente(){
        Paciente pacienteBUscado = pacienteService.getPacienteById(1L);
        assertNotNull(pacienteBUscado);
    }

    @Test
    @Order(3)
    public void actualizarPaciente(){

        Paciente paciente=Paciente.builder()
                        .id(1L)
                .apellido("Bendezu Villena")
                .build();
        Paciente pacienteActualizado = pacienteService.updatePaciente(paciente);
        assertEquals(paciente.getApellido(), pacienteActualizado.getApellido());
    }
    @Test
    @Order(4)
    public void ListarTodosPacientes(){
        Domicilio domicilio = Domicilio.builder()
                .calle("Vencedroes")
                .numero(45)
                .localidad("Ayacucho")
                .provincia("Huamanga")
                .build();
        Paciente paciente=Paciente.builder().nombre("Cinthya")
                .apellido("Bolivia Bautista")
                .cedula("47112951")
                .fechaIngreso(LocalDate.of(2024,8,10))
                .email("pamela.07@gmail.com")
                .domicilio(domicilio)
                .build();
        pacienteService.savePaciente(paciente);
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        assertNotEquals(0, pacientes.size());
        assertEquals(2,pacientes.size());
    }

    @Test
    @Order(5)
    public void eliminarPaciente(){
        assertEquals(true,pacienteService.deletePaciente(1L));
    }


}
