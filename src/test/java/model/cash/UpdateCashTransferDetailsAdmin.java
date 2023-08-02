package model.cash;

import lombok.Data;

@Data
public class UpdateCashTransferDetailsAdmin {
    private Integer orderID;
    private Integer vaultID;
    private Integer meetTime;
    private Integer meetTimeFrom;
    private Integer meetTimeTo;
    private String comment;

    public UpdateCashTransferDetailsAdmin(Integer orderID, Integer vaultID, Integer meetTime, Integer meetTimeFrom, Integer meetTimeTo, String comment) {
        this.orderID = orderID;
        this.vaultID = vaultID;
        this.meetTime = meetTime;
        this.meetTimeFrom = meetTimeFrom;
        this.meetTimeTo = meetTimeTo;
        this.comment = comment;
    }
}
