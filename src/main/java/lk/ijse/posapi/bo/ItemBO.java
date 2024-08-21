package lk.ijse.posapi.bo;

import lk.ijse.posapi.dto.CustomerDTO;
import lk.ijse.posapi.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemBO extends SuperBO{
    boolean saveItem(ItemDTO dto, Connection connection) throws SQLException;

    boolean updateItem(String itemCode,ItemDTO itemDTO,Connection connection);
    ItemDTO getItem(String itemCode, Connection connection) throws SQLException;
    boolean deleteItem(String itemCode,Connection connection);
}
