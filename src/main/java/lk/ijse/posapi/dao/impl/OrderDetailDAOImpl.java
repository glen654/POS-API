package lk.ijse.posapi.dao.impl;

import lk.ijse.posapi.dao.custom.OrderDetailDAO;
import lk.ijse.posapi.dto.OrderDTO;
import lk.ijse.posapi.dto.OrderDetailDTO;
import lk.ijse.posapi.dto.OrderRequestDTO;
import lk.ijse.posapi.entity.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    static String SAVE_ORDER_DETAIL = "INSERT INTO order_details (orderId, itemCode, orderQty, unitPrice) VALUES (?, ?, ?, ?)";
    static String GET_ORDER_DETAILS = "SELECT o.orderId, o.orderDate, o.customerId, o.totalAmount, " +
            "od.itemCode, od.orderQty, od.unitPrice " +
            "FROM orders o " +
            "JOIN order_details od ON o.orderId = od.orderId";
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

    @Override
    public List<OrderRequestDTO> getAllOrderDetails(Connection connection) throws SQLException {
        List<OrderRequestDTO> orderList = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_ORDER_DETAILS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String orderId = rs.getString("orderId");
                LocalDate orderDate = rs.getDate("orderDate").toLocalDate();
                String customerId = rs.getString("customerId");
                double totalAmount = rs.getDouble("totalAmount");
                String itemCode = rs.getString("itemCode");
                int orderQty = rs.getInt("orderQty");
                double unitPrice = rs.getDouble("unitPrice");

                OrderDTO orderDTO = new OrderDTO(orderId, orderDate, customerId, totalAmount);
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId, itemCode, orderQty, unitPrice);

                OrderRequestDTO orderRequestDTO = new OrderRequestDTO(orderDTO, List.of(orderDetailDTO));

                orderList.add(orderRequestDTO);
            }
        }

        return orderList;
    }
}
