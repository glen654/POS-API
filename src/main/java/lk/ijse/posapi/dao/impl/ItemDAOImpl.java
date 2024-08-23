package lk.ijse.posapi.dao.impl;

import lk.ijse.posapi.dao.custom.ItemDAO;
import lk.ijse.posapi.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemDAOImpl implements ItemDAO {
    static String SAVE_ITEM = "INSERT INTO item (itemCode,itemName,qtyOnHand,unitPrice) VALUES (?,?,?,?)";
    static String UPDATE_ITEM = "UPDATE item SET itemName=?,qtyOnHand=?,unitPrice=? WHERE itemCode=?";
    static String GET_ITEM = "SELECT * FROM item WHERE itemCode=?";
    static String DELETE_ITEM = "DELETE FROM item WHERE itemCode=?";
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
        try {
            var preparedStatement = connection.prepareStatement(UPDATE_ITEM);
            preparedStatement.setString(1,entity.getItemName());
            preparedStatement.setInt(2, entity.getQtyOnHand());
            preparedStatement.setDouble(3, entity.getUnitPrice());
            preparedStatement.setString(4,id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item get(String id, Connection connection) throws SQLException {
        var item = new Item();
        try {
            var preparedStatement = connection.prepareStatement(GET_ITEM);
            preparedStatement.setString(1,id);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                item.setItemCode(resultSet.getString("itemCode"));
                item.setItemName(resultSet.getString("itemName"));
                item.setQtyOnHand(resultSet.getInt("qtyOnHand"));
                item.setUnitPrice(resultSet.getDouble("unitPrice"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        try {
            var preparedStatement = connection.prepareStatement(DELETE_ITEM);
            preparedStatement.setString(1,id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
