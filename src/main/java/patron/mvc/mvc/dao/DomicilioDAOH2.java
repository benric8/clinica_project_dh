package patron.mvc.mvc.dao;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patron.mvc.mvc.model.Domicilio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DomicilioDAOH2 implements IDao<Domicilio>{
    private static final String SQL_INSERT="INSERT INTO DOMICILIOS VALUES(?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_UPDATE = "UPDATE DOMICILIOS SET calle = ?, numero = ?, localidad = ?, provincia = ? WHERE ID = ?";
    private static final String SQL_DELETE = "DELETE FROM DOMICILIOS WHERE id = ?";
    private static final String SQL_SELECT="SELECT * FROM DOMICILIOS";
    private final BD bd;

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        log.info("Iniciando la operacion Guardar domicilio");
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, domicilio.getCalle());
            pstmt.setInt(2, domicilio.getNumero());
            pstmt.setString(3, domicilio.getLocalidad());
            pstmt.setString(4, domicilio.getProvincia());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            while (rs.next()) {
                domicilio.setId(rs.getInt(1));
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return domicilio;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        log.info("Iniciando la operacion Actualizar Domicilio");

        Domicilio domiciliobd = null;
        try {

            domiciliobd = buscarPorId(domicilio.getId());
            if (domiciliobd != null) {
                domiciliobd.setCalle(domicilio.getCalle());
                domiciliobd.setNumero(domicilio.getNumero());
                domiciliobd.setLocalidad(domicilio.getLocalidad());
                domiciliobd.setProvincia(domicilio.getProvincia());
            } else {
                log.info("Domicilio no encontrado");
            }

            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_UPDATE);

            pstmt.setString(1, domicilio.getCalle());
            pstmt.setInt(2, domicilio.getNumero());
            pstmt.setString(3, domicilio.getLocalidad());
            pstmt.setString(4,domicilio.getProvincia());
            pstmt.setInt(5, domicilio.getId());
            int isDomicilioUpdates = pstmt.executeUpdate();
            if (isDomicilioUpdates == 0) {
                log.info("Domicilio no encontrado");
            } else {
                log.info("Domicilio actualizado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return domiciliobd;
        
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Iniciando la operacion Eliminar Domicilio");
        Domicilio domiciliobd = null;
        try{
            domiciliobd = buscarPorId(id);
            if (domiciliobd != null) {
                PreparedStatement  preparedStatement = bd.getConnection().prepareStatement(SQL_DELETE);
                preparedStatement.setInt(1, id);
                int isDeleted=preparedStatement.executeUpdate();
                if (isDeleted == 0) {
                    log.info("Ocurrio un error al eliminar el Domicilio");
                }else {
                    log.info("Domicilio eliminado");
                }
            }else {
                log.info("Domicilio no encontrado");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<Domicilio> listarTodos() {
        log.info("Iniciando la operacion de listar todos los domicilios");
        List<Domicilio> domicilios = new ArrayList<>();
        try {
            PreparedStatement pstmt = bd.getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
               
                domicilios.add(new Domicilio(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)
                ));
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return domicilios;
        
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        log.info("Inicio de operacion: buscar Domicilio por id {}",id);
        Domicilio domicilio = null;
        try{
            PreparedStatement psSelectOne = bd.getConnection().prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1, id);
            ResultSet rs = psSelectOne.executeQuery();
            while (rs.next()) {
                domicilio = new Domicilio(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5));
            }

        } catch (Exception e) {
            log.error("Problemas con la BD",e.getMessage());
        }
        return domicilio;
    }
}
