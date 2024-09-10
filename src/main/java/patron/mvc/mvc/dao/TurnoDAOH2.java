package patron.mvc.mvc.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patron.mvc.mvc.model.Odontologo;
import patron.mvc.mvc.model.Paciente;
import patron.mvc.mvc.model.Turno;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Component
public class TurnoDAOH2 implements IDao<Turno>{

    private static final String SQL_INSERT="INSERT INTO TURNOS VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM TURNOS WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE TURNOS SET calle = ?, numero = ?, localidad = ?, provincia = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM TURNOS WHERE id = ?";
    private static final String SQL_SELECT="SELECT * FROM TURNOS";

    private final BD bd;
    private final PacienteDAOH2 pacienteDAO;
    private final OdontologoDAOH2 odontologoDAOH2;

    @Override
    public Turno guardar(Turno turno) {
        log.info("Iniciando la operacion Guardar Turno");
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, turno.getPaciente().getId());
            pstmt.setInt(2, turno.getOdontologo().getId());
            pstmt.setDate(3, Date.valueOf(turno.getFecha()));
            
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                turno.setId(rs.getInt(1));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return turno;
    }

    @Override
    public Turno actualizar(Turno turno) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Turno> listarTodos() {
        log.info("Iniciando la operacion de listar todos los turnos");
        List<Turno> turnos = new ArrayList<>();
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                Paciente paciente = pacienteDAO.buscarPorId(rs.getInt(2));
                Odontologo odontologo = odontologoDAOH2.buscarPorId(rs.getInt(3));
                turnos.add(new Turno(
                        rs.getInt(1),
                        paciente,
                        odontologo,
                        rs.getDate(4).toLocalDate()
                ));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return turnos;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        return null;
    }
}
