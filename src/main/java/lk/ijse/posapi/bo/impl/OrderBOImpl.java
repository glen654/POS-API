package lk.ijse.posapi.bo.impl;

import lk.ijse.posapi.bo.BOFactory;
import lk.ijse.posapi.bo.custom.ItemBO;
import lk.ijse.posapi.bo.custom.OrderBO;
import lk.ijse.posapi.controller.CustomerController;
import lk.ijse.posapi.dao.DAOFactory;
import lk.ijse.posapi.dao.custom.ItemDAO;
import lk.ijse.posapi.dao.custom.OrderDAO;
import lk.ijse.posapi.dao.custom.OrderDetailDAO;
import lk.ijse.posapi.dto.OrderDTO;
import lk.ijse.posapi.dto.OrderDetailDTO;
import lk.ijse.posapi.dto.OrderRequestDTO;
import lk.ijse.posapi.entity.Order;
import lk.ijse.posapi.entity.OrderDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ORDER_DETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ITEM);
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Override
    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTOs, Connection connection) throws SQLException {
        logger.info("Inside of the place order method bo layer");
        try {
            connection.setAutoCommit(false);

            // Save the order
            boolean orderSaved = orderDAO.save(
                    new Order(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId(), orderDTO.getTotalAmount()),
                    connection);
            if (!orderSaved) {
                logger.error("Data no saved to the order table");
                connection.rollback();
                return false;
            }

            // Save the order details
            for (OrderDetailDTO orderDetail : orderDetailDTOs) {
                boolean orderDetailSaved = orderDetailDAO.save(
                        new OrderDetail(orderDetail.getOrderId(),orderDetail.getItemCode(),orderDetail.getOrderQty(),orderDetail.getUnitPrice()),
                        connection);
                if (!orderDetailSaved) {
                    logger.error("Data not saved to the order details table");
                    connection.rollback();
                    return false;
                }

                //Update Item Quantity
                boolean itemQuantityUpdated = itemDAO.updateQuantity(orderDetail.getItemCode(),
                        orderDetail.getOrderQty(),connection);
                if(!itemQuantityUpdated){
                    logger.error("Quantity not updated on item table");
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

    @Override
    public List<OrderRequestDTO> getAllOrdersWithDetails(Connection connection) throws SQLException {
        return orderDetailDAO.getAllOrderDetails(connection);
    }
}
