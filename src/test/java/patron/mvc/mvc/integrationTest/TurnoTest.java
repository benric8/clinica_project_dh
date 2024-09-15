package patron.mvc.mvc.integrationTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import patron.mvc.mvc.dto.TurnoDTO;
import patron.mvc.mvc.entity.Domicilio;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.entity.Paciente;
import patron.mvc.mvc.entity.Turno;
import patron.mvc.mvc.service.OdontologoService;
import patron.mvc.mvc.service.PacienteService;
import patron.mvc.mvc.service.TurnoService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnoTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarTurnos() {
        Paciente paciente1 = pacienteService.savePaciente(
                Paciente.builder()
                        .nombre("Matias")
                        .apellido("Santos")
                        .cedula("111111")
                        .fechaIngreso(LocalDate.of(2024, 9, 12))
                        .domicilio(Domicilio.builder()
                                .calle("Calle 1")
                                .numero(122)
                                .localidad("Uruguay")
                                .provincia("Montevideo")
                                .build())
                        .email("matiassantos@digitalhouse.com")
                        .build()
        );

        Paciente paciente2 = pacienteService.savePaciente(
                Paciente.builder()
                        .nombre("Helen")
                        .apellido("Vasquez")
                        .cedula("1112221")
                        .fechaIngreso(LocalDate.of(2024, 9, 12))
                        .domicilio(Domicilio.builder()
                                .calle("Calle 2")
                                .numero(122)
                                .localidad("Peru")
                                .provincia("Lima")
                                .build())
                        .email("helenvasquez@digitalhouse.com")
                        .build()
        );

        Odontologo odontologo1 = odontologoService.saveOdontologo(
                Odontologo.builder()
                        .nombre("Juan")
                        .apellido("Maldonado")
                        .matricula("MP10")
                        .build()
        );

        Odontologo odontologo2 = odontologoService.saveOdontologo(
                Odontologo.builder()
                        .nombre("Daniela")
                        .apellido("Paz")
                        .matricula("MP20")
                        .build()
        );

        Turno turno1 = turnoService.saveTurno(
                TurnoDTO.builder()
                        .pacienteId(paciente1.getId())
                        .odontologoId(odontologo1.getId())
                        .fecha(LocalDate.of(2024, 11, 12))
                        .build()
        );

        Turno turno2 = turnoService.saveTurno(
                TurnoDTO.builder()
                        .pacienteId(paciente2.getId())
                        .odontologoId(odontologo2.getId())
                        .fecha(LocalDate.of(2024, 12, 20))
                        .build()
        );
    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarTurnos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());

    }
    @Test
    public void listarTurnoPorOdontologo() throws Exception{
        cargarTurnos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                        .param("idOdontologo", String.valueOf(1L))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void listarTurnoPorPaciente() throws Exception{
        cargarTurnos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                        .param("idPaciente", String.valueOf(2L))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());
    }
}
