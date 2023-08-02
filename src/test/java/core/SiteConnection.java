package core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class SiteConnection {

	private static final Logger logger = LoggerFactory.getLogger(SiteConnection.class);
	private String urlApi;
	private String urlWeb;
	private String userOx;
	private String userOxPassword;
	private String userOxPin;
	private String userBo;
	private String userBoPassword;
	private String userBoPin;
	private String userTest;
	private String userTestPassword;
	private String userTestPin;
	private String admin;
	private String adminPassword;
	private String twoFactorPassword;

	private static Properties loadProperties() {
		Properties p = new Properties();
		try {
			p.load(Files.newInputStream(Paths.get("conf/" + System.getProperty("conf", "DEV") + ".properties")));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return p;
	}

	public static SiteConnection load() {
		Properties props = loadProperties();
		SiteConnection conn = new SiteConnection();

		conn.urlApi = props.getProperty("urlApi");
		conn.urlWeb = props.getProperty("urlWeb");

		conn.userOx = props.getProperty("userOx");
		conn.userOxPassword = props.getProperty("userOxPassword");
		conn.userOxPin = props.getProperty("userOxPin");

		conn.userBo = props.getProperty("userBo");
		conn.userBoPassword = props.getProperty("userBoPassword");
		conn.userBoPin = props.getProperty("userBoPin");

		conn.userTest = props.getProperty("userTest");
		conn.userTestPassword = props.getProperty("userTestPassword");
		conn.userTestPin = props.getProperty("userTestPin");

		conn.admin = props.getProperty("admin");
		conn.adminPassword = props.getProperty("adminPassword");
		conn.twoFactorPassword = props.getProperty("twoFactorPassword");

		return conn;
	}

	public String getUrlApi() {
		return this.urlApi;
	}

	public String getUrlWeb() {
		return this.urlWeb;
	}

	public String getUserOx() {
		return this.userOx;
	}

	public String getUserOxPassword() {
		return this.userOxPassword;
	}

	public String getUserOxPin() {
		return this.userOxPin;
	}

	public String getUserBo() {
		return this.userBo;
	}

	public String getUserBoPassword() {
		return this.userBoPassword;
	}

	public String getUserBoPin() {
		return this.userBoPin;
	}

	public String getUserTest() {
		return this.userTest;
	}

	public String getUserTestPassword() {
		return this.userTestPassword;
	}

	public String getUserTestPin() {
		return this.userTestPin;
	}

	public String getAdmin() {
		return this.admin;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public String getTwoFactorPassword() {
		return this.twoFactorPassword;
	}
}