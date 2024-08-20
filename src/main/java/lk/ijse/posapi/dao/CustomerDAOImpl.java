package lk.ijse.posapi.dao;

import lk.ijse.posapi.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO{
    static String SAVE_CUSTOMER = "INSERT INTO customer (customerId,customerName,customerAddress,customerTel) VALUES (?,?,?,?)";
    static String UPDATE_CUSTOMER = "UPDATE customer SET customerName=?,customerAddress=?,customerTel=? WHERE customerId=?";
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

    @Override
    public boolean update(String id, Customer entity, Connection connection) {
        try {
            var preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER);
            preparedStatement.setString(1,entity.getCustomerName());
            preparedStatement.setString(2, entity.getCustomerAddress());
            preparedStatement.setString(3, entity.getCustomerTel());
            preparedStatement.setString(4,id);
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
