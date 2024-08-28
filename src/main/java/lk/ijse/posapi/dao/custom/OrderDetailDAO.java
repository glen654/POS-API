package lk.ijse.posapi.dao.custom;

import lk.ijse.posapi.dao.CrudDAO;
import lk.ijse.posapi.dto.OrderRequestDTO;
import lk.ijse.posapi.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
     List<OrderRequestDTO> getAllOrderDetails(Connection connection) throws SQLException;

}
