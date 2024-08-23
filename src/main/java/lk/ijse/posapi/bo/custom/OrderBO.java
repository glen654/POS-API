package lk.ijse.posapi.bo.custom;

import lk.ijse.posapi.bo.SuperBO;
import lk.ijse.posapi.dto.OrderDTO;
import lk.ijse.posapi.dto.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetailDTOs, Connection connection) throws SQLException;
}
