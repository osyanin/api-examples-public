package model.body;

import lombok.Data;

@Data
public class UpdateExchangeStatus {
    private Integer orderID;
    private Integer statusID;

    private Auth auth;

    public UpdateExchangeStatus(Integer orderID, Integer statusID, Auth auth) {
        this.orderID = orderID;
        this.statusID = statusID;
        this.auth = auth;
    }
}
