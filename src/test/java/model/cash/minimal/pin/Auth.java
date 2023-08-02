package model.cash.minimal.pin;

import lombok.Data;

@Data
public class Auth {
    private String pinCode;

    public Auth(String pinCode) {
        this.pinCode = pinCode;
    }
}
