package patron.mvc.mvc.dao;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> listarTodos();
    T buscarPorId(Integer id);
}
