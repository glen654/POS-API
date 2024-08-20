package lk.ijse.posapi.bo;

import lk.ijse.posapi.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerBO extends SuperBO{
    boolean saveCustomer(CustomerDTO dto, Connection connection) throws SQLException;

    boolean updateCustomer(String customerId,CustomerDTO customerDTO,Connection connection);
    CustomerDTO getCustomer(String customerId, Connection connection) throws SQLException;
    boolean deleteCustomer(String customerId,Connection connection);
}
