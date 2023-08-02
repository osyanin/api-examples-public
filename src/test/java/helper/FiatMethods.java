package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.JsonVerificator;
import core.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.JsonBodyFiat;
import model.body.CreateFiatWithdrawBody;
import model.body.FetchAdminFiatTransferBody;
import model.body.FiatDeposit;
import model.body.OrderID;
import model.fiat.CreateFiatPartialDeposit;

import static core.TestBase.siteConnection;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FiatMethods {

	protected JsonVerificator jsonVerificator = new JsonVerificator();
	protected JsonBodyFiat jsonBodyFiat = new JsonBodyFiat();
	Gson gson = new GsonBuilder().serializeNulls().create();

	@Step("/list/fetch_fiats")
	public Response fetchFiats(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/fetch_fiats")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fiat/fetchFiatBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/vault/fetch_admin_fiat_transfer")
	public Response fetchAdminFiatTransferStep(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBodyFiat.fetchAdminFiatTransferGson())
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_admin_fiat_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fiat/fetchAdminFiatTransferMinimal.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/vault/fetch_admin_fiat_transfer")
	public Response fetchAdminFiatTransferNegativeStep(User user,
			FetchAdminFiatTransferBody fetchAdminFiatTransferBody,
			Integer statusCode) {
		String request = gson.toJson(fetchAdminFiatTransferBody);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_admin_fiat_transfer")
				.then()
				.statusCode(statusCode)
				.extract()
				.response();
	}

	@Step("/order/get_fiat_details_admin")
	public Response getFiatDetailsAdmin(User user, OrderID orderID) {

		String request = gson.toJson(orderID);
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_fiat_details_admin")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fiat/getFiatDetailsAdmin.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_fiat_details_admin")
	public Response getFiatDetailsAdminNegative(User user, OrderID orderID, Integer statusCode) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_fiat_details_admin")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_fiat_details_user")
	public Response getFiatDetailsUser(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_fiat_details_user")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fiat/getFiatDetailsUser.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_fiat_details_user")
	public Response getFiatDetailsUserNegative(User user, OrderID orderID, Integer statusCode) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_fiat_details_user")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_fiat_deposit")
	public Response createFiatDeposit(User user, FiatDeposit fiatDeposit) {
		String request = gson.toJson(fiatDeposit);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_fiat_deposit")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fiat/createFiatDeposit.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_fiat_deposit")
	public Response createFiatDepositNegative(User user, FiatDeposit fiatDeposit, Integer statusCode) {
		String request = gson.toJson(fiatDeposit);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_fiat_deposit")
				.then()
				.statusCode(statusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_fiat_deposit")
	public Response createFiatPartialDeposit(User user, CreateFiatPartialDeposit createFiatPartialDeposit) {
		String request = gson.toJson(createFiatPartialDeposit);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_fiat_deposit")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fiat/createFiatDeposit.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/update_fiat_status_success")
	public Response updateFiatStatusSuccessStep(String orderUID) {
		return given()
				.header("Content-Type", "application/json")
				.body(jsonBodyFiat.updateFiatStatusSuccessGson(orderUID))
				.when()
				.post(siteConnection.getUrlApi() + "/order/update_fiat_status_success")
				.then()
				.statusCode(200)
				.assertThat()
				.extract()
				.response();
	}

	@Step("/order/create_fiat_withdraw")
	public Response createFiatWithdrawStep(User user, CreateFiatWithdrawBody createFiatWithdrawBody) {
		String request = gson.toJson(createFiatWithdrawBody);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_fiat_withdraw")
				.then()
				.statusCode(200)
				.extract()
				.response();
	}


}