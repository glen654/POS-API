package lk.ijse.posapi.dao.impl;

import lk.ijse.posapi.dao.custom.OrderDAO;
import lk.ijse.posapi.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class OrderDAOImpl implements OrderDAO {
    static final String SAVE_ORDER = "INSERT INTO orders (orderId, orderDate, customerId, totalAmount) VALUES (?, ?, ?, ?)";
    @Override
    public boolean save(Order entity, Connection connection) throws SQLException {
        try {
            var preparedStatement = connection.prepareStatement(SAVE_ORDER);
            preparedStatement.setString(1,entity.getOrderId());
            preparedStatement.setDate(2, new java.sql.Date(entity.getOrderDate().getTime()));
            preparedStatement.setString(3,entity.getCustomerId());
            preparedStatement.setDouble(4,entity.getTotalAmount());
            return preparedStatement.executeUpdate() != 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(String id, Order entity, Connection connection) {
        return false;
    }

    @Override
    public List<Order> get(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }
}
