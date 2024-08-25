package lk.ijse.posapi.bo.custom;

import lk.ijse.posapi.bo.SuperBO;
import lk.ijse.posapi.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDTO dto, Connection connection) throws SQLException;

    boolean updateCustomer(String customerId,CustomerDTO customerDTO,Connection connection);
    List<CustomerDTO> getCustomer(Connection connection) throws SQLException;
    boolean deleteCustomer(String customerId,Connection connection);
}
