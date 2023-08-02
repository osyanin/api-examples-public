package model.crypto;

import lombok.Data;

@Data
public class OrderID {
    private Integer orderID;

    public OrderID(Integer orderID) {
        this.orderID = orderID;
    }
}
