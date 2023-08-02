package api.happy;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Acoount")
@Epic("Positive")
@Owner("Osyanin Boris")
public class AdminTests extends TestBase {

	@Test
	@DisplayName("Admin Roles verify")
	@Disabled
	void getAdminRolesTest() {
		ResponseBody<Response> response = adminMethods.getAdminRole(ADMIN);
		assertEquals("[4,1]", response.print(), "Expected constant values");
	}

	@Test
	@DisplayName("Смотрим балансы пользователя")
	@Disabled
	void fetchBalanceTest() {
		adminMethods.fetchUserBalanceByAdmin(ADMIN);
	}
}
