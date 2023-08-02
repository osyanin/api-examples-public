package model.cash;

import lombok.Data;

@Data
public class Auth {
    private String otpCode;
    private String pinCode;

    public Auth(String otpCode, String pinCode) {
        this.otpCode = otpCode;
        this.pinCode = pinCode;
    }
}
