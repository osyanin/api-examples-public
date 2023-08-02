package model.body;

import lombok.Data;

@Data
public
class Auth {
    private String pinCode;
    private String securityCode;
    private String securityID;

    public Auth(String pinCode, String securityCode, String securityID) {
        this.pinCode = pinCode;
        this.securityCode = securityCode;
        this.securityID = securityID;
    }
}