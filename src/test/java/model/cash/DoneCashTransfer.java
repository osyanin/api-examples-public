package model.cash;

import lombok.Data;

@Data
public class DoneCashTransfer {
    private Integer orderID;
    private Integer statusID;
    private String verifyCode;
    private String comment;

    public DoneCashTransfer(Integer orderID, Integer statusID, String verifyCode, String comment) {
        this.orderID = orderID;
        this.statusID = statusID;
        this.verifyCode = verifyCode;
        this.comment = comment;
    }
}
