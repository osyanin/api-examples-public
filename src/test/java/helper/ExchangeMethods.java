package helper;

import static core.TestBase.siteConnection;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.JsonVerificator;
import core.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.JsonBodyExchange;
import model.body.FetchAdminExchange;
import model.body.FetchUserExchange;
import model.body.GetUserExchangeRate;
import model.body.OrderID;

public class ExchangeMethods {

	protected JsonVerificator jsonVerificator = new JsonVerificator();
	protected JsonBodyExchange jsonBodyExchange = new JsonBodyExchange();
	Gson gson = new GsonBuilder().serializeNulls().create();

	@Step("/order/get_exchange_details_admin")
	public Response getExchangeDetailsAdminStep(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_exchange_details_admin")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/getExchangeDetailsAdmin.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_exchange_details_admin: {user.login} {orderID} {statusCode}")
	public Response getExchangeDetailsNegativeAdminStep(User user, Integer orderID, Integer statusCode) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("orderID", orderID);

		String request = gson.toJson(jsonObject);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_exchange_details_admin")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_exchange_details_user")
	public Response getExchangeDetailsUserStep(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_exchange_details_user")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/getExchangeDetailsUser.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_exchange_details_user ")
	public Response getExchangeDetailsNegativeUserStep(User user, Integer orderID, Integer statusCode) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("orderID", orderID);

		String request = gson.toJson(jsonObject);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_exchange_details_user")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/body2.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_exchange")
	public Response createExchangeStep(String amount,
			String amountFrom,
			String rule,
			Integer walletFromID,
			Integer walletToID,
			User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBodyExchange.createExchangeGson(amount,
						amountFrom,
						rule,
						walletFromID,
						walletToID,
						user.getPin(),
						siteConnection.getTwoFactorPassword(),
						""
				))
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_exchange")
				.then()
				.statusCode(201)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/createExchange.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_exchange")
	public Response createExchangeStepNegativeStep(String amount,
			String amountFrom,
			String rule,
			Integer walletFromID,
			Integer walletToID,
			User user,
			Integer statusCode) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBodyExchange.createExchangeGson(amount,
						amountFrom,
						rule,
						walletFromID,
						walletToID,
						user.getPin(),
						siteConnection.getTwoFactorPassword(),
						""
				))
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_exchange")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_user_exchange")
	public Response fetchUserExchangeStep(FetchUserExchange fetchUserExchange, User user) {
		String request = gson.toJson(fetchUserExchange);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_user_exchange")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/fetchUserExchange.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_user_exchange")
	public Response fetchUserExchangeNegativeStep(FetchUserExchange fetchUserExchange, User user, Integer statusCode) {
		String request = gson.toJson(fetchUserExchange);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_user_exchange")
				.then()
				.statusCode(400)
				.extract()
				.response();
	}

	@Step("/order/fetch_admin_exchange")
	public Response fetchAdminExchangeStep(FetchAdminExchange fetchAdminExchange, User user) {
		String request = gson.toJson(fetchAdminExchange);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_admin_exchange")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/fetchAdminExchange.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/user/wallet/get_user_exchange_rate")
	public Response getUserExchangeRateStep(GetUserExchangeRate getUserExchangeRate, User user) {
		String request = gson.toJson(getUserExchangeRate);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/user/wallet/get_user_exchange_rate")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/getUserExchangeRate.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/user/wallet/get_user_exchange_rate {user.login}")
	public Response getUserExchangeRateNegativeStep(User user,
			GetUserExchangeRate getUserExchangeRate,
			Integer statusCode) {

		String request = gson.toJson(getUserExchangeRate);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/user/wallet/get_user_exchange_rate")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.extract()
				.response();
	}

	@Step("/order/update_exchange_status")
	public Response updateExchangeStatusStep(Integer orderID, Integer statusID, String otpCode, User user) {

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBodyExchange.updateExchangeStatusGSON(orderID, statusID, otpCode))
				.when()
				.put(siteConnection.getUrlApi() + "/order/update_exchange_status")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/updateExchangeStatus.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/list/fetch_status_group_filter_exchange_user")
	public Response fetchStatusGroupFilterExchangeStep(User user) {

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/fetch_status_group_filter_exchange_user")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/fetchStatusGroupFilterExchange.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/list/fetch_status_group_filter_exchange_user")
	public Response fetchStatusGroupFilterExchangeAdmin(User user) {

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/fetch_status_group_filter_exchange_admin")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/exchange/fetchStatusGroupFilterExchange.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}
}
