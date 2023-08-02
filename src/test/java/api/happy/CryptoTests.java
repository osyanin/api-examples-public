package api.happy;

import core.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import model.crypto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Crypto")
@Epic("Positive")
@Owner("Osyanin Boris")
public class CryptoTests extends TestBase {

	@Test
	@DisplayName("Fetch status group filter crypto user")
	void fetchStatusGroupFilterCryptoUserTest() {
		cryptoMethods.fetchStatusGroupFilterCryptoUserStep(BO);
	}

	@Test
	@DisplayName("Fetch status group filter crypto admin")
	void fetchStatusGroupFilterCryptoAdminTest() {
		cryptoMethods.fetchStatusGroupFilterCryptoAdminStep(ADMIN);
	}

	@Test
	@DisplayName("Crypto directions fetch")
	void cryptoDirectionsTest() {
		cryptoMethods.cryptoDirectionsStep(BO);
	}

	@Test
	@DisplayName("Fetch User Crypto Wallet")
	void fetchUserWalletCryptoTest() {
		cryptoMethods.fetchUserWalletCryptoStep(BO, new UserWalletID(2877));
	}

	@Test
	@DisplayName("Создание крипто-кошелька в блокчейне для нашего кошелька")
	void createUserWalletCryptoTest() {
		cryptoMethods.createUserWalletCryptoStep(BO, new UserWalletID(2877));
	}

	@Test
	@DisplayName("Показать 10 последних трансферов и их статус: Юзер")
	void fetchUserCryptoTransferTest() {
		cryptoMethods.fetchUserCryptoTransferStep(BO,
				new FetchUserCryptoTransfer(null, null, null, null, null, null, 10, 0, null)
		);
	}

	@Test
	@DisplayName("Показать 10 последних трансферов и их статус: Админ")
	void fetchAdminCryptoTransferTest() {
		cryptoMethods.fetchAdminCryptoTransferStep(ADMIN,
				new FetchAdminCryptoTransfer(null, null, null, null, null, null, null, null, 10, 0, "created_at_DESC")
		);
	}

	@Test
	@DisplayName("Показать данные о конкретном заказе: Пользователь")
	void getCryptoDetailsUser() {
		cryptoMethods.getCryptoDetailsUser(BO, new OrderID(1458));
	}

	@Test
	@DisplayName("Показать данные о конкретном заказе: Админ")
	void getCryptoDetailsAdmin() {
		cryptoMethods.getCryptoDetailsAdmin(ADMIN, new OrderID(1829));
	}

	@Test
	@DisplayName("Создать заявку на депозит крипты")
	void createCryptoDepositTest() {
		int randomWintNextIntWithinARange = random.nextInt(MAX - MIN) + MIN;

		String wallet = "TUTpUS1uizwdMAhSPQ5CzHR7xEgXaqmbWk";
		String transaction = "cf71f704576aa07e08362b46473c025dd1c7cd1f921d4591f5be7439f1c6fdc4";

		cryptoMethods.createCryptoDepositStep(BO,
				new CreateCryptoDeposit("101", wallet, 1, 2, 1, 453, transaction, randomWintNextIntWithinARange)
		);
	}
}