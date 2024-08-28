package lk.ijse.posapi.bo.impl;

import lk.ijse.posapi.bo.custom.ItemBO;
import lk.ijse.posapi.dao.DAOFactory;
import lk.ijse.posapi.dao.custom.ItemDAO;
import lk.ijse.posapi.dto.ItemDTO;
import lk.ijse.posapi.entity.Item;
import lk.ijse.posapi.entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDao(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO dto, Connection connection) throws SQLException {
        return itemDAO.save(new Item(dto.getItemCode(), dto.getItemName(), dto.getQtyOnHand(), dto.getUnitPrice()), connection);
    }

    @Override
    public boolean updateItem(String itemCode, ItemDTO itemDTO, Connection connection) {
        Item item = new Item(itemCode, itemDTO.getItemName(), itemDTO.getQtyOnHand(), itemDTO.getUnitPrice());
        return itemDAO.update(itemCode, item, connection);
    }

    @Override
    public List<ItemDTO> getItem(Connection connection) throws SQLException {
        List<Item> items = itemDAO.get(connection);
        List<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : items) {
            ItemDTO itemDTO = new ItemDTO();
            itemDTO.setItemCode(item.getItemCode());
            itemDTO.setItemName(item.getItemName());
            itemDTO.setQtyOnHand(item.getQtyOnHand());
            itemDTO.setUnitPrice(item.getUnitPrice());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public boolean deleteItem(String itemCode, Connection connection) {
        return itemDAO.delete(itemCode, connection);
    }

    @Override
    public boolean updateItemQuantity(String itemCode, int quantity, Connection connection) throws SQLException {
        Item item = itemDAO.getItemByCode(itemCode,connection);

        if(item ==null) {
            throw new SQLException("Item not found with code: " + itemCode);
        }

        int newQuantity = item.getQtyOnHand() - quantity ;

        if(newQuantity< 0) {
            throw new SQLException("Insufficient stock for item code: " + itemCode);
        }

        return itemDAO.updateQuantity(itemCode,newQuantity,connection);
    }

}