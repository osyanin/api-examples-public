package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.JsonVerificator;
import core.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.JsonBodyExchange;
import model.crypto.*;

import static core.TestBase.siteConnection;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CryptoMethods {

	protected JsonVerificator jsonVerificator = new JsonVerificator();
	protected JsonBodyExchange jsonBodyExchange = new JsonBodyExchange();
	Gson gson = new GsonBuilder().serializeNulls().create();

	@Step("Запрос: fetch_status_group_filter_crypto_user {user.login}")
	public Response fetchStatusGroupFilterCryptoUserStep(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/fetch_status_group_filter_crypto_user")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/fetchStatusGroupFilterCryptoUser.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: fetch_status_group_filter_crypto_admin")
	public Response fetchStatusGroupFilterCryptoAdminStep(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/fetch_status_group_filter_crypto_admin")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/fetchStatusGroupFilterCryptoUser.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: crypto_directions")
	public Response cryptoDirectionsStep(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/crypto_directions")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/cryptoDirections.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/user/wallet/fetch_user_wallets_crypto")
	public Response fetchUserWalletCryptoStep(User user, UserWalletID walletID) {
		String request = gson.toJson(walletID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/user/wallet/fetch_user_wallets_crypto")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/fetchUserCryptoWallet.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/user/wallet/fetch_user_wallets_crypto")
	public Response fetchUserWalletCryptoNegativeStep(User user, UserWalletID walletID, Integer statusCode) {
		String request = gson.toJson(walletID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/user/wallet/fetch_user_wallets_crypto")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_user_crypto_transfer")
	public Response fetchUserCryptoTransferStep(User user, FetchUserCryptoTransfer fetchUserCryptoTransfer) {
		String request = gson.toJson(fetchUserCryptoTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_user_crypto_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/fetchUserCryptoTransfer.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_user_crypto_transfer")
	public Response fetchUserCryptoTransferNegativeStep(User user,
			FetchUserCryptoTransfer fetchUserCryptoTransfer,
			Integer statusCode) {
		String request = gson.toJson(fetchUserCryptoTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_user_crypto_transfer")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_admin_crypto_transfer")
	public Response fetchAdminCryptoTransferStep(User user, FetchAdminCryptoTransfer fetchUserCryptoTransfer) {
		String request = gson.toJson(fetchUserCryptoTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_admin_crypto_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				/*.body(matchesJsonSchemaInClasspath("schemas/crypto/fetchAdminCryptoTransfer.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("Запрос: get_crypto_details_user")
	public Response getCryptoDetailsUser(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_crypto_details_user")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/getCryptoDetailsUser.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: get_crypto_details_user")
	public Response getCryptoDetailsNegativeUser(User user, OrderID orderID, Integer statusCode) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_crypto_details_user")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/body2.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: get_crypto_details_admin")
	public Response getCryptoDetailsAdmin(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_crypto_details_admin")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/getCryptoDetailsAdmin.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: get_crypto_details_admin")
	public Response getCryptoDetailsNegativeAdmin(User user, OrderID orderID, Integer statusCode) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_crypto_details_admin")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/body2.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: create_crypto_deposit")
	public Response createCryptoDepositStep(User user, CreateCryptoDeposit createCryptoDeposit) {
		String request = gson.toJson(createCryptoDeposit);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_crypto_deposit")
				.then()
				.statusCode(403)
				.extract()
				.response();
	}

	@Step("Запрос: create_user_wallet_crypto: {user.login}")
	public Response createUserWalletCryptoStep(User user, UserWalletID userWalletID) {
		String request = gson.toJson(userWalletID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/user/wallet/create_user_wallet_crypto")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/getCryptoDetailsAdmin.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("Запрос: create_user_wallet_crypto: {user.login}")
	public Response createUserWalletCryptoNegativeStep(User user, UserWalletID userWalletID, Integer statusCode) {
		String request = gson.toJson(userWalletID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/user/wallet/create_user_wallet_crypto")
				.then()
				.statusCode(statusCode)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/getCryptoDetailsAdmin.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("Запрос order/create_crypto_withdraw Login: {user.login} Сумма: {withdraw.amount} ID кошелька: {withdraw.walletExternal} ID кошелька: {withdraw.walletID}")
	public Response createCryptoWithdrawStep(User user, Withdraw withdraw) {
		String request = gson.toJson(withdraw);
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", ContentType.JSON)
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_crypto_withdraw")
				.then()
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/crypto/getCryptoDetailsAdmin.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();

	}
}
