package first;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import java.util.concurrent.ThreadLocalRandom;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static jodd.util.MathUtil.randomInt;

public class ProbeSimulation extends Simulation {

	String token = "ff394436-8665-4849-a3cc-538702997b98";
	HttpRequestActionBuilder login = http("login")
			.post("/user/auth/login")
			.header("Content-Type", "application/json")
			.header("Fingerprint", "user fingerprint")
			.body(StringBody(
					"{\n" + "  \"username\": \"IlyaM\",\n" + "  \"password\": \"qwerty\",\n" + "  \"pinCode\": \"00000\"\n"
							+ "}"))
			.check(header("set-cookie").saveAs("token"));

	HttpRequestActionBuilder terraReg = http("Reg")
			.post("https://terraclub.bitrix24.ru/bitrix/services/main/ajax.php?action=crm.site.form.fill")
			.header("ContentT", "")
			.queryParam("action", "crm.site.form.fill")
			.formParam("values",
					"{\"CONTACT_NAME\":[\"Борис11\"],\"CONTACT_LAST_NAME\":[\"Осянин11\"],\"CONTACT_PHONE\":[\"+7 (925) 563-26-88\"],\"CONTACT_EMAIL\":[\"anonym_fh@mail.ru\"],\"DEAL_UF_CRM_1659357594\":[\"3682\"]}"
			)
			.formParam("properties", "{}")
			.formParam("consents", "{\"AGREEMENT_2\":\"Y\",\"AGREEMENT_20\":\"Y\"}\n")
			.formParam("recaptcha", "undefined")
			.formParam("timeZoneOffset", "-100")
			.formParam("id", randomInt(190, 50000))
			.formParam("sec", "ufflqk")
			.formParam("lang", "ru")
			.formParam("trace",
					"{\"url\":\"https://b24-ter3wc.bitrix24.site/crm_form_bdozi/?utm_source=40\",\"device\":{\"isMobile\":false},\"tags\":{\"ts\":1674568272,\"list\":{\"utm_source\":\"40\"},\"gclid\":null},\"client\":{},\"pages\":{\"list\":[[\"https://b24-ter3wc.bitrix24.site/crm_form_bdozi/?utm_source=40\",1674568273,\"Регистрация на 25 поток Наставничества T\"]]},\"gid\":\"5tph4metiauoqg4m0chb12ct33t3h7r3\",\"previous\":{\"list\":[]}}\n"
			)
			.formParam("entities", "[]")
			.formParam("security_sign", "undefined");

	HttpRequestActionBuilder terraQREnter = http("").get(
			"https://myterraclub.ru/b24qrcode/?deal_id=1893928&category_id=182&stage_target=UC_GBZ2FE&stage_transfer=EXECUTING");
	HttpRequestActionBuilder loginBO = http("login")
			.post("user/auth/login")
			.header("Content-Type", "application/json")
			.header("Fingerprint", "user fingerprint")
			.body(StringBody(
					"{\n" + "  \"username\": \"osyanin\",\n" + "  \"password\": \"1357924680\",\n" + "  \"pinCode\": \"16011\"\n"
							+ "}"))
			.check(header("set-cookie").saveAs("token"));

	HttpRequestActionBuilder loginJohnyPROD = http("login into PRODUCTION")
			.post("user/auth/login")
			.header("Content-Type", "application/json")
			.header("Fingerprint", "user fingerprint")
			.body(StringBody("{\n" + "  \"username\": \"osyanin\",\n" + "  \"password\": \"13579246801!aQ\",\n"
					+ "  \"pinCode\": \"16011\"\n" + "}"))
			.check(header("set-cookie").saveAs("token"));

	HttpRequestActionBuilder createFiatDepositBo = http("create_fiat_deposit")
			.post("/order/create_fiat_deposit")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.body(StringBody("{\n" + "\t\"amount\": \"2500\",\n" + "\t\"walletID\": 2879,\n" + "\t\"gatewayID\": 1\n" + "}"))
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	//.check(jsonPath("OrderID").ofInt()).check(jsonPath("OrderID").not("0"));

	HttpRequestActionBuilder createFiatDeposit = http("create_fiat_deposit")
			.post("/order/create_fiat_deposit")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.body(StringBody("{\n" + "\t\"amount\": \"2500\",\n" + "\t\"walletID\": 2879,\n" + "\t\"gatewayID\": 1\n" + "}"))
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	ScenarioBuilder createLink = scenario("Ho Ho Ho").exec(login).exec(createFiatDeposit);
	HttpRequestActionBuilder createFiatDepositJP = http("create_fiat_deposit")
			.post("/order/create_fiat_deposit")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.body(StringBody("{\n" + "\t\"amount\": \"2500\",\n" + "\t\"walletID\": 2587,\n" + "\t\"gatewayID\": 1\n" + "}"))
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	HttpRequestActionBuilder getUserExchangeRateRedisStress = http("getUserExchangeRateRedis")
			.post("/user/wallet/get_user_exchange_rate")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.body(StringBody(
					"{\n" + "\t\"currency_from_id\": 9,\n" + "\t\"currency_to_id\": 5,\n" + "\t\"amount\": 100\n" + "}"))
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	ScenarioBuilder redisStress = scenario("redisStress").exec(login).exec(getUserExchangeRateRedisStress);
	HttpRequestActionBuilder fetchUserBalance = http("postgresRead")
			.post("/user/wallet/fetch_user_balance")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	ScenarioBuilder postgresReadingStress1 = scenario("postgresReadingStress1").exec(login).exec(fetchUserBalance);
	HttpRequestActionBuilder fetchUserWalletsCrypto = http("postgresRead")
			.post("/user/wallet/fetch_user_wallets_crypto")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.body(StringBody("{\n" + "\t\"userWalletID\": 1167\n" + "}"))
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	ScenarioBuilder getPostgresReadingStress2 = scenario("").exec(login).exec(fetchUserWalletsCrypto);
	ScenarioBuilder allProtocolsEnabled = scenario("Get over here")
			.exec(loginBO)
			.exec(fetchUserBalance)
			.exec(fetchUserWalletsCrypto)
			.exec(getUserExchangeRateRedisStress)
			.exec(createFiatDepositBo);
	ScenarioBuilder readingAll = scenario("Get over here")
			.exec(loginBO)
			.exec(fetchUserBalance)
			.exec(fetchUserWalletsCrypto)
			.exec(getUserExchangeRateRedisStress);
	HttpRequestActionBuilder fetchUserWalletsCryptoJP = http("fetchUserWalletPostgres")
			.post("/user/wallet/fetch_user_wallets_crypto")
			.header("Content-Type", "application/json")
			.header("Authorization", token)
			.header("Fingerprint", "user fingerprint")
			.body(StringBody("{\n" + "\t\"userWalletID\": 2585\n" + "}"))
			.check(status().is(session -> 200 + ThreadLocalRandom.current().nextInt(1)));
	ScenarioBuilder productionFullTestJP = scenario("Production Full Test")
			.exec(loginJohnyPROD)
			.exec(fetchUserBalance)
			.exec(fetchUserWalletsCryptoJP)
			.exec(getUserExchangeRateRedisStress)
			.exec(createFiatDepositJP);
	HttpProtocolBuilder dev3 = http
			.baseUrl("https://dev3.1-go.online/api/")
			.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");
	HttpProtocolBuilder kube = http
			.baseUrl("https://kube.1-go.online/api/")
			.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");
	HttpProtocolBuilder dev1 = http
			.baseUrl("https://dev1.1-go.online/api/")
			.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");
	HttpProtocolBuilder prod = http
			.baseUrl("https://cb-gamma.com/api/")
			.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");
	ScenarioBuilder productionTest = scenario("Production Load Run").exec(loginJohnyPROD);
	ScenarioBuilder justLogin = scenario("simple login").exec(loginBO);
	ScenarioBuilder terraRegistrationScenario = scenario("Hello there").exec(terraReg);
	ScenarioBuilder terraQREntering = scenario("QR Mass").exec(terraQREnter);

	private final int users = Integer.parseInt(System.getProperty("users", "1"));

	{
		setUp(
				//readingAll.injectOpen(atOnceUsers(500)).protocols(kube));
				//createLink.injectOpen(atOnceUsers(750)).protocols(dev3));
				allProtocolsEnabled.injectOpen(atOnceUsers(users)).protocols(dev1));
				//justLogin.injectOpen(atOnceUsers(500)).protocols(kube));
				//terraRegistrationScenario.injectOpen(atOnceUsers(500)));
				//terraQREntering.injectOpen(atOnceUsers(125)));
				//redisStress.injectOpen(atOnceUsers(1)).protocols(dev1));
				//productionFullTestJP.injectOpen(atOnceUsers(1000)).protocols(prod));
	}
}
