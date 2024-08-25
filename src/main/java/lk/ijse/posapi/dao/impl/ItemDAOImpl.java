package lk.ijse.posapi.dao.impl;

import lk.ijse.posapi.dao.custom.ItemDAO;
import lk.ijse.posapi.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    static String SAVE_ITEM = "INSERT INTO item (itemCode,itemName,qtyOnHand,unitPrice) VALUES (?,?,?,?)";
    static String UPDATE_ITEM = "UPDATE item SET itemName=?,qtyOnHand=?,unitPrice=? WHERE itemCode=?";
    static String GET_ITEM = "SELECT * FROM item";
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
    public List<Item> get(Connection connection) throws SQLException {
        List<Item> items = new ArrayList<>();

        try {
            var preparedStatement = connection.prepareStatement(GET_ITEM);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                var item = new Item();
                item.setItemCode(resultSet.getString("itemCode"));
                item.setItemName(resultSet.getString("itemName"));
                item.setQtyOnHand(resultSet.getInt("qtyOnHand"));
                item.setUnitPrice(resultSet.getDouble("unitPrice"));
                items.add(item);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return items;
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
