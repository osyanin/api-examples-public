package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.JsonVerificator;
import core.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.cash.*;
import model.cash.minimal.pin.Order;

import static core.TestBase.siteConnection;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CashMethods {

	protected JsonVerificator jsonVerificator = new JsonVerificator();
	Gson gson = new GsonBuilder().serializeNulls().create();

	@Step("Запрос: get cash directions {user.login}")
	public Response getCashDirectionsStep(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/cash_directions")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/cash/getCashDirections.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("Запрос: fetch_for_new_cash_transfer {user.login}")
	public Response fetchForNewCashTransferStep(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/list/fetch_for_new_cash_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/cash/fetchForNewCashTransfer.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_available_date_slots {user.login} ")
	public Response getAvailableDateSlotsStep(User user, GetAvailableDateSlots getAvailableDateSlots) {
		String request = gson.toJson(getAvailableDateSlots);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_available_date_slots")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/cash/getAvailableDateSlots.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_user_cash_transfer {user.login}")
	public Response fetchUserCashTransferStep(User user, FetchUserCashTransfer fetchUserCashTransfer) {
		String request = gson.toJson(fetchUserCashTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_user_cash_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/cash/fetchUserCashTransfer.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/fetch_admin_cash_transfer {user.login}")
	public Response fetchAdminCashTransferStep(User user, FetchAdminCashTransfer fetchAdminCashTransfer) {
		String request = gson.toJson(fetchAdminCashTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/fetch_admin_cash_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/cash/fetchAdminCashTransfer.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_cash_transfer {user.login}")
	public Response createCashTransferStep(User user, CreateCashTransfer createCashTransfer) {
		String request = gson.toJson(createCashTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_cash_transfer")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/cash/createCashTransfer.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/create_cash_transfer {user.login}")
	public Response createCashTransferNegativeStep(User user,
			CreateCashTransfer createCashTransfer,
			int expectedStatusCode) {
		String request = gson.toJson(createCashTransfer);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/create_cash_transfer")
				.then()
				.statusCode(expectedStatusCode)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/errors/errorBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/update_cash_transfer_amount {user.login}")
	public Response updateCashTransferAmountStep(User user, UpdateCashTransferAmount updateCashTransferAmount) {
		String request = gson.toJson(updateCashTransferAmount);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.put(siteConnection.getUrlApi() + "/order/update_cash_transfer_amount")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/update_cash_transfer_status {user.login}")
	public Response updateCashTransferStatusStep(User user, UpdateCashTransferStatus updateCashTransferStatus) {
		String request = gson.toJson(updateCashTransferStatus);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.put(siteConnection.getUrlApi() + "/order/update_cash_transfer_status")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}

	@Step("/order/get_cash_transfer_details_admin {user.login}")
	public Response getCashTransferDetailsAdminStep(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_cash_transfer_details_admin")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("/order/get_cash_transfer_details_user {user.login}")
	public Response getCashTransferDetailsUserStep(User user, OrderID orderID) {
		String request = gson.toJson(orderID);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/get_cash_transfer_details_user")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("/order/update_cash_transfer_details_admin {user.login}")
	public Response updateCashTransferDetailsAdminStep(User user,
			UpdateCashTransferDetailsAdmin updateCashTransferDetailsAdmin) {
		String request = gson.toJson(updateCashTransferDetailsAdmin);

		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.put(siteConnection.getUrlApi() + "/order/update_cash_transfer_details_admin")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("/order/update_cash_transfer_status_user_cancelled {user.login}")
	public Response updateCashTransferStatusUserCancelled(User user, Order order) {
		String request = gson.toJson(order);
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "user fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.put(siteConnection.getUrlApi() + "/order/update_cash_transfer_status_user_cancelled")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("/order/update_cash_transfer_execution {user.login}")
	public Response updateCashTransferExecution(User user, UpdateCashTransferExecution updateCashTransferExecution) {
		String request = gson.toJson(updateCashTransferExecution);
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/update_cash_transfer_execution")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("/order/done_cash_transfer {user.login}")
	public Response doneCashTransfer(User user, DoneCashTransfer doneCashTransfer) {
		String request = gson.toJson(doneCashTransfer);
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/done_cash_transfer")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}

	@Step("/order/init_cash_transfer_verify {user.login}")
	public Response initCashTransferVerifyStep(User user, InitCashTransferVerify initCashTransferVerify) {
		String request = gson.toJson(initCashTransferVerify);
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(request)
				.when()
				.post(siteConnection.getUrlApi() + "/order/init_cash_transfer_verify")
				.then()
				.statusCode(200)
				/*.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/success.json")
								.using(jsonVerificator.jsonSchemaFactory))*/
				.extract()
				.response();
	}
}