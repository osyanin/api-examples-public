package model.cash;

import lombok.Data;

@Data
public class UpdateCashTransferStatus {
    private Integer orderID;
    private Integer statusID;
    private String comment;

    public UpdateCashTransferStatus(Integer orderID, Integer statusID, String comment) {
        this.orderID = orderID;
        this.statusID = statusID;
        this.comment = comment;
    }
}
