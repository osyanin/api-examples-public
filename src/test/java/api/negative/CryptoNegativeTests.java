package api.negative;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import model.crypto.FetchUserCryptoTransfer;
import model.crypto.OrderID;
import model.crypto.UserWalletID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Feature("Crypto")
@Epic("Negative")
@Owner("Osyanin Boris")
public class CryptoNegativeTests extends TestBase {

	@Test
	@DisplayName("Fetch User Crypto Wallet: walletID = 0")
	void fetchUserWalletCryptoTestNegative() {
		Response response = cryptoMethods.fetchUserWalletCryptoNegativeStep(BO, new UserWalletID(0), 400);

		assertEquals("Корректное сообщение об ошибке", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Показать 1 последний трансфер и его статус: nearAmount = \"null\"")
	void fetchUserCryptoTransferNullTest() {
		Response response = cryptoMethods.fetchUserCryptoTransferNegativeStep(BO,
				new FetchUserCryptoTransfer("-1", -1, -1, -1, "null", null, 1, 0, null),
				400
		);

		assertEquals("Корректное сообщение об ошибке", response.getBody().path("cause"));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/negativeInts.csv")
	@DisplayName("get_crypto_details_user negative")
	void getCryptoDetailsUserNegativeTest(Integer orderID) {
		Response response = cryptoMethods.getCryptoDetailsNegativeUser(BO, new OrderID(orderID), 400);

		assertEquals("Введены некорректные данные", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("get_crypto_details_user negative null")
	void getCryptoDetailsUserNegativeTest() {
		Response response = cryptoMethods.getCryptoDetailsNegativeUser(BO, new OrderID(null), 400);

		assertEquals("Введены некорректные данные", response.getBody().path("cause"));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/negativeInts.csv")
	@DisplayName("get_crypto_details_user negative")
	void getCryptoDetailsAdminNegativeTest(Integer orderID) {
		Response response = cryptoMethods.getCryptoDetailsNegativeAdmin(ADMIN, new OrderID(orderID), 400);

		assertEquals("Введены некорректные данные", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("get_crypto_details_user negative null")
	void getCryptoDetailsAdminNegativeTest() {
		Response response = cryptoMethods.getCryptoDetailsNegativeAdmin(ADMIN, new OrderID(null), 400);

		assertEquals("Введены некорректные данные", response.getBody().path("cause"));
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/negativeInts.csv")
	@DisplayName("Негативный тест Создания крипто-кошелька в блокчейне для нашего кошелька")
	void createUserWalletCryptoNegativeTest(int userWalletID) {
		Response response = cryptoMethods.createUserWalletCryptoNegativeStep(BO, new UserWalletID(userWalletID), 400);

		assertEquals("Введены некорректные данные", response.getBody().path("cause"));
	}

	@Test
	@DisplayName("Негативный тест Создания крипто-кошелька в блокчейне для нашего кошелька: null")
	void createUserWalletCryptoNegativeTest() {
		Response response = cryptoMethods.createUserWalletCryptoNegativeStep(BO, new UserWalletID(null), 400);

		assertEquals("Введены некорректные данные", response.getBody().path("cause"));
	}


}
