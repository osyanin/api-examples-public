package model.body;

import lombok.Data;

@Data
public class CreateExchange {
    private String amount;
    private String amountFrom;
    private String rule;
    private Integer walletFromID;
    private Integer walletToID;

    private Auth auth;

    public CreateExchange(String amount, String amountFrom, String rule, Integer walletFromID, Integer walletToID, Auth auth) {
        this.amount = amount;
        this.amountFrom = amountFrom;
        this.rule = rule;
        this.walletFromID = walletFromID;
        this.walletToID = walletToID;
        this.auth = auth;
    }
}
