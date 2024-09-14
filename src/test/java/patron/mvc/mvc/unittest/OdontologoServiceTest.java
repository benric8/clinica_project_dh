package patron.mvc.mvc.unittest;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import patron.mvc.mvc.entity.Odontologo;
import patron.mvc.mvc.service.OdontologoService;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;
    @Test
    @Order(1)
    public void guardarOdontologo(){

        Odontologo odontologo=Odontologo.builder().nombre("Richard")
                .nombre("Gabriel")
                .apellido("Castillo")
                .matricula("01242")
                .build();

        Odontologo odontologoGuardado = odontologoService.saveOdontologo(odontologo);

        assertNotNull(odontologoGuardado.getId());
        assertEquals(odontologo.getNombre(), odontologoGuardado.getNombre());
    }

    @Test
    @Order(2)
    public void buscarOdontologo(){
        Odontologo odontologoBUscado = odontologoService.getOdontologoById(1L);
        assertNotNull(odontologoBUscado);
    }

    @Test
    @Order(3)
    public void actualizarOdontologo(){

        Odontologo odontologo=Odontologo.builder()
                .id(1L)
                .apellido("Castillo Gomez")
                .matricula("0202401")
                .build();
        Odontologo odontologoActualizado = odontologoService.updateOdontologo(odontologo);
        assertEquals(odontologo.getApellido(), odontologoActualizado.getApellido());
    }
    @Test
    @Order(4)
    public void ListarTodosOdontologos(){

        Odontologo odontologo=Odontologo.builder().nombre("Cinthya")
                .nombre("Lisbeth")
                .apellido("Ramiro Quezada")
                .matricula("0202402")
                .build();
        odontologoService.saveOdontologo(odontologo);
        List<Odontologo> odontologos = odontologoService.getAllOdontologos();
        assertNotEquals(0, odontologos.size());
        assertEquals(2,odontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo(){
        assertEquals(true,odontologoService.deleteOdontologo(1L));
    }
}
