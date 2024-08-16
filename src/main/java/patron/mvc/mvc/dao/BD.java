package patron.mvc.mvc.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
@Slf4j
@Component
public class BD {
    public static void crearTablas(){
        Connection con = null;
        Statement st = null;
        try {
            con = getConnection();
            st = con.createStatement();
            st.execute(Querys.SQL_DROP_CREATE_DOMICILIOS.getSql());
            st.execute(Querys.SQL_DROP_CREATE_PACIENTES.getSql());
            st.execute(Querys.SQL_DROP_PRUEBA.getSql());
        }catch (Exception e) {
            log.info("error inesperado: {}",e.getMessage());
        }
    }


    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/ClinicaOdontologicaC2","sa","sa");
    }
}
