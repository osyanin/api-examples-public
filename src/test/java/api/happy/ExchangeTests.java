package api.happy;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.body.FetchAdminExchange;
import model.body.FetchUserExchange;
import model.body.GetUserExchangeRate;
import model.body.OrderID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Обмен")
@Epic("Positive")
@Owner("Osyanin Boris")
public class ExchangeTests extends TestBase {

	@Test
	void getExchangeDetailsAdminTest() {
		exchangeMethods.getExchangeDetailsAdminStep(ADMIN, new OrderID(1302614));
	}

	@Test
	void getExchangeDetailsUserTest() {
		exchangeMethods.getExchangeDetailsUserStep(BO, new OrderID(1302614));
	}

	@Test
	void createExchangeTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 5000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				2877,
				BO
		);
		String orderID = extractOrderID.body().path("publicID");

		ADMIN = updateAdminToken(ADMIN);
		exchangeMethods.fetchAdminExchangeStep(new FetchAdminExchange(orderID,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				"created_at_DESC",
				10,
				0
		), ADMIN);
	}

	@Test
	void fetchUserExchange() {
		exchangeMethods.fetchUserExchangeStep(new FetchUserExchange(null,
				null,
				null,
				null,
				null,
				null,
				null,
				"created_at_DESC",
				10,
				0
		), BO);
	}

	@Test
	@Disabled
	void fetchAdminExchangeTest() {
		Response response = exchangeMethods.fetchAdminExchangeStep(new FetchAdminExchange("O-b337-2c0e3bfa3791",
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				"created_at_DESC",
				10,
				0
		), ADMIN);
		ArrayList<Integer> ID = response.body().path("data.ID");
		assertEquals(ID.get(0), 1302675);
	}

	@Test
	void fetchStatusGroupFilterExchangeTest() {
		exchangeMethods.fetchStatusGroupFilterExchangeStep(BO);
	}

	@Test
	void fetchStatusGroupFilterExchangeAdminTest() {
		exchangeMethods.fetchStatusGroupFilterExchangeAdmin(ADMIN);
	}
}
