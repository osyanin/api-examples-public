package api.negative;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import java.util.ArrayList;
import model.body.FetchAdminExchange;
import model.body.FetchUserExchange;
import model.body.GetUserExchangeRate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Negative")
@Feature("Обмен")
@Owner("Osyanin Boris")
public class ExchangeNegativeTests extends TestBase {

	@Test
	@DisplayName("Обмен: get_exchange_details_admin OrderID = 0")
	void getExchangeDetailsZeroOrderIDAdminTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeAdminStep(ADMIN, 0, 400);

		assertEquals("Поле Ордер обязательно для заполнения",
				response.getBody().path("cause"),
				"Поле Ордер обязательно для заполнения"
		);
	}

	@Test
	@DisplayName("Обмен: get_exchange_details_admin OrderID = -1")
	void getExchangeDetailsMinusOneAdminTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeAdminStep(ADMIN, -1, 403);

		assertEquals("Ордер не найден или у вас нет прав на его изменение", response.getBody().path("cause"), "Ожидается ошибка минимальной суммы ввода");
	}

	@Test
	@DisplayName("Обмен: get_exchange_details_admin OrderID = 2147483646 - почти максимальное значение Int")
	void getExchangeDetailsMaxAdminTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeAdminStep(ADMIN, 2147483646,
				403);

		assertEquals("Ордер не найден или у вас нет прав на его изменение",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_exchange_details_admin OrderID = null")
	void getExchangeDetailsNullAdminTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeAdminStep(ADMIN, null, 400);

		assertEquals("Поле Ордер обязательно для заполнения",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_exchange_details_user Некорректный OrderID")
	void getExchangeDetailsOrderIdZeroUserTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeUserStep(BO, 0, 400);

		assertEquals("Поле Ордер обязательно для заполнения",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_exchange_details_user Некорректный OrderID")
	void getExchangeDetailsOrderIdMinusUserTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeUserStep(BO, -1, 403);

		assertEquals("Ордер не найден или у вас нет прав на его изменение",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_exchange_details_user Некорректный OrderID")
	void getExchangeDetailsOrderIdMaxUserTest() {
		Response response = exchangeMethods.getExchangeDetailsNegativeUserStep(BO, 2147483646, 403);

		assertEquals("Ордер не найден или у вас нет прав на его изменение",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyFrom = null")
	void getUserExchangeRateNegativeNullCurrencyFromTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO,
				new GetUserExchangeRate(null, 9, 1000),
				400
		);

		assertEquals("Поле Входящая валюта обязательно для заполнения",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyFrom = 0")
	void getUserExchangeRateNegativeZeroCurrencyFromTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO, new GetUserExchangeRate(0, 9, 1000), 400);

		assertEquals("Поле Входящая валюта обязательно для заполнения",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyFrom = -1")
	void getUserExchangeRateNegativeMinusCurrencyFromTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO, new GetUserExchangeRate(-1, 9, 1000), 404);

		assertEquals("Операция для данного тарифа недоступна",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyFrom = 2147483646")
	void getUserExchangeRateNegativeMaxCurrencyFromTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO,
				new GetUserExchangeRate(2147483646, 9, 1000),
				404
		);

		assertEquals("Операция для данного тарифа недоступна",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyTo = null")
	void getUserExchangeRateNegativeNullCurrencyToTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO,
				new GetUserExchangeRate(5, null, 1000),
				400
		);

		assertEquals("Поле Исходящая валюта обязательно для заполнения",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyTo = 0")
	void getUserExchangeRateNegativeZeroCurrencyToTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO, new GetUserExchangeRate(5, 0, 1000),
				400);

		assertEquals("Поле Исходящая валюта обязательно для заполнения",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyTo = -1")
	void getUserExchangeRateNegativeMinusCurrencyToTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO, new GetUserExchangeRate(5, -1, 1000),
				404);

		assertEquals("Операция для данного тарифа недоступна",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: get_user_exchange_rate currencyTo = 2147483646")
	void getUserExchangeRateNegativeMaxCurrencyToTest() {
		Response response = exchangeMethods.getUserExchangeRateNegativeStep(BO,
				new GetUserExchangeRate(5, 2147483646, 1000),
				404
		);

		assertEquals("Операция для данного тарифа недоступна",
				response.getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amount = null")
	void createExchangeNegativeNullAmountTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(null,
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Введенная сумма покупки должна быть положительной",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amount = -1")
	void createExchangeNegativeMinusAmountTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep("-1",
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Введенная сумма покупки должна быть положительной",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amount = 0")
	void createExchangeNegativeZeroAmountTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep("0",
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Введенная сумма покупки должна быть положительной",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amount = 21474836461111111")
	void createExchangeNegativeMaxAmountTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep("21474836461111111",
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		String orderID = extractOrderID.body().path("publicID");

		ADMIN = updateAdminToken(ADMIN);
		Response responseExchange = exchangeMethods.fetchAdminExchangeStep(new FetchAdminExchange(orderID,
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

		ArrayList<Integer> ID = responseExchange.body().path("data.ID");

		exchangeMethods.updateExchangeStatusStep(ID.get(0), 35, siteConnection.getTwoFactorPassword(), ADMIN);

		assertEquals("Произошла ошибка при расчете комиссии",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amountFrom = null")
	void createExchangeNegativeNullAmountFromTest() {
		exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		final double AMOUNT = 100;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				null,
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Введенная сумма продажи должна быть положительной",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amountFrom = -1")
	void createExchangeNegativeMinusAmountFromTest() {
		exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		final double AMOUNT = 100;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				"-1",
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Введенная сумма продажи должна быть положительной",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange amountFrom = 0")
	void createExchangeNegativeZeroAmountFromTest() {
		exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		final double AMOUNT = 100;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				"0",
				"flat_0",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Введенная сумма продажи должна быть положительной",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange rule = null")
	void createExchangeNegativeNullRuleTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				null,
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Произошла ошибка при расчете комиссии",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange rule = empty value")
	void createExchangeNegativeEmptyRuleTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Произошла ошибка при расчете комиссии",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange rule = chaos value")
	void createExchangeNegativeSomeLettersRuleTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"blahblahblahNoRulesOnlyAnarchy",
				2879,
				2877,
				BO,
				400
		);

		assertEquals("Произошла ошибка при расчете комиссии",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletFromID = null")
	void createExchangeNegativeNullWalletFromIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				null,
				2877,
				BO,
				400
		);

		assertEquals("Поле Счёт отправителя обязательно для заполнения",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletFromID = -1")
	void createExchangeNegativeMinusWalletFromIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				-1,
				2877,
				BO,
				404
		);

		assertEquals("Указанный счёт не найден",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletFromID = 0")
	void createExchangeNegativeZeroWalletFromIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				0,
				2877,
				BO,
				400
		);

		assertEquals("Поле Счёт отправителя обязательно для заполнения",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletFromID = 2147483646")
	void createExchangeNegativeMaxWalletFromIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				2147483646,
				2877,
				BO,
				404
		);

		assertEquals("Указанный счёт не найден",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletToID = null")
	void createExchangeNegativeNullWalletToIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				null,
				BO,
				400
		);

		assertEquals("Поле Счёт получателя обязательно для заполнения",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletToID = 0")
	void createExchangeNegativeZeroWalletToIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				0,
				BO,
				400
		);

		assertEquals("Поле Счёт получателя обязательно для заполнения",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletToID = -1")
	void createExchangeNegativeMinusWalletToIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				-1,
				BO,
				404
		);

		assertEquals("Указанный счёт не найден",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	@Test
	@DisplayName("Обмен: create_exchange walletToID = 2147483646")
	void createExchangeNegativeMaxWalletToIDTest() {
		Response response = exchangeMethods.getUserExchangeRateStep(new GetUserExchangeRate(5, 9, 1000), BO);

		String rateCalc = response.getBody().path("rate");
		final double AMOUNT = 100;
		double AmountFromCalculated = Double.parseDouble(rateCalc) * AMOUNT;

		Response extractOrderID = exchangeMethods.createExchangeStepNegativeStep(Double.toString(AMOUNT),
				Double.toString(AmountFromCalculated),
				"flat_0",
				2879,
				2147483646,
				BO,
				404
		);

		assertEquals("Указанный счёт не найден",
				extractOrderID.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}

	/*@Test
	@DisplayName("Обмен: fetch_user_exchange publicID = chaos value")
	void fetchUserExchangeNegativeStatusGroupList() {
		Response response = exchangeMethods.fetchUserExchangeNegativeStep(new FetchUserExchange(
				"2147483646btadvaergeqbtgfsfaferbgfsdsefargsfdsewafrfvdsEWFREBGFH",
				null,
				null,
				null,
				null,
				null,
				"null",
				"created_at_DESC",
				10,
				0
		), BO, 400);

		assertEquals("Ошибка данных",
				response.getBody().path("cause"),
				"Внутренние параметры создания ордера не совпали. Пожалуйста, попробуйте еще раз"
		);
	}*/
}