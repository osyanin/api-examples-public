package helper;

import static core.TestBase.siteConnection;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import core.JsonVerificator;
import core.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.JsonBody;

public class UserMethods {

	protected JsonVerificator jsonVerificator = new JsonVerificator();
	protected JsonBody jsonBody = new JsonBody();

	@Step("Запрос: get_user_data: {user.login}")
	public Response getUserDataBody(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/user/profile/get_user_data")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/getUserDataBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: get_user_data: {user.login}")
	public void getUserDataBodyAfterLogout(User user) {
		given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/user/profile/get_user_data")
				.then()
				.statusCode(401)
				.assertThat();
	}

	@Step("user/registration/init_registration_v3: {user.login} {inviteToken}")
	public Response initRegisterv3(User user, String inviteToken) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.registrationInitBodyJackson(user.getLogin(), user.getPassword(), user.getPin(), inviteToken))
				.when()
				.post(siteConnection.getUrlApi() + "/user/registration/init_registration_v3")
				.then()
				.statusCode(201)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/registeredUserDataBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("user/registration/init_registration_v3: {user.login} {inviteToken}")
	public Response initRegisterv3Negative(User user, String inviteToken, Integer statusCode) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.registrationInitBodyJackson(user.getLogin(), user.getPassword(), user.getPin(), inviteToken))
				.when()
				.post(siteConnection.getUrlApi() + "/user/registration/init_registration_v3")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.extract()
				.response();
	}

	@Step("user/registration/finish_registration_v3")
	public Response finishRegistration(User user,
			String firstName,
			String codewordGreen,
			int securityQuestionIDOne,
			String securityQuestionAnswerOne,
			String tgUsernameMain,
			String emailMain) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.registrationFinishBodyJackson(firstName,
						codewordGreen,
						securityQuestionIDOne,
						securityQuestionAnswerOne,
						tgUsernameMain,
						emailMain
				))
				.when()
				.post(siteConnection.getUrlApi() + "/user/profile/finish_registration_v3")
				.then()
				.statusCode(200)
				.extract()
				.response();
	}

	@Step("Запрос: get_user_data")
	public Response getUserDataBodyAfterRegistrationPartOne(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/user/profile/get_user_data")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/getUserDataBodyRegisterPartOne.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/user/profile/update_password")
	public Response changePassword(User user, String oldPassword, String newPassword) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.changePasswordBodyJackson(oldPassword, newPassword))
				.when()
				.put(siteConnection.getUrlApi() + "/user/profile/update_password")
				.then()
				.statusCode(200)
				.extract()
				.response();
	}

	@Step("/user/profile/update_password")
	public Response changePasswordNegative(User user, String oldPassword, String newPassword, Integer statusCode) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.changePasswordBodyJackson(oldPassword, newPassword))
				.when()
				.put(siteConnection.getUrlApi() + "/user/profile/update_password")
				.then()
				.statusCode(statusCode)
				.extract()
				.response();
	}

	@Step("/user/profile/update_password")
	public Response changePinStep(User user, String oldPin, String newPin) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.changePinBodyJackson(user.getPassword(), oldPin, newPin, siteConnection.getTwoFactorPassword()))
				.when()
				.put(siteConnection.getUrlApi() + "/user/profile/update_payment_pin")
				.then()
				.statusCode(200)
				.extract()
				.response();
	}

	@Step("/user/profile/update_password")
	public Response changePinNegativeStep(User user, String oldPin, String newPin, Integer statusCode) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.changePinBodyJackson(user.getPassword(), oldPin, newPin, siteConnection.getTwoFactorPassword()))
				.when()
				.put(siteConnection.getUrlApi() + "/user/profile/update_payment_pin")
				.then()
				.statusCode(statusCode)
				.extract()
				.response();
	}

	@Step("/update_tg_username")
	public Response changeTgUsername(User user, String newTgUsername, boolean isMain) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.changeTgUsernameBodyJackson(newTgUsername, isMain))
				.when()
				.put(siteConnection.getUrlApi() + "/user/profile/update_tg_username")
				.then()
				.statusCode(200)
				.extract()
				.response();
	}

	@Step("/update_tg_username")
	public Response changeTgUsernameNegative(User user, String newTgUsername, Boolean isMain, Integer statusCode) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.changeTgUsernameBodyJackson(newTgUsername, isMain))
				.when()
				.put(siteConnection.getUrlApi() + "/user/profile/update_tg_username")
				.then()
				.statusCode(statusCode)
				.extract()
				.response();
	}

	@Step("/user/auth/logout")
	public void logout(User user) {
		given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.when()
				.post(siteConnection.getUrlApi() + "/user/auth/logout")
				.then()
				.statusCode(200);
	}
}
