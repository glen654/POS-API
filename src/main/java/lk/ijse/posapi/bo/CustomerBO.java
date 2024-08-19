package lk.ijse.posapi.bo;

import lk.ijse.posapi.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomerBO extends SuperBO{
    boolean saveCustomer(CustomerDTO dto, Connection connection) throws SQLException;
}
