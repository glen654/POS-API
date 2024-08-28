package lk.ijse.posapi.dao.custom;

import lk.ijse.posapi.dao.CrudDAO;
import lk.ijse.posapi.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateQuantity(String itemCode, int quantity, Connection connection) throws SQLException;
    Item getItemByCode(String itemCode, Connection connection) throws SQLException;
}
