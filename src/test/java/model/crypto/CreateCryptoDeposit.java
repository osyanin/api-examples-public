package model.crypto;

import com.google.gson.annotations.SerializedName;

public class CreateCryptoDeposit {

	@SerializedName("amount")
	private String amount;

	@SerializedName("userWalletAddress")
	private String userWalletAddress;

	@SerializedName("serviceStatusID")
	private Integer serviceStatusID;

	@SerializedName("serviceCurrencyID")
	private Integer serviceCurrencyID;

	@SerializedName("serviceID")
	private Integer serviceID;

	@SerializedName("userID")
	private Integer userID;

	@SerializedName("transactionID")
	private String transactionID;

	@SerializedName("serviceOrderID")
	private Integer serviceOrderID;

	public CreateCryptoDeposit(String amount, String userWalletAddress, Integer serviceStatusID, Integer serviceCurrencyID, Integer serviceID, Integer userID, String transactionID, Integer serviceOrderID) {
		this.amount = amount;
		this.userWalletAddress = userWalletAddress;
		this.serviceStatusID = serviceStatusID;
		this.serviceCurrencyID = serviceCurrencyID;
		this.serviceID = serviceID;
		this.userID = userID;
		this.transactionID = transactionID;
		this.serviceOrderID = serviceOrderID;
	}

	public String getAmount(){
		return amount;
	}

	public String getUserWalletAddress(){
		return userWalletAddress;
	}

	public Integer getServiceStatusID(){
		return serviceStatusID;
	}

	public Integer getServiceCurrencyID(){
		return serviceCurrencyID;
	}

	public Integer getServiceID(){
		return serviceID;
	}

	public Integer getUserID(){
		return userID;
	}

	public String getTransactionID(){
		return transactionID;
	}

	public Integer getServiceOrderID(){
		return serviceOrderID;
	}
}