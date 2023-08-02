package model.cash;

import lombok.Data;

@Data
public class CreateCashTransfer {
    private Integer amount;
    private Integer vaultID;
    private Integer directionID;
    private Integer walletID;
    private Integer meetTimeFrom;
    private Integer meetTimeTo;
    private String comment;
    private Integer[] banknotes;
    private Auth auth;
    private boolean isCourier;

    public CreateCashTransfer(Integer amount, Integer vaultID, Integer directionID, Integer walletID, Integer meetTimeFrom, Integer meetTimeTo, String comment, Integer[] banknotes, Auth auth, boolean isCourier) {
        this.amount = amount;
        this.vaultID = vaultID;
        this.directionID = directionID;
        this.walletID = walletID;
        this.meetTimeFrom = meetTimeFrom;
        this.meetTimeTo = meetTimeTo;
        this.comment = comment;
        this.banknotes = banknotes;
        this.auth = auth;
        this.isCourier = isCourier;
    }
}
