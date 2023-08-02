package model.body;

import lombok.Data;

@Data
public class FetchUserExchange {
    private String publicID;
    private Integer walletFromID;
    private Integer walletToID;
    private Integer currencyFromID;
    private Integer currencyToID;
    private String nearAmount;
    private String statusGroupList;
    private String orderBy;
    private Integer limit;
    private Integer offset;

    public FetchUserExchange(String publicID, Integer walletFromID, Integer walletToID, Integer currencyFromID, Integer currencyToID, String nearAmount, String statusGroupList, String orderBy, Integer limit, Integer offset) {
        this.publicID = publicID;
        this.walletFromID = walletFromID;
        this.walletToID = walletToID;
        this.currencyFromID = currencyFromID;
        this.currencyToID = currencyToID;
        this.nearAmount = nearAmount;
        this.statusGroupList = statusGroupList;
        this.orderBy = orderBy;
        this.limit = limit;
        this.offset = offset;
    }
}
