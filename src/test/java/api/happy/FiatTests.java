package api.happy;

import static com.google.gson.JsonParser.parseString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonElement;
import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.JsonBodyFiat;
import model.body.Auth;
import model.body.CreateFiatWithdrawBody;
import model.body.FiatDeposit;
import model.body.OrderID;
import org.junit.jupiter.api.Test;

@Feature("FIAT")
@Epic("Positive")
@Owner("Osyanin Boris")
public class FiatTests extends TestBase {

	protected JsonBodyFiat jsonBodyFiat = new JsonBodyFiat();

	@Test
	@Description("fetch_fiats")
	void fetchFiatTest() {
		Response response = fiatMethods.fetchFiats(BO);
		JsonElement jsonElement = parseString(response.body().asString());
		assertEquals(jsonBodyFiat.fiatListBodyGsonAssertion(), jsonElement, "List of FIAT's");
	}

	@Test
	@Description("fetchAdminFiatTransfer - response schema checking")
	void fetchAdminFiatTransferTest() {
		fiatMethods.fetchAdminFiatTransferStep(ADMIN);
	}

	@Test
	@Description("getFiatDetailsAdmin")
	void getFiatDetailsAdminTest() {
		fiatMethods.getFiatDetailsAdmin(ADMIN, new OrderID(25));
	}

	@Test
	@Description("getFiatDetailsUser")
	void getFiatDetailsUserTest() {
		fiatMethods.getFiatDetailsUser(BO, new OrderID(743));
	}

	@Test
	@Description("Create FIAT deposit")
	void createFiatDepositTest() {
		fiatMethods.createFiatDeposit(BO, new FiatDeposit("2500", 2879, 2));
	}

	@Test
	@Description("Update Fiat Status Success")
	void updateFiatStatusSuccessTest() {
		Response response = fiatMethods.createFiatDeposit(BO, new FiatDeposit("2500", 2879, 2));

		int ID = response.path("OrderID");

		Response response1 = fiatMethods.getFiatDetailsUser(BO, new OrderID(ID));
		String UID = response1.path("publicID");
		fiatMethods.updateFiatStatusSuccessStep(UID);
	}

    /*@Test
    @Description("Create Fiat Withdraw")
    void createFiatWithdrawTest() {
        fiatMethods.createFiatWithdrawStep(
                BO,
                new CreateFiatWithdrawBody(
                        new Auth(
                                BO.getPin(),
                                siteConnection.getTwoFactorPassword(),
                                ""),
                        "2640",
                        2879,
                        "2222222222222222",
                        2));
    }*/

}
