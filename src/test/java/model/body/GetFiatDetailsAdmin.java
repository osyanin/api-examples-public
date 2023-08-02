package model.body;

import lombok.*;

@Data
public class GetFiatDetailsAdmin {
    private Integer orderID;

    public GetFiatDetailsAdmin(Integer orderID) {
        this.orderID = orderID;
    }

}
