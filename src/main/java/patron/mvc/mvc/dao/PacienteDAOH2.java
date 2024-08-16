package patron.mvc.mvc.dao;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patron.mvc.mvc.model.Domicilio;
import patron.mvc.mvc.model.Paciente;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PacienteDAOH2 implements IDao<Paciente> {
    private static final String SQL_INSERT="INSERT INTO PACIENTES VALUES(?,?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT="SELECT * FROM PACIENTES";

    private final BD bd;
    private final DomicilioDAOH2 domicilioDAO;





    @Override
    public Paciente guardar(Paciente paciente) {
        log.info("Iniciando la operacion Guardar PAciente");
        try {
            Statement stmt = bd.getConnection().createStatement();
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellido());
            pstmt.setString(3, paciente.getCedula());
            pstmt.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            pstmt.setInt(5,paciente.getDomicilio().getId());

            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return List.of();
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        log.info("Iniciando la operacion Buscar Paciente: ",id);
        Paciente paciente = null;
        Domicilio domicilio = null;
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                domicilio = domicilioDAO.buscarPorId(rs.getInt(6));
                paciente = new Paciente(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilio
                        );
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return paciente;
    }

}
