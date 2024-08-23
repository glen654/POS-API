package lk.ijse.posapi.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posapi.bo.BOFactory;
import lk.ijse.posapi.bo.custom.OrderBO;
import lk.ijse.posapi.dto.OrderDTO;
import lk.ijse.posapi.dto.OrderDetailDTO;
import lk.ijse.posapi.dto.OrderRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class PlaceOrderController extends HttpServlet {
    OrderBO orderBO = (OrderBO) BOFactory.getBOFactory().getBo(BOFactory.BOTypes.ORDER);
    static Logger logger = LoggerFactory.getLogger(ItemController.class);
    Connection connection;

    @Override
    public void init() throws ServletException {
        logger.info("Initializing Place Order Controller with call inti method");
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
        //TODO:Save Order
        if(!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try {
            Jsonb jsonb = JsonbBuilder.create();
            OrderRequestDTO orderRequestDTO = jsonb.fromJson(req.getReader(), OrderRequestDTO.class);
            System.out.println("deserialized orderrequestdto:" + orderRequestDTO);

            if (orderRequestDTO == null || orderRequestDTO.getOrderDTO() == null || orderRequestDTO.getOrderDetailDTOS() == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid order data");
                return;
            }

            OrderDTO orderDTO = orderRequestDTO.getOrderDTO();
            List<OrderDetailDTO> orderDetailDTOs = orderRequestDTO.getOrderDetailDTOS();

            boolean orderPlaced = orderBO.placeOrder(orderDTO, orderDetailDTOs, connection);

            if (orderPlaced) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Order placed successfully");
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to place order");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
