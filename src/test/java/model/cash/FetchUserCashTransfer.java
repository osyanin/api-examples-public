package model.cash;

import lombok.Data;

@Data
public class FetchUserCashTransfer {
    private String publicID;
    private Long timeFrom;
    private Long timeTo;
    private Integer currencyID;
    private String directionID;
    private Integer vaultID;
    private Integer walletID;
    private String nearAmount;
    private String statusGroupList;
    private Integer limit;
    private Integer offset;
    private String orderBy;

    public FetchUserCashTransfer(String publicID, Long timeFrom, Long timeTo, Integer currencyID, String directionID, Integer vaultID, Integer walletID, String nearAmount, String statusGroupList, Integer limit, Integer offset, String orderBy) {
        this.publicID = publicID;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.currencyID = currencyID;
        this.directionID = directionID;
        this.vaultID = vaultID;
        this.walletID = walletID;
        this.nearAmount = nearAmount;
        this.statusGroupList = statusGroupList;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }
}
