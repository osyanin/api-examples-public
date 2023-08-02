package model.cash;

import lombok.Data;

@Data
public class UpdateCashTransferExecution {
    private Integer orderID;
    private Integer statusID;
    private String otpCode;
    private String comment;

    public UpdateCashTransferExecution(Integer orderID, Integer statusID, String otpCode, String comment) {
        this.orderID = orderID;
        this.statusID = statusID;
        this.otpCode = otpCode;
        this.comment = comment;
    }
}
