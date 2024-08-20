package lk.ijse.posapi.bo;

import lk.ijse.posapi.dao.CustomerDAO;
import lk.ijse.posapi.dao.DAOFactory;
import lk.ijse.posapi.dto.CustomerDTO;
import lk.ijse.posapi.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDTO dto, Connection connection) throws SQLException {
        return customerDAO.save(new Customer(dto.getCustomerId(), dto.getCustomerName(), dto.getCustomerAddress(), dto.getCustomerTel()),connection);
    }

    @Override
    public boolean updateCustomer(String customerId, CustomerDTO customerDTO, Connection connection) {
        Customer customer = new Customer(customerId, customerDTO.getCustomerName(), customerDTO.getCustomerAddress(), customerDTO.getCustomerTel());
        return customerDAO.update(customerId, customer, connection);
    }
}
