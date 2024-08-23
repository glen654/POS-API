package lk.ijse.posapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail implements Serializable {
    private String orderId;
    private String itemCode;
    private int orderQty;
    private double unitPrice;
}
