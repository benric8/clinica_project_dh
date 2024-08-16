package patron.mvc.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import patron.mvc.mvc.dao.BD;
import patron.mvc.mvc.model.Paciente;
import patron.mvc.mvc.service.PacienteService;

@SpringBootTest
class MvcApplicationTests {

	@Autowired
	private PacienteService pacienteService;

	@Test
	void contextLoads() {
	}

	@Test
	public void pruebaBusquedaPaciente(){
		BD.crearTablas();

		Integer idABuscar=2;
		Paciente paciente= pacienteService.buscarPorId(idABuscar);
		Assertions.assertTrue(paciente.getApellido().equals("Vasquez"));

	}

}
