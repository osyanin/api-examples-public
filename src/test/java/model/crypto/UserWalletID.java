package model.crypto;

import lombok.Data;

@Data
public class UserWalletID {
    private Integer userWalletID;

    public UserWalletID(Integer userWalletID) {
        this.userWalletID = userWalletID;
    }
}
