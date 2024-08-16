package patron.mvc.mvc.dao;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import patron.mvc.mvc.model.Domicilio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DomicilioDAOH2 implements IDao<Domicilio>{
    private static final String SQL_INSERT="INSERT INTO DOMICILIOS VALUES(?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM DOMICILIOS WHERE ID=?";

    private final BD bd;

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        log.info("Inicio de operacion: guardar Domicilio",domicilio.getId());
        try {

        }catch (Exception e) {
            log.error("Problemas con la BD",e.getMessage());
        }
        return null;
    }

    @Override
    public void actualizar(Domicilio domicilio) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Domicilio> listarTodos() {
        return List.of();
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        log.info("Inicio de operacion: buscar Domicilio por id",id);
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
        return null;
    }
}
