package api.happy;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import core.TestBase;
import core.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Acoount")
@Epic("Positive")
@Owner("Osyanin Boris")
class UserTests extends TestBase {

	@Test
	@DisplayName("getUserData: BO")
	void getUserProfileTest() {
		ResponseBody<Response> response = userMethods.getUserDataBody(BO);
		assertAll("Profile",
				() -> assertEquals("osyanin", response.path("username"), "username"),
				() -> assertEquals("ogionfrost", response.path("tgUsername"), "tgUsername")
		);
	}

	@Test
	@DisplayName("Logout test: OX")
	void getUserProfileTestAndLogout() {
		ResponseBody<Response> response = userMethods.getUserDataBody(OX);
		assertAll("Profile", () -> assertEquals("oxxxymiron", response.path("username"), "login"));
		userMethods.logout(OX);
		userMethods.getUserDataBodyAfterLogout(OX);
	}

	@Test
	@DisplayName("Password change")
	void changePasswordTest() {
		userMethods.changePassword(TEST, TEST.getPassword(), TEST.getPassword() + "1");
		userMethods.changePassword(TEST, TEST.getPassword() + "1", TEST.getPassword());
	}

	@Test
	@DisplayName("PinCode change")
	void changePinCodeTest() {
		userMethods.changePinStep(TEST, TEST.getPin(), "12345");
		userMethods.changePinStep(TEST, "12345", TEST.getPin());
	}

	@Test
	@DisplayName("TgUsernameChange")
	void changeTgUsernameTest() {
		userMethods.changeTgUsername(TEST, "SomeNewTG", true);
		ResponseBody<Response> mainTgAfterFirstChange = userMethods.getUserDataBody(TEST);
		userMethods.changeTgUsername(TEST, "SomeNewTG11", true);
		ResponseBody<Response> mainTgAfterSecondChange = userMethods.getUserDataBody(TEST);

		assertAll("Profile",
				() -> assertEquals("SomeNewTG", mainTgAfterFirstChange.path("tgUsername"), "tgUsername"),
				() -> assertEquals("SomeNewTG11", mainTgAfterSecondChange.path("tgUsername"), "tgUsername")
		);
	}

	@Test
	@DisplayName("Registration of new user part one")
	void registrationTestV3PartOne() {
		User newUser = new User(vars.getNewUser(), vars.getNewUserPassword(), vars.getNewUserPinCode());

		ADMIN = updateAdminToken(ADMIN);
		String inviteToken = adminMethods.getInviteToken(ADMIN);

		ResponseBody<Response> responseBody = userMethods.initRegisterv3(newUser, inviteToken);
		String regToken = responseBody.path("sessionKey");

		newUser = updateUserToken(newUser, regToken);
		ResponseBody<Response> getProfileFromNewUser = userMethods.getUserDataBodyAfterRegistrationPartOne(newUser);

		User finalNewUser = newUser;
		assertAll("registered user data",
				() -> assertEquals(finalNewUser.getLogin(), getProfileFromNewUser.path("username"), "checking username")
		);
	}

	@Test
	@DisplayName("Registration Part One + Finalization")
	void registrationTestV3PartTwo() {
		User newUser = new User(vars.getNewUserAnother(), vars.getNewUserPassword(), vars.getNewUserPinCode());

		String inviteToken = adminMethods.getInviteToken(ADMIN);

		ResponseBody<Response> responseBody = userMethods.initRegisterv3(newUser, inviteToken);
		String regToken = responseBody.path("sessionKey");

		newUser = updateUserToken(newUser, regToken);

		userMethods.finishRegistration(newUser,
				vars.getNewUser(),
				vars.getNewUser(),
				1,
				vars.getNewUser(),
				vars.getNewUser(),
				vars.getNewUserMail()
		);

		ResponseBody<Response> getProfileFromNewUser = userMethods.getUserDataBody(newUser);

		User finalNewUser = newUser;
		assertAll("registered user data",
				() -> assertEquals(finalNewUser.getLogin(), getProfileFromNewUser.path("username"), "checking username"),
				() -> assertEquals(2, (Integer) getProfileFromNewUser.path("statusID"), "checking statusID"),
				() -> assertEquals("Обычный", getProfileFromNewUser.path("status"), "status"),
				() -> assertEquals(vars.getNewUser(), getProfileFromNewUser.path("tgUsername"), "tgUsername")
		);
	}
}