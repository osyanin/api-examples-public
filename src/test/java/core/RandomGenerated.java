package core;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

class RandomGenerated {

	private final Random rnd = new Random();
	private final StringBuilder salt = new StringBuilder();

	public String stringValue(int length) {
		{
			while (salt.length() < length) {
				String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return salt.toString();
	}

	public String randomTgName(int length) {
		{
			while (salt.length() < length) {
				String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return "@" + salt.toString();
	}

	public String cyryllicUpperCase(int length) {
		{
			while (salt.length() < length) {
				String SALTCHARS = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЭЮЯ";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return salt.toString();
	}

	public String cyryllicLowerCase(int length) {
		{
			while (salt.length() < length) {
				String SALTCHARS = "абвгдеёжзийклмнопрстуфхцчшщыэюя";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return salt.toString();
	}


	public String phoneNumber888() {
		int length = 5;
		{
			while (salt.length() < length) {
				String SALTCHARS = "1234567890";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return "88812" + salt.toString();
	}

	public String phoneNumber() {
		int length = 9;
		{
			while (salt.length() < length) {
				String SALTCHARS = "1234567890";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return "9" + salt.toString();
	}

	public String number(int length) {
		{
			while (salt.length() < length) {
				String SALTCHARS = "1234567890";
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
		}
		return salt.toString();
	}

	public String getPassword(int length) {

		final int MIN_LENGTH = 8;
		final int MAX_LENGTH = 12;

		if (length == 0) {
			length = MIN_LENGTH + rnd.nextInt(MAX_LENGTH - MIN_LENGTH);
		}
		int individualLength = Math.round(((float) length) / 3);
		int symbolLength = length - (2 * individualLength);

		String password =
				RandomStringUtils.randomAlphabetic(individualLength) + RandomStringUtils.randomNumeric(individualLength)
						+ RandomStringUtils.random(symbolLength, "!@#%^*&");

		//logger.debug("Pwd is: {}", password);
		System.out.println("Pwd is: {}" + password);
		return password;
	}

}
