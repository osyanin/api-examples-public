package model.body;

import lombok.Data;

@Data
public class FiatDeposit {
    private String amount;
    private Integer walletID;
    private Integer gatewayID;

    public FiatDeposit(String amount, Integer walletID, Integer gatewayID) {
        this.amount = amount;
        this.walletID = walletID;
        this.gatewayID = gatewayID;
    }
}
