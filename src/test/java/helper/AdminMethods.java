package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import core.JsonVerificator;
import core.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.JsonBody;

import static core.TestBase.siteConnection;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AdminMethods {

	protected JsonVerificator jsonVerificator = new JsonVerificator();
	protected JsonBody jsonBody = new JsonBody();

	public Response loginAsAdminExtractResponse(User user) {
		return given()
				.header("Content-Type", "application/json")
				.header("Fingerprint", "admin fingerprint")
				.body(userCredentialsBodyJackson(user))
				.when()
				.log()
				.all()
				.post(siteConnection.getUrlApi() + "/admin/login")
				.then()
				.log()
				.ifError()
				.statusCode(200)
				.extract()
				.response();
	}

	protected String userCredentialsBodyJackson(User user) {
		String createdPlainJsonObject;
		ObjectMapper objectMapper = new ObjectMapper();

		ObjectNode obj = objectMapper.createObjectNode();
		obj.put("username", user.getLogin());
		obj.put("password", user.getPassword());
		obj.put("otpCode", siteConnection.getTwoFactorPassword());

		try {
			createdPlainJsonObject = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return createdPlainJsonObject;
	}

	@Step("admin/role/get_admin_role")
	public Response getAdminRole(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.contentType("text/html;charset=UTF-8")
				.cookie("sessionKey", user.getToken())
				.when()
				.get(siteConnection.getUrlApi() + "/admin/role/get_admin_role")
				.then()
				.statusCode(200)
				.assertThat()
				.extract()
				.response();
	}

	@Step("admin/role/get_admin_role")
	public String getInviteToken(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.getInviteTokenBodyJackson(7, 2, "kpss"))
				.when()
				.post(siteConnection.getUrlApi() + "/admin/user/get_invite_token")
				.then()
				.statusCode(200)
				.assertThat()
				.body("inviteToken", is(notNullValue()))
				.extract()
				.path("inviteToken");
	}

	@Step("/admin/user/fetch_user_balance_admin")
	public Response fetchUserBalanceByAdmin(User user) {
		return given()
				.header("Authorization", user.getToken())
				.header("Fingerprint", "admin fingerprint")
				.header("Content-Type", "application/json")
				.cookie("sessionKey", user.getToken())
				.body(jsonBody.userIdBodyJackson(453))
				.when()
				.post(siteConnection.getUrlApi() + "/admin/user/fetch_user_balance_admin")
				.then()
				.statusCode(200)
				.assertThat()
				.body(matchesJsonSchemaInClasspath("schemas/fetchBalanceBody.json").using(jsonVerificator.jsonSchemaFactory))
				.extract()
				.response();
	}
}
