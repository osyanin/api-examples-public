package model.fiat;

import lombok.Data;

@Data
public class CreateFiatDeposit {
    private String amount;
    private Integer walletID;

    public CreateFiatDeposit(String amount, Integer walletID) {
        this.amount = amount;
        this.walletID = walletID;
    }
}
