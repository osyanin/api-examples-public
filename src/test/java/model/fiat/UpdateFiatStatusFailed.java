package model.fiat;

import lombok.Data;

@Data
public class UpdateFiatStatusFailed {
	private String orderUID;
	private Integer payedTime;
	private String amount;
	private String successAmount;
	private String failedAmount;
	private String waitingAmount;
	private String success;
	private String serviceStatusName;
	private Integer serviceStatusID;

	public UpdateFiatStatusFailed(String orderUID,
			Integer payedTime,
			String amount,
			String successAmount,
			String failedAmount,
			String waitingAmount,
			String success,
			String serviceStatusName,
			Integer serviceStatusID) {
		this.orderUID = orderUID;
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

// {
//	"orderUID": "7d2601c7-9a79-4f88-b739-96ce175c0946",
//	"payedTime": 1671186981,
//	"amount": "2500",
//	"successAmount": "2500",
//	"failedAmount": "2500",
//	"waitingAmount": "0",
//	"success": false,
//	"serviceStatusName": "A",
//	"serviceStatusID": 658
//}
