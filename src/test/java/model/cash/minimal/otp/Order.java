package model.cash.minimal.otp;

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
