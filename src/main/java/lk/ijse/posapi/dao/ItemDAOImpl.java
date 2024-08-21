package lk.ijse.posapi.dao;

import lk.ijse.posapi.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO{
    static String SAVE_ITEM = "INSERT INTO item (itemCode,itemName,qtyOnHand,unitPrice) VALUES (?,?,?,?)";
    @Override
    public boolean save(Item entity, Connection connection) throws SQLException {
        try {
            var preparedStatement = connection.prepareStatement(SAVE_ITEM);
            preparedStatement.setString(1,entity.getItemCode());
            preparedStatement.setString(2,entity.getItemName());
            preparedStatement.setInt(3,entity.getQtyOnHand());
            preparedStatement.setDouble(4,entity.getUnitPrice());
            return preparedStatement.executeUpdate() != 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(String id, Item entity, Connection connection) {
        return false;
    }

    @Override
    public Item get(String id, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }
}
