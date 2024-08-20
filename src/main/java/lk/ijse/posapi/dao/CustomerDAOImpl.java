package lk.ijse.posapi.dao;

import lk.ijse.posapi.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO{
    static String SAVE_CUSTOMER = "INSERT INTO customer (cusId,cusName,cusAddress,cusTel) VALUES (?,?,?,?)";
    @Override
    public boolean save(Customer entity, Connection connection) throws SQLException {
        try {
            var preparedStatement = connection.prepareStatement(SAVE_CUSTOMER);
            preparedStatement.setString(1,entity.getCustomerId());
            preparedStatement.setString(2,entity.getCustomerName());
            preparedStatement.setString(3,entity.getCustomerAddress());
            preparedStatement.setString(4,entity.getCustomerTel());
            return preparedStatement.executeUpdate() != 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
