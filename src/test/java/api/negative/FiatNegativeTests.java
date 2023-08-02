package api.negative;

import core.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.body.Auth;
import model.body.CreateFiatWithdrawBody;
import model.body.FetchAdminFiatTransferBody;
import model.body.FiatDeposit;
import model.body.OrderID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Negative")
@Feature("FIAT")
@Owner("Osyanin Boris")
public class FiatNegativeTests extends TestBase {

	@Test
	void getFiatDetailsAdminNull() {
		fiatMethods.getFiatDetailsAdminNegative(ADMIN, new OrderID(null), 400);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/negativeInts.csv")
	void getFiatDetailsAdminMinus(Integer orderID) {
		Response response = fiatMethods.getFiatDetailsAdminNegative(ADMIN, new OrderID(orderID), 400);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	void getFiatDetailsAdminZero() {
		fiatMethods.getFiatDetailsAdminNegative(ADMIN, new OrderID(0), 400);
	}

	@Test
	void getFiatDetailsAdminMaxInt() {
		fiatMethods.getFiatDetailsAdminNegative(ADMIN, new OrderID(2147483646), 400);
	}

	@Test
	void getFiatDetailsUserNull() {
		fiatMethods.getFiatDetailsUserNegative(BO, new OrderID(null), 400);
	}

	@Test
	void getFiatDetailsUserMinus() {
		fiatMethods.getFiatDetailsUserNegative(BO, new OrderID(-1), 400);
	}

	@Test
	void getFiatDetailsUserZero() {
		fiatMethods.getFiatDetailsUserNegative(BO, new OrderID(0), 400);
	}

	@Test
	void getFiatDetailsUserMaxInt() {
		fiatMethods.getFiatDetailsUserNegative(BO, new OrderID(2147483646), 400);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов publicID = \"\"")
	void fetchAdminFiatTransferPublicIDEmptyRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody("", null, null, "", null, "", null, null, ""),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов userID = 0")
	void fetchAdminFiatTransferUserIdZeroRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody(null, 0, null, "", null, "", null, null, ""),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов userWalletID = 0")
	void fetchAdminFiatTransferUserWalletIDZeroRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody(null, null, 0, "", null, "", null, null, ""),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов uesrPrivateName = \"\"")
	void fetchAdminFiatTransferUserPrivateNameEmptyRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody(null, null, null, "", null, "", null, null, ""),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов statusGroupList = \"\"")
	void fetchAdminFiatTransferStatusGroupListEmptyRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody(null, null, null, null, null, "", 1, null, null),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов limit = -1")
	void fetchAdminFiatTransferLimitMinusRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody(null, null, null, null, null, null, -1, null, null),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("FIAT: Запрос на просмотр трансферов offset = -1")
	void fetchAdminFiatTransferOffsetMinusRequest() {
		Response response = fiatMethods.fetchAdminFiatTransferNegativeStep(ADMIN,
				new FetchAdminFiatTransferBody(null, null, null, null, null, null, 1, -1, null),
				400
		);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@Description("Create FIAT deposit amount = 0")
	void createFiatDepositTestAmountZero() {
		Response response = fiatMethods.createFiatDepositNegative(BO, new FiatDeposit("0", 2879, 2), 400);

		assertEquals("Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@Description("Create FIAT deposit wallet = 0")
	void createFiatDepositTestWalletZero() {
		Response response = fiatMethods.createFiatDepositNegative(BO, new FiatDeposit("2500", 0, 2), 400);

		assertEquals("Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

}
