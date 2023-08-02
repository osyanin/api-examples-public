package model.body;

import lombok.*;

@Data
public class FetchAdminFiatTransferBody {
    private String publicID;
    private Integer userID;
    private Integer userWalletID;
    private String userPrivateName;
    private Integer currencyID;
    private String statusGroupList;
    private Integer limit;
    private Integer offset;
    private String orderBy;

    public FetchAdminFiatTransferBody(String publicID, Integer userID, Integer userWalletID, String userPrivateName, Integer currencyID, String statusGroupList, Integer limit, Integer offset, String orderBy) {
        this.publicID = publicID;
        this.userID = userID;
        this.userWalletID = userWalletID;
        this.userPrivateName = userPrivateName;
        this.currencyID = currencyID;
        this.statusGroupList = statusGroupList;
        this.limit = limit;
        this.offset = offset;
        this.orderBy = orderBy;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FetchAdminFiatTransferBody;
    }
}