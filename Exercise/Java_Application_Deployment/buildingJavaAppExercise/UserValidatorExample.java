public class UserValidatorExample {
	public static void main(String[] args) {
		UserValidator validator = new UserValidator();

		User user = new User("user[at]invalid-email.com", "+1 708-868-9779");

		validator.setValidationStrategy(new EmailCheck());
		System.out.println(validator.validateUser(user));
		// Behavior can be dynamically changed at runtime. This time the user will be valid.
		validator.setValidationStrategy(new PhoneCheck());
		System.out.println(validator.validateUser(user));

		User secondUser = new User("user@validemail.com", null);

		validator.setValidationStrategy(new EmailCheck());
		System.out.println(validator.validateUser(secondUser));
		validator.setValidationStrategy(new PhoneCheck());
		System.out.println(validator.validateUser(secondUser));
	}
}
