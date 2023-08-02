package core;

import java.io.File;

public class SiteVariables {

	private File rabbitJpg;
	private File rabbitPdf;

	private String generatedCyrillicUpperCase;
	private String generatedCyrillicLowerCase;
	private String generatedPhoneNumber;
	private String generatedPassportNumber;
	private String newUser;
	private String newUserAnother;
	private String newUserTg;
	private String newUserPinCode;
	private String newUserMail;
	private String newUserPassword;

	private String newMaster;
	private String newMasterMail;
	private String newMasterPassword;

	private String newGameName;
	private String newConventName;

	private String inviteCode;

	private String bigInviteCode;

	public static SiteVariables generate() {
		RandomGenerated generated = new RandomGenerated();
		SiteVariables vars = new SiteVariables();

		vars.rabbitJpg = new File("src/test/resources/rabbit.jpg");
		vars.rabbitPdf = new File("src/test/resources/rabbit.pdf");

		vars.generatedCyrillicUpperCase = new RandomGenerated().cyryllicUpperCase(1);
		vars.generatedCyrillicLowerCase = new RandomGenerated().cyryllicLowerCase(9);
		vars.generatedPhoneNumber = new RandomGenerated().phoneNumber888();
		vars.newUserPinCode = new RandomGenerated().number(5);

		vars.newUser = generated.stringValue(8);
		vars.newUserAnother = generated.stringValue(9);
		vars.newUserTg = generated.stringValue(10);
		vars.newUserMail = generated.stringValue(8).toLowerCase() + "@grr.la";
		vars.newUserPassword = generated.stringValue(2) + "a1!";
		vars.inviteCode = generated.stringValue(13);
		vars.bigInviteCode = generated.stringValue(14);
		return vars;
	}

	public File getRabbitJpg() {
		return rabbitJpg;
	}

	public void setRabbitJpg(File rabbitJpg) {
		this.rabbitJpg = rabbitJpg;
	}

	public File getRabbitPdf() {
		return rabbitPdf;
	}

	public void setRabbitPdf(File rabbitPdf) {
		this.rabbitPdf = rabbitPdf;
	}

	public String getGeneratedCyrillicUpperCase() {
		return generatedCyrillicUpperCase;
	}

	public void setGeneratedCyrillicUpperCase(String generatedCyrillicUpperCase) {
		this.generatedCyrillicUpperCase = generatedCyrillicUpperCase;
	}

	public String getGeneratedCyrillicLowerCase() {
		return generatedCyrillicLowerCase;
	}

	public void setGeneratedCyrillicLowerCase(String generatedCyrillicLowerCase) {
		this.generatedCyrillicLowerCase = generatedCyrillicLowerCase;
	}

	public String getGeneratedPhoneNumber() {
		return generatedPhoneNumber;
	}

	public void setGeneratedPhoneNumber(String generatedPhoneNumber) {
		this.generatedPhoneNumber = generatedPhoneNumber;
	}

	public String getGeneratedPassportNumber() {
		return generatedPassportNumber;
	}

	public void setGeneratedPassportNumber(String generatedPassportNumber) {
		this.generatedPassportNumber = generatedPassportNumber;
	}

	public String getNewUser() {
		return newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	public String getNewUserAnother() {
		return newUserAnother;
	}

	public String getNewUserPinCode() {
		return newUserPinCode;
	}

	public String getNewUserMail() {
		return newUserMail;
	}

	public void setNewUserMail(String newUserMail) {
		this.newUserMail = newUserMail;
	}

	public String getNewUserPassword() {
		return newUserPassword;
	}

	public void setNewUserPassword(String newUserPassword) {
		this.newUserPassword = newUserPassword;
	}

	public String getNewMaster() {
		return newMaster;
	}

	public void setNewMaster(String newMaster) {
		this.newMaster = newMaster;
	}

	public String getNewMasterMail() {
		return newMasterMail;
	}

	public void setNewMasterMail(String newMasterMail) {
		this.newMasterMail = newMasterMail;
	}

	public String getNewMasterPassword() {
		return newMasterPassword;
	}

	public void setNewMasterPassword(String newMasterPassword) {
		this.newMasterPassword = newMasterPassword;
	}

	public String getNewGameName() {
		return newGameName;
	}

	public void setNewGameName(String newGameName) {
		this.newGameName = newGameName;
	}

	public String getNewConventName() {
		return newConventName;
	}

	public void setNewConventName(String newConventName) {
		this.newConventName = newConventName;
	}

	public String getInvalidInviteCode() {
		return inviteCode;
	}

	public String getBigInviteCode() {
		return bigInviteCode;
	}
}
