package security;

public class DivideNameAndPassword {
	public static String[] NameAndPassword(String input) {
		String[] nameAndPassword = new String[2];
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '|') {
				String password = input.substring(0, i);
				String name = input.substring(i + 1);
				nameAndPassword[0] = name;
				nameAndPassword[1] = password;
			}
		}
		return nameAndPassword;
	}
}
