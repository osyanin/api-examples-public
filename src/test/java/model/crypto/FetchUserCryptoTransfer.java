package model.crypto;

import lombok.Data;

@Data
public class FetchUserCryptoTransfer {
    private String publicID;
    private Integer currencyID;
    private Integer directionID;
    private Integer walletID;
    private String nearAmount;
    private Integer statusGroupList;
    private Integer limit;
    private Integer offset;
    private String orderBy;

    public FetchUserCryptoTransfer(String publicID, Integer currencyID, Integer directionID, Integer walletID, String nearAmount, Integer statusGroupList, Integer limit, Integer offset, String orderBy) {
        this.publicID = publicID;
        this.currencyID = currencyID;
        this.directionID = directionID;
        this.walletID = walletID;
        this.nearAmount = nearAmount;
        this.statusGroupList = statusGroupList;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }
}
