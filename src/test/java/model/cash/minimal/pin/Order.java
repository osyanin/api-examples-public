package model.cash.minimal.pin;

import lombok.Data;

@Data
public class Order {
    private Integer orderID;
    private Auth auth;

    public Order(Integer orderID, Auth auth) {
        this.orderID = orderID;
        this.auth = auth;
    }
}
