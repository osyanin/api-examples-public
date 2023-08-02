package model.crypto;

import lombok.Data;

@Data
public class FetchAdminCryptoTransfer {
    private String publicID;
    private Integer currencyID;
    private Integer directionID;
    private Integer userID;
    private Integer userWalletID;
    private String userPrivateName;
    private String nearAmount;
    private Integer statusGroupList;
    private Integer limit;
    private Integer offset;
    private String orderBy;

    public FetchAdminCryptoTransfer(String publicID, Integer currencyID, Integer directionID, Integer userID, Integer userWalletID, String userPrivateName, String nearAmount, Integer statusGroupList, Integer limit, Integer offset, String orderBy) {
        this.publicID = publicID;
        this.currencyID = currencyID;
        this.directionID = directionID;
        this.userID = userID;
        this.userWalletID = userWalletID;
        this.userPrivateName = userPrivateName;
        this.nearAmount = nearAmount;
        this.statusGroupList = statusGroupList;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }
}
