package lk.ijse.posapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    private String cusId;
    private String cusName;
    private String cusAddress;
    private String cusTel;
}
