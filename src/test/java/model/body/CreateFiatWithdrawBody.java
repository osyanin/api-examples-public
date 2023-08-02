package model.body;

import lombok.Data;

@Data
public class CreateFiatWithdrawBody {
    private Auth auth;
    private String amount;
    private Integer walletID;
    private String recipient;
    private Integer gatewayID;

    public CreateFiatWithdrawBody(Auth auth, String amount, Integer walletID, String recipient, Integer gatewayID) {
        this.amount = amount;
        this.walletID = walletID;
        this.recipient = recipient;
        this.gatewayID = gatewayID;
        this.auth = auth;
    }
}