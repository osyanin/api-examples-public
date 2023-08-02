package model.cash;

import lombok.Data;

@Data
public class InitCashTransferVerify {
    private Integer orderID;
    private String pinCode;

    public InitCashTransferVerify(Integer orderID, String pinCode) {
        this.orderID = orderID;
        this.pinCode = pinCode;
    }
}
