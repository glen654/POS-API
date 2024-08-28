package lk.ijse.posapi.controller;

import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posapi.bo.BOFactory;
import lk.ijse.posapi.bo.custom.ItemBO;
import lk.ijse.posapi.dto.ItemDTO;
import lk.ijse.posapi.util.UtilProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBo(BOFactory.BOTypes.ITEM);
    static Logger logger = LoggerFactory.getLogger(ItemController.class);
    Connection connection;

    @Override
    public void init() throws ServletException {
        logger.info("Initializing Item Controller with call inti method");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/posSystem");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            logger.error("Init failed with", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:Save Item
        logger.info("Inside of the Item save method");
        if(!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try(var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO itemDTO = jsonb.fromJson(req.getReader(),ItemDTO.class);
            itemDTO.setItemCode(UtilProcess.generateItemId());
            if(itemBO.saveItem(itemDTO,connection)){
                writer.write("Item Save Successful");
                logger.info("Item saved successful");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                writer.write("Item Save Unsuccessful");
                logger.error("Item save unsuccessful");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (JsonException | SQLException e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:Get Item
        logger.info("Inside of the item get method");
        try(var writer = resp.getWriter()) {
            List<ItemDTO> item = itemBO.getItem(connection);
            resp.setContentType("application/json");
            var jsonb = JsonbBuilder.create();
            jsonb.toJson(item,writer);
        } catch (SQLException e) {
            logger.error("Item get unsuccessful");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:Update Item
        logger.info("Inside of the item update method");
        if(!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try(var writer = resp.getWriter()){
            var itemCode = req.getParameter("itemCode");
            Jsonb jsonb = JsonbBuilder.create();
            var updateItem = jsonb.fromJson(req.getReader(), ItemDTO.class);
            if(itemBO.updateItem(itemCode,updateItem,connection)){
                writer.write("Item update successful");
                logger.info("Item updated successful");
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }else{
                writer.write("Item Update Failed");
                logger.error("item update unsuccessful");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (JsonException e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO:Delete Item
        logger.info("Inside of the item delete method");
        var itemCode = req.getParameter("itemCode");
        try(var writer = resp.getWriter()){
            if(itemBO.deleteItem(itemCode,connection)){
                writer.write("Delete Successful");
                logger.info("Item delete successful");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                logger.error("Item delete unsuccessful");
                writer.write("Delete failed");
            }
        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e);
        }
    }
}
