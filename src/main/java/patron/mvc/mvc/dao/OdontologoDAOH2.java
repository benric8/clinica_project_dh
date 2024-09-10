package patron.mvc.mvc.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patron.mvc.mvc.model.Domicilio;
import patron.mvc.mvc.model.Odontologo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class OdontologoDAOH2 implements IDao<Odontologo>{

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (NOMBRE, APELLIDO, MATRICULA) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE ODONTOLOGOS SET nombre = ?, apellido = ?, MATRICULA = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM ODONTOLOGOS WHERE id = ?";
    private static final String SQL_SELECT="SELECT * FROM ODONTOLOGOS";

    private final BD bd;
    
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        log.info("Iniciando la operacion Guardar odontologo");
        try {

            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, odontologo.getNombre());
            pstmt.setString(2, odontologo.getApellido());
            pstmt.setString(3, odontologo.getMatricula());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        log.info("Iniciando la operacion Actualizar odontologo");

        Odontologo odontologobd = null;
        try {

            odontologobd = buscarPorId(odontologo.getId());
            if (odontologobd != null) {
                odontologobd.setNombre(odontologo.getNombre());
                odontologobd.setApellido(odontologo.getApellido());
                odontologobd.setMatricula(odontologo.getMatricula());
            } else {
                log.info("odontologo no encontrado");
            }

            Statement stmt = bd.getConnection().createStatement();
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_UPDATE);

            pstmt.setString(1, odontologo.getNombre());
            pstmt.setString(2, odontologo.getApellido());
            pstmt.setString(3, odontologo.getMatricula());

            pstmt.setInt(4, odontologo.getId());
            int isodontologoUpdates = pstmt.executeUpdate();
            if (isodontologoUpdates == 0) {
                log.info("odontologo no encontrado");
            } else {
                log.info("odontologo actualizado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return odontologobd;
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Iniciando la operacion Eliminar odontologo");
        Odontologo odontologobd = null;
        try{
            odontologobd = buscarPorId(id);
            if (odontologobd != null) {
                PreparedStatement  preparedStatement = bd.getConnection().prepareStatement(SQL_DELETE);
                preparedStatement.setInt(1, id);
                int isDeleted=preparedStatement.executeUpdate();
                if (isDeleted == 0) {
                    log.info("Ocurrio un error al eliminar el odontologo");
                }else {
                    log.info("odontologo eliminado");
                }
            }else {
                log.info("odontologo no encontrado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        log.info("Iniciando la operacion de listar todos los odontologos");
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                odontologos.add(new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        log.info("Iniciando la operacion Buscar odontologo: ",id);
        Odontologo odontologo = null;
        Domicilio domicilio = null;
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_SELECT_ONE);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return odontologo;
    }




}
