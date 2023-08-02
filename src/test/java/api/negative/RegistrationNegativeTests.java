package api.negative;

import core.TestBase;
import core.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Negative")
@Feature("Account")
@Feature("Registration")
@Owner("Osyanin Boris")
public class RegistrationNegativeTests extends TestBase {

	@Test
	@DisplayName("Регистрация: Без инвайт кода")
	void registrationTestV3PartOneNullInviteToken() {
		User newUser = new User(vars.getNewUser(), vars.getNewUserPassword(), vars.getNewUserPinCode());

		Response response = userMethods.initRegisterv3Negative(newUser, null, 400);
		assertEquals("Поле Инвайт Токен обязательно для заполнения", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: короткий инвайт-код")
	void registrationTestV3PartOneWrongSmallInviteToken() {
		User newUser = new User(vars.getNewUser(), vars.getNewUserPassword(), vars.getNewUserPinCode());

		Response response = userMethods.initRegisterv3Negative(newUser, vars.getNewUser(), 400);
		assertEquals("Поле Инвайт Токен должно иметь ровно 13 символов", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: длинный инвайт-код")
	void registrationTestV3PartOneWrongBigInviteToken() {
		User newUser = new User(vars.getNewUser(), vars.getNewUserPassword(), vars.getNewUserPinCode());

		Response response = userMethods.initRegisterv3Negative(newUser, vars.getBigInviteCode(), 400);
		assertEquals("Поле Инвайт Токен должно иметь ровно 13 символов", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: неправильный инвайт-код")
	void registrationTestV3PartOneWrongInviteToken() {
		User newUser = new User(vars.getNewUser() + "wi", vars.getNewUserPassword(), vars.getNewUserPinCode());

		Response response = userMethods.initRegisterv3Negative(newUser, vars.getInvalidInviteCode(), 403);
		assertEquals("Введен неверный инвайт-токен", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: пустой пароль")
	void registrationTestV3PartOneEmptyPassword() {
		User newUser = new User(vars.getNewUser() + "w", null, vars.getNewUserPinCode());

		String inviteToken = adminMethods.getInviteToken(ADMIN);

		Response response = userMethods.initRegisterv3Negative(newUser, inviteToken, 400);
		assertEquals("Поле Пароль обязательно для заполнения", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: пустой пин")
	void registrationTestV3PartOneEmptyPin() {
		User newUser = new User(vars.getNewUser() + "wr", vars.getNewUserPassword(), null);

		String inviteToken = adminMethods.getInviteToken(ADMIN);

		Response response = userMethods.initRegisterv3Negative(newUser, inviteToken, 400);
		assertEquals("Поле Пин-Код обязательно для заполнения", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: Длинный логин")
	void registrationTestV3PartOneLongName() {
		User newUser = new User(vars.getNewUser() + "longN", vars.getNewUserPassword(), vars.getNewUserPinCode());

		String inviteToken = adminMethods.getInviteToken(ADMIN);

		Response response = userMethods.initRegisterv3Negative(newUser, inviteToken, 400);
		assertEquals("Поле Логин должно иметь не более 12 символов", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: Короткий логин")
	void registrationTestV3PartOneShortName() {
		User newUser = new User("lol", vars.getNewUserPassword(), vars.getNewUserPinCode());

		String inviteToken = adminMethods.getInviteToken(ADMIN);

		Response response = userMethods.initRegisterv3Negative(newUser, inviteToken, 400);

		assertEquals("Поле Логин должно иметь не менее 5 символов", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Регистрация: Короткий логин")
	void registrationTestV3PartOneCharName() {
		User newUser = new User(vars.getNewUser() + "'@", vars.getNewUserPassword(), vars.getNewUserPinCode());

		String inviteToken = adminMethods.getInviteToken(ADMIN);

		Response response = userMethods.initRegisterv3Negative(newUser, inviteToken, 400);
		assertEquals("Введенное имя пользователя не соответствует критериям", response.getBody().path("cause"));
	}
}
