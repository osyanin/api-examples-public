package core;

import com.google.common.collect.ImmutableMap;
import helper.*;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import model.JsonBody;
import org.junit.jupiter.api.BeforeAll;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.restassured.RestAssured.given;

public class TestBase {

	public static final SiteConnection siteConnection = SiteConnection.load();
	protected static final SiteVariables vars = SiteVariables.generate();
	public static User BO = new User(siteConnection.getUserBo(),
			siteConnection.getUserBoPassword(),
			siteConnection.getUserBoPin()
	);
	protected static User OX = new User(siteConnection.getUserOx(),
			siteConnection.getUserOxPassword(),
			siteConnection.getUserOxPin()
	);
	protected static User ADMIN = new User(siteConnection.getAdmin(), siteConnection.getAdminPassword(), "N/A");
	protected static User TEST = new User(siteConnection.getUserTest(),
			siteConnection.getUserTestPassword(),
			siteConnection.getUserTestPin()
	);
	private final LocalDateTime ldt = LocalDateTime.now().plusDays(1);
	private final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
	protected final String formattedDateTomorrow = format.format(ldt);
	protected AdminMethods adminMethods = new AdminMethods();
	protected UserMethods userMethods = new UserMethods();
	protected FiatMethods fiatMethods = new FiatMethods();
	protected ExchangeMethods exchangeMethods = new ExchangeMethods();
	protected CashMethods cashMethods = new CashMethods();
	protected CryptoMethods cryptoMethods = new CryptoMethods();
	protected JsonBody jsonBody = new JsonBody();
	protected Random random = new Random();
	protected int MAX = 1000000000;
	protected int MIN = 100000;

	@BeforeAll
	public static void setUp() {
		given().config(RestAssured
				.config()
				.logConfig(LogConfig
						.logConfig()
						.enableLoggingOfRequestAndResponseIfValidationFails()
						.enablePrettyPrinting(true)));

		if (RestAssured.filters().isEmpty()) {
			RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
		}
	}

	@BeforeAll
	public static void gatherEnvVars() {
		System.getProperties().list(System.out);

		allureEnvironmentWriter(ImmutableMap
				.<String, String>builder()
				.put("API Url", siteConnection.getUrlApi())
				.put("OS", System.getProperty("os.name"))
				.put("Java Runtime Version", System.getProperty("java.runtime.version"))
				//.put("")
				.build(), System.getProperty("user.dir") + "/build/allure-results/");
	}

	public String extractSessionTokenFromResponse(Response response) {
		String sessionTokenValue = "No value";
		Pattern pattern = Pattern.compile("=(.*?);");
		Matcher matcher = pattern.matcher(response.header("set-cookie"));
		if (matcher.find()) {
			sessionTokenValue = matcher.group(1);
		}
		return sessionTokenValue;
	}

	@Step("")
	public Response loginExtractResponse(User user) {
		return given()
				.header("Content-Type", "application/json")
				.header("Fingerprint", "user fingerprint")
				.body(jsonBody.userCredentialsBodyJackson(user))
				.when()
				.log()
				.all()
				.post(siteConnection.getUrlApi() + "/user/auth/login")
				.then()
				.log()
				.all()
				.statusCode(200)
				.assertThat()
				.extract()
				.response();
	}

	@Step("Обновить токен залогинившимся пользователем {user.login}")
	public User updateUserToken(User user) {
		String sessionToken = extractSessionTokenFromResponse(loginExtractResponse(user));
		return user.setToken(sessionToken);
	}

	public User updateUserToken(User user, String token) {
		return user.setToken(token);
	}

	protected User updateAdminToken(User user) {
		String sessionToken = extractSessionTokenFromResponse(adminMethods.loginAsAdminExtractResponse(user));
		return user.setToken(sessionToken);
	}

	@BeforeAll
	public void logIn() {
		if (Objects.equals(BO.getToken(), "N/A")) {
			BO = updateUserToken(BO);
		}
		if (Objects.equals(OX.getToken(), "N/A")) {
			OX = updateUserToken(OX);
		}
		if (Objects.equals(TEST.getToken(), "N/A")) {
			TEST = updateUserToken(TEST);
		}
		if (Objects.equals(ADMIN.getToken(), "N/A")) {
			ADMIN = updateAdminToken(ADMIN);
		}
	}

    /*@AfterAll
    public static void sendTGmessage() {
        String tgBotApiKey = "192790167:AAFhT-IZzW7OqhdIjgl9FmFQgso4AmrmEdM";
        String chatID = "-100176472218";
        String allChats = "-1";
        String text = "";
        open("https://testres.1-go.online/allure-report/");
        String screenshotPath = screenshot("report");
        System.out.println("Filepath: " + screenshot("somename"));
        File file = null;
        if (screenshotPath != null) {
            file = new File(screenshotPath);
        }

        given().contentType("multipart/form-data").body(file).when().post(
                "https://api.telegram.org/bot" + tgBotApiKey + "/sendMessage?chat_id=" + chatID + "&text=Hello World"
        );

    }*/
}