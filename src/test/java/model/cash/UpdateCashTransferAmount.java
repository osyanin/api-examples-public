package model.cash;

import lombok.Data;
import model.cash.minimal.otp.Auth;

@Data
public class UpdateCashTransferAmount {
    private Integer orderID;
    private Integer Amount;
    private Integer StatusID;
    private String comment;
    private Auth auth;

    public UpdateCashTransferAmount(Integer orderID, Integer Amount, Integer StatusID, String comment, Auth auth) {
        this.orderID = orderID;
        this.Amount = Amount;
        this.StatusID = StatusID;
        this.comment = comment;
        this.auth = auth;
    }
}
