package api.happy;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.cash.*;
import model.cash.minimal.otp.Auth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Feature("Работа с наличными")
@Epic("Positive")
@Owner("Osyanin Boris")
public class CashTests extends TestBase {

	@Test
	void getCashDirectionsTest() {
		cashMethods.getCashDirectionsStep(BO);
	}

	@Test
	void fetchNewCashTransferTest() {
		step("Получить список новых транзакций налички: {user.login}", () -> {
			cashMethods.fetchForNewCashTransferStep(BO);
		});
	}

	@Test
	@DisplayName("Проверка доступных дат в Vault 1")
	void getAvailableDateSlotsTest() {
		step("Проверка доступных дат в {getAvailableDateSlots.vaultID}", () -> {
			cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

	}

	@Test
	void fetchUserCashTransferTest() {
		step("Проверка что список заявок на вывод не пуст с возможностью фильтрации: Пользователь {user.login}", () -> {
			cashMethods.fetchUserCashTransferStep(BO,
					new FetchUserCashTransfer(null, null, null, null, null, null, null, null, null, 10, 0, null)
			);
		});
	}

	@Test
	@DisplayName("Проверка что список заявок на вывод не пуст с возможностью фильтрации: Админ {user.login}")
	void fetchAdminCashTransferTest() {
		cashMethods.fetchAdminCashTransferStep(ADMIN,
				new FetchAdminCashTransfer(null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						null,
						5,
						0,
						"created_at_DESC"
				)
		);
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в офисе Башни, с последующей отменой пользователем")
	void createCashTransferAndDeclineByUserTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];
		final Response[] extractedID = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		final int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		final int meetTimeTo = meetTime[0].path("meetTimeTo[0]");
		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferStep(BO,
					new CreateCashTransfer(5000,
							1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					)
			);
		});

		String publicID = response[0].path("publicID");
		step("Вывод ID используя publicID", () -> {
			extractedID[0] = cashMethods.fetchUserCashTransferStep(BO,
					new FetchUserCashTransfer(publicID, null, null, null, null, null, null, null, null, 10, 0, null)
			);
		});

		Integer orderID = extractedID[0].path("data[0].ID");

		step("Смена суммы вывода. ", () -> {
			cashMethods.updateCashTransferAmountStep(ADMIN,
					new UpdateCashTransferAmount(orderID, 5500, 16, "changed", new Auth(siteConnection.getTwoFactorPassword()))
			);
		});

		meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));

		final int changedMeetTimeFrom = meetTime[0].path("meetTimeFrom[1]");
		final int changedMeetTimeTo = meetTime[0].path("meetTimeTo[1]");

		step("Смена времени встречи", () -> {
			cashMethods.updateCashTransferDetailsAdminStep(ADMIN,
					new UpdateCashTransferDetailsAdmin(orderID, 1, 0, changedMeetTimeFrom, changedMeetTimeTo, "")
			);
		});

		step("Отмена заявки пользователем", () -> {
			cashMethods.updateCashTransferStatusUserCancelled(BO,
					new model.cash.minimal.pin.Order(orderID, new model.cash.minimal.pin.Auth(BO.getPin()))
			);
		});
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в офисе Башни, с последующей отменой банком")
	void createCashTransferDeclineByBankTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];
		final Response[] extractedID = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");
		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferStep(BO,
					new CreateCashTransfer(5000,
							1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					)
			);
		});

		String publicID = response[0].path("publicID");
		step("Вывод ID используя publicID", () -> {
			extractedID[0] = cashMethods.fetchUserCashTransferStep(BO,
					new FetchUserCashTransfer(publicID, null, null, null, null, null, null, null, null, 10, 0, null)
			);
		});

		Integer orderID = extractedID[0].path("data[0].ID");
		step("Отмена заявки по причине отмены банком", () -> {
			cashMethods.updateCashTransferStatusStep(ADMIN, new UpdateCashTransferStatus(orderID, 14, ""));
		});
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в офисе Башни, с последующей отменой админом, ручка для отмены статуса.")
	void createCashTransferDeclineByBankAnotherEndpointTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];
		final Response[] extractedID = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");
		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferStep(BO,
					new CreateCashTransfer(5000,
							1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					)
			);
		});

		String publicID = response[0].path("publicID");
		step("Вывод ID используя publicID", () -> {
			extractedID[0] = cashMethods.fetchUserCashTransferStep(BO,
					new FetchUserCashTransfer(publicID, null, null, null, null, null, null, null, null, 10, 0, null)
			);
		});

		Integer orderID = extractedID[0].path("data[0].ID");

		step("Отмена заявки по причине отмены банком", () -> {
			cashMethods.updateCashTransferExecution(ADMIN,
					new UpdateCashTransferExecution(orderID, 14, siteConnection.getTwoFactorPassword(), "")
			);
		});
	}

	@Test
	@DisplayName("Проверка возможных действий с заявкой: Пользователь")
	void getCashTransferDetailsUserTest() {
		cashMethods.getCashTransferDetailsUserStep(BO, new OrderID(3009568));
	}

	@Test
	@DisplayName("Проверка возможных действий с заявкой: Админ")
	void getCashTransferDetailsAdminTest() {
		cashMethods.getCashTransferDetailsAdminStep(ADMIN, new OrderID(3009568));
	}

	@Test
	@DisplayName("Успешное завершение заявки на вывод наличных")
	void doneCashTransferTest() {

		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];
		final Response[] extractedID = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");
		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferStep(BO,
					new CreateCashTransfer(5000,
							1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					)
			);
		});

		String publicID = response[0].path("publicID");

		step("Вывод ID используя publicID", () -> {
			extractedID[0] = cashMethods.fetchUserCashTransferStep(BO,
					new FetchUserCashTransfer(publicID, null, null, null, null, null, null, null, null, 10, 0, null)
			);
		});

		Integer ID = extractedID[0].path("data[0].ID");

		cashMethods.doneCashTransfer(ADMIN,
				new DoneCashTransfer(ID,
						14,
						"f8af15469b937dfc5b1982df7883a4521ecbdec920c92bcb66610cebe0c929356116a1941049aca770811c4e8724b652aad2f376ac84ab51b95cbf837281c4888c52a2699624de845670ca190b1aabd3",
						""
				)
		);
	}
}

