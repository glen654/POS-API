package lk.ijse.posapi.bo.impl;

import lk.ijse.posapi.bo.custom.CustomerBO;
import lk.ijse.posapi.dao.custom.CustomerDAO;
import lk.ijse.posapi.dao.DAOFactory;
import lk.ijse.posapi.dto.CustomerDTO;
import lk.ijse.posapi.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CustomerDTO> getCustomer(Connection connection) throws SQLException {
        List<Customer> customers = customerDAO.get(connection);
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for(Customer customer : customers){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerTel(customer.getCustomerTel());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean deleteCustomer(String customerId, Connection connection) {
        return customerDAO.delete(customerId,connection);
    }
}
