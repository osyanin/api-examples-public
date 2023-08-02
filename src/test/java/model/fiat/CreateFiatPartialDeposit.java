package model.fiat;

import lombok.Data;

@Data
public class CreateFiatPartialDeposit {
	private String publicID;
	private Integer payedTime;
	private String amount;
	private String successAmount;
	private String failedAmount;
	private String waitingAmount;
	private String success;
	private String serviceStatusName;
	private Integer serviceStatusID;

	public CreateFiatPartialDeposit(String publicID,
			Integer payedTime,
			String amount,
			String successAmount,
			String failedAmount,
			String waitingAmount,
			String success,
			String serviceStatusName,
			Integer serviceStatusID) {
		this.publicID = publicID;
		this.payedTime = payedTime;
		this.amount = amount;
		this.successAmount = successAmount;
		this.failedAmount = failedAmount;
		this.waitingAmount = waitingAmount;
		this.success = success;
		this.serviceStatusName = serviceStatusName;
		this.serviceStatusID = serviceStatusID;
	}
}
