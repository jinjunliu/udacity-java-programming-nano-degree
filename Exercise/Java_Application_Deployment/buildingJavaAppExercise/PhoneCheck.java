public class PhoneCheck implements ValidationStrategy{

	private static final String PHONE_PATTERN = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$";

	@Override
	public boolean validateUser(User user) {
		if (user.getPhoneNumber() != null) {
			return user.getPhoneNumber().matches(PHONE_PATTERN);
		}

		return false;
	}
}
