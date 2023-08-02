package api.negative;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.cash.CreateCashTransfer;
import model.cash.GetAvailableDateSlots;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Negative")
@Feature("Работа с наличными")
@Owner("Osyanin Boris")
public class CashNegativeTests extends TestBase {

	@Test
	@DisplayName("Тест создания заявки на ввод наличных с меньшим, чем дОлжно значением.")
	void createCashTransferWithNotLowerThanMinimumTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(4999,
							1,
							1,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Введенная сумма меньше минимальной. Пожалуйста, введите большую сумму.",
				response[0].getBody().path("cause"),
				"Ожидается ошибка минимальной суммы ввода"
		);
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных больше, чем баланс.")
	void createCashTransferWithMoreThanBalanceTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(2147483646,
							1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Недостаточно средств на балансе счета",
				response[0].getBody().path("cause"),
				"Ожидается ошибка нехватки баланса"
		);
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных с отрицательным значением")
	void createCashTransferWithNegativeAmountTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(-1,
							1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Введенная сумма должна быть положительной.",
				response[0].getBody().path("cause"),
				"Обработка некорректного значения"
		);
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в нулевом офисе, которого нет")
	void createCashTransferWithWrongVaultZeroTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							0,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Поле Касса обязательно для заполнения", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в офисе, которого нет")
	void createCashTransferWithWrongVaultMaxTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							2147483646,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					404
			);
		});

		assertEquals("Такая касса не найдена", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в отрицательном номере офиса, которого нет")
	void createCashTransferWithWrongVaultNegativeTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							-1,
							2,
							2879,
							meetTimeFrom,
							meetTimeTo,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					404
			);
		});

		assertEquals("Такая касса не найдена", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных с нулевого кошелька, которого нет")
	void createCashTransferWithWrongWalletZeroTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							0,
							meetTimeFrom,
							meetTimeTo,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Поле Счет обязательно для заполнения", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных с нулевого кошелька, которого нет")
	void createCashTransferWithWrongWalletIntMaxTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							2147483646,
							meetTimeFrom,
							meetTimeTo,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					404
			);
		});

		assertEquals("Указанный счёт не найден", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных с отрицательного кошелька, которого нет")
	void createCashTransferWithWrongWalletIntNegativeTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							-1,
							meetTimeFrom,
							meetTimeTo,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					404
			);
		});

		assertEquals("Указанный счёт не найден", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в некорректное время: 0")
	void createCashTransferWithWrongMeetTimeFromZeroTest() {
		final Response[] response = new Response[1];

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							2879,
							0,
							0,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Поле Конец встречи обязательно для заполнения", response[0].getBody().path("cause"), "Ожидается ошибка Vault not found");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в некорректное время: -1")
	void createCashTransferWithWrongMeetTimeFromNegativeTest() {
		final Response[] response = new Response[1];

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							2879,
							-1,
							-1,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Невозможно назначить встречу на указанное время/дату", response[0].getBody().path("cause"), "Ожидается ошибка");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в некорректное время: текущее -1")
	void createCashTransferWithWrongMeetTimeFromRandomTimeTest() {
		final Response[] meetTime = new Response[1];
		final Response[] response = new Response[1];

		step("Получение доступных слотов для встречи", () -> {
			meetTime[0] = cashMethods.getAvailableDateSlotsStep(BO, new GetAvailableDateSlots(formattedDateTomorrow, 1));
		});

		int meetTimeFrom = meetTime[0].path("meetTimeFrom[0]");
		int meetTimeTo = meetTime[0].path("meetTimeTo[0]");

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							2879,
							meetTimeFrom - 1,
							meetTimeTo - 1,
							"",
							new Integer[]{1},
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Выбранное время встречи уже занято. Пожалуйста, выберите другое время", response[0].getBody().path("cause"), "Ожидается ошибка");
	}

	@Test
	@DisplayName("Тест создания заявки на вывод наличных в некорректное время: MaxInt")
	void createCashTransferWithWrongMeetTimeMaxTest() {
		final Response[] response = new Response[1];

		step("Создание заявки на получение наличных", () -> {
			response[0] = cashMethods.createCashTransferNegativeStep(BO,
					new CreateCashTransfer(1000,
							1,
							2,
							2879,
							2147483646,
							2147483646,
							"",
							null,
							new model.cash.Auth(siteConnection.getTwoFactorPassword(), BO.getPin()),
							false
					),
					400
			);
		});

		assertEquals("Выбранное время встречи уже занято. Пожалуйста, выберите другое время", response[0].getBody().path("cause"), "Ожидается ошибка");
	}
}
