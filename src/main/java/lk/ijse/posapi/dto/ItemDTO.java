package lk.ijse.posapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemCode;
    private String itemName;
    private int qtyOnHand;
    private double unitPrice;
}
