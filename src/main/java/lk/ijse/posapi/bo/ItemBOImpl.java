package lk.ijse.posapi.bo;

import lk.ijse.posapi.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemBOImpl implements ItemBO{
    @Override
    public boolean saveItem(ItemDTO dto, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean updateItem(String itemCode, ItemDTO itemDTO, Connection connection) {
        return false;
    }

    @Override
    public ItemDTO getItem(String itemCode, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean deleteItem(String itemCode, Connection connection) {
        return false;
    }
}
