package core;

public class User {

	final private String login;
	final private String password;
	final private String pin;
	final private String token;

	public User(String login, String pass, String pin) {
		this.login = login;
		this.password = pass;
		this.pin = pin;
		this.token = "N/A";
	}

	private User(String login, String pass, String pin, String token) {
		this.login = login;
		this.password = pass;
		this.pin = pin;
		this.token = token;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getPin() {
		return pin;
	}

	public String getToken() {
		return token;
	}

	public User setToken(String token) {
		return new User(login, password, pin, token);
	}
}
