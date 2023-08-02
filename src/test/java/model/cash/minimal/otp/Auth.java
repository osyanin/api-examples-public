package model.cash.minimal.otp;

import lombok.Data;

@Data
public class Auth {
    private String otpCode;

    public Auth(String otpCode) {
        this.otpCode = otpCode;
    }
}
