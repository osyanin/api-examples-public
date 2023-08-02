package model.cash.minimal;

import lombok.Data;

@Data
public class Order {
    private Integer orderID;

    public Order(Integer orderID) {
        this.orderID = orderID;
    }
}
