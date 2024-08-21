package lk.ijse.posapi.bo;

import lk.ijse.posapi.dao.DAOFactory;
import lk.ijse.posapi.dao.ItemDAO;
import lk.ijse.posapi.dto.ItemDTO;
import lk.ijse.posapi.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemBOImpl implements ItemBO{
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean saveItem(ItemDTO dto, Connection connection) throws SQLException {
        return itemDAO.save(new Item(dto.getItemCode(), dto.getItemName(), dto.getQtyOnHand(),dto.getUnitPrice()),connection);
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
