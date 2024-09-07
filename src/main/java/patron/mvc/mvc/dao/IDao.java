package patron.mvc.mvc.dao;

import java.util.List;

public interface IDao<T> {
    T guardar(T t);
    T actualizar(T t);
    void eliminar(Integer id);
    List<T> listarTodos();
    T buscarPorId(Integer id);
}
