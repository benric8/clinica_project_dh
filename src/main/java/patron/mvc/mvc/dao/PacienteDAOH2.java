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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class PacienteDAOH2 implements IDao<Paciente> {
    private static final String SQL_INSERT="INSERT INTO PACIENTES (NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID,EMAIL) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE Pacientes SET nombre = ?, apellido = ?, cedula = ?, fecha_ingreso = ?, domicilio_id = ?, email = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM Pacientes WHERE id = ?";
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
            pstmt.setString(6, paciente.getEmail());
            pstmt.executeUpdate();
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
    public Paciente actualizar(Paciente paciente) {
        log.info("Iniciando la operacion Actualizar Paciente");

        Paciente pacientebd = null;
        try {

            pacientebd = buscarPorId(paciente.getId());
            if (pacientebd != null) {
                pacientebd.setNombre(paciente.getNombre());
                pacientebd.setApellido(paciente.getApellido());
                pacientebd.setCedula(paciente.getCedula());
                pacientebd.setEmail(paciente.getEmail());
                pacientebd.setDomicilio(paciente.getDomicilio());
                pacientebd.setFechaIngreso(paciente.getFechaIngreso());
            } else {
                log.info("Paciente no encontrado");
            }

            Statement stmt = bd.getConnection().createStatement();
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_UPDATE);

            pstmt.setString(1, paciente.getNombre());
            pstmt.setString(2, paciente.getApellido());
            pstmt.setString(3, paciente.getCedula());
            pstmt.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            pstmt.setInt(5, paciente.getDomicilio().getId());
            pstmt.setString(6, paciente.getEmail());
            pstmt.setInt(7, paciente.getId());
            int isPacienteUpdates = pstmt.executeUpdate();
            if (isPacienteUpdates == 0) {
                log.info("Paciente no encontrado");
            } else {
                log.info("Paciente actualizado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return pacientebd;
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Iniciando la operacion Eliminar Paciente");
        Paciente pacientebd = null;
        try{
            pacientebd = buscarPorId(id);
            if (pacientebd != null) {
                PreparedStatement  preparedStatement = bd.getConnection().prepareStatement(SQL_DELETE);
                preparedStatement.setInt(1, id);
                int isDeleted=preparedStatement.executeUpdate();
                if (isDeleted == 0) {
                    log.info("Ocurrio un error al eliminar el Paciente");
                }else {
                    log.info("Paciente eliminado");
                }
            }else {
                log.info("Paciente no encontrado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        log.info("Iniciando la operacion de listar todos los pacientes");
        List<Paciente> pacientes = new ArrayList<>();
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Domicilio domicilio = domicilioDAO.buscarPorId(rs.getInt(6));
                pacientes.add(new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilio,
                        rs.getString(7)
                ));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return pacientes;
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
                        rs.getDate(5).toLocalDate(),domicilio,
                        rs.getString(7)
                        );
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return paciente;
    }

}
