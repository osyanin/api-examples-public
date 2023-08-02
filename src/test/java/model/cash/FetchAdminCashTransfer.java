package model.cash;

import lombok.Data;

@Data
public class FetchAdminCashTransfer {
    private String publicID;
    private Long timeFrom;
    private Long timeTo;
    private Integer currencyID;
    private String directionID;
    private Integer vaultID;
    private Integer userID;
    private Integer userWalletID;
    private String userPrivateName;
    private String nearAmount;
    private String statusGroupList;
    private String toChangeStatusGroupID;
    private Integer limit;
    private Integer offset;
    private String orderBy;

    public FetchAdminCashTransfer(String publicID, Long timeFrom, Long timeTo, Integer currencyID, String directionID, Integer vaultID, Integer userID, Integer userWalletID, String userPrivateName, String nearAmount, String statusGroupList, String toChangeStatusGroupID, Integer limit, Integer offset, String orderBy) {
        this.publicID = publicID;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.currencyID = currencyID;
        this.directionID = directionID;
        this.vaultID = vaultID;
        this.userID = userID;
        this.userWalletID = userWalletID;
        this.userPrivateName = userPrivateName;
        this.nearAmount = nearAmount;
        this.statusGroupList = statusGroupList;
        this.toChangeStatusGroupID = toChangeStatusGroupID;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }
}
