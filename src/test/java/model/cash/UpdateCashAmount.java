package model.cash;

import lombok.Data;

@Data
public class UpdateCashAmount {
    private Integer orderID;
    private Integer Amount;
    private Integer StatusID;
    private String comment;
    private Auth auth;

    public UpdateCashAmount(Integer orderID, Integer Amount, Integer StatusID, String comment, Auth auth) {
        this.orderID = orderID;
        this.Amount = Amount;
        this.StatusID = StatusID;
        this.comment = comment;
        this.auth = auth;
    }
}