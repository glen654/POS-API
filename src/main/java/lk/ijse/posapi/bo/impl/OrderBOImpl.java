package lk.ijse.posapi.bo.impl;

import lk.ijse.posapi.bo.custom.OrderBO;
import lk.ijse.posapi.dao.DAOFactory;
import lk.ijse.posapi.dao.custom.OrderDAO;
import lk.ijse.posapi.dao.custom.OrderDetailDAO;
import lk.ijse.posapi.dto.OrderDTO;
import lk.ijse.posapi.dto.OrderDetailDTO;
import lk.ijse.posapi.entity.Order;
import lk.ijse.posapi.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ORDER_DETAIL);
    @Override
    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTOs, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);

            // Save the order
            boolean orderSaved = orderDAO.save(
                    new Order(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId(), orderDTO.getTotalAmount()),
                    connection);
            if (!orderSaved) {
                connection.rollback();
                return false;
            }

            // Save the order details
            for (OrderDetailDTO orderDetail : orderDetailDTOs) {
                boolean orderDetailSaved = orderDetailDAO.save(
                        new OrderDetail(orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getOrderQty(),orderDetail.getUnitPrice()),
                        connection);
                if (!orderDetailSaved) {
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
