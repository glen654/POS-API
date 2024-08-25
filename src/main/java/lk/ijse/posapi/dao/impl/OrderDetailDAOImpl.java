package lk.ijse.posapi.dao.impl;

import lk.ijse.posapi.dao.custom.OrderDetailDAO;
import lk.ijse.posapi.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    static final String SAVE_ORDER_DETAIL = "INSERT INTO order_details (orderId, itemCode, orderQty, unitPrice) VALUES (?, ?, ?, ?)";
    @Override
    public boolean save(OrderDetail entity, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ORDER_DETAIL)) {
            preparedStatement.setString(1, entity.getOrderId());
            preparedStatement.setString(2, entity.getItemCode());
            preparedStatement.setInt(3, entity.getOrderQty());
            preparedStatement.setDouble(4, entity.getUnitPrice());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(String id, OrderDetail entity, Connection connection) {
        return false;
    }

    @Override
    public List<OrderDetail> get(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String id, Connection connection) {
        return false;
    }
}
