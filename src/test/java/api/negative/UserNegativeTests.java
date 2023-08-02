package api.negative;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Negative")
@Feature("Account")
@Owner("Osyanin Boris")
public class UserNegativeTests extends TestBase {

	@Test
	@DisplayName("Смена пароля: Некорректный старый пароль.")
	void changePasswordNegativeInputOldPasswordTest() {
		Response response = userMethods.changePasswordNegative(TEST,
				TEST.getPassword() + "1",
				TEST.getPassword() + "1",
				403
		);

		assertEquals("Введен неверный пароль", response.getBody().path("cause"), "Введен неверный пароль");
	}

	@Test
	@DisplayName("Смена пароля: пустой старый пароль.")
	void changePasswordNegativeEmptyOldPasswordTest() {
		Response response = userMethods.changePasswordNegative(TEST, null, TEST.getPassword(), 400);

		assertEquals("Поле Пароль обязательно для заполнения", response.getBody().path("cause"), "Введен неверный пароль");
	}

	@Test
	@DisplayName("Смена пароля: пустой новый пароль.")
	void changePasswordNegativeInputNewPasswordTest() {
		Response response = userMethods.changePasswordNegative(TEST, TEST.getPassword(), null, 400);

		assertEquals("Поле Новый пароль обязательно для заполнения",
				response.getBody().path("cause"),
				"Введен неверный пароль"
		);
	}

	@Test
	@DisplayName("Смена пароля: некорректный новый пароль. Только цифры")
	void changePasswordNegativeInputNewWrongPasswordTest() {
		Response response = userMethods.changePasswordNegative(TEST, TEST.getPassword(), "123456789", 400);

		assertEquals("Введенный пароль не соответствует критериям",
				response.getBody().path("cause"),
				"Введен неверный пароль"
		);
	}

	@Test
	@DisplayName("Смена пароля: некорректный новый пароль.Цифры маленькая буква и спецсимвол")
	void changePasswordNegativeInputNewWrongMarkPasswordTest() {
		Response response = userMethods.changePasswordNegative(TEST, TEST.getPassword(), "123456789!f", 400);

		assertEquals("Введенный пароль не соответствует критериям",
				response.getBody().path("cause"),
				"Введен неверный пароль"
		);
	}

	@Test
	@DisplayName("Смена пароля: некорректный новый пароль.Цифры маленькая буква и спецсимвол")
	void changePasswordNegativeInputNewWrongPasswordMinTest() {
		Response response = userMethods.changePasswordNegative(TEST, TEST.getPassword(), "1!fA", 400);

		assertEquals("Поле Новый пароль должно иметь не менее 6 символов",
				response.getBody().path("cause"),
				"Введен неверный пароль"
		);
	}

	@Test
	@DisplayName("Смена пина: пустой старый пин")
	void changePinNegativeNullTest() {
		Response response = userMethods.changePinNegativeStep(TEST, null, TEST.getPassword(), 400);

		assertEquals("Поле ПИН обязательно для заполнения", response.getBody().path("cause"), "Введен неверный пин");
	}

	@Test
	@DisplayName("Смена пина: короткий старый пин")
	void changePinNegativeMinimumTest() {
		Response response = userMethods.changePinNegativeStep(TEST, "1", TEST.getPassword(), 403);

		assertEquals("Введен неверный пин-код", response.getBody().path("cause"), "Введен неверный пин");
	}

	@Test
	@DisplayName("Смена пина: длинный старый пин")
	void changePinNegativeMaximumTest() {
		Response response = userMethods.changePinNegativeStep(TEST, "123456", TEST.getPin(), 403);

		assertEquals("Введен неверный пин-код", response.getBody().path("cause"), "Введен неверный пин");
	}


}
