package model.crypto;

import lombok.Data;
import model.body.Auth;

@Data
public class Withdraw {
	private Auth auth;
	private Integer walletID;
	private String amount;
	private String walletExternal;

	public Withdraw(Auth auth, Integer walletID, String amount, String walletExternal) {
		this.auth = auth;
		this.walletID = walletID;
		this.amount = amount;
		this.walletExternal = walletExternal;
	}
}

//{"auth":{"pinCode":"16011","securityCode":"zxclol","securityID":""},"walletID":7569,"amount":"21809.379","walletExternal":"TPJQY64AUXPbd7YuPYwRqaJjxMpZd1NcH4"}