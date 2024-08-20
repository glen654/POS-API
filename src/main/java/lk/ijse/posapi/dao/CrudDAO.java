package lk.ijse.posapi.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T entity, Connection connection) throws SQLException;
    boolean update(String id,T entity,Connection connection);
    T get(String id, Connection connection) throws SQLException;
}
