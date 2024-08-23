package lk.ijse.posapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    private String orderId;
    private String itemCode;
    private int orderQty;
    private double unitPrice;
}
