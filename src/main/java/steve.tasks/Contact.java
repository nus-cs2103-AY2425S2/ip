package steve.tasks;

/**
 * Represents a contact in a task management system.
 */
public class Contact extends Task {
    private String name;
    private String phone;
    private String email;

    /**
     * Constructs a Contact with the specified name.
     *
     * @param name The name of the contact.
     */
    public Contact(String name) {
        super(name);
        contactInitializer(validateDescription(name));
    }

    /**
     * Parses user description input into String array of name and contact number
     */
    public static String[] descriptionParser(String input) {
        String[] parts = input.split("/", 3);
        if (parts.length != 3) {
            throw new IllegalArgumentException(invalidFormatMessage());
        }
        return new String[]{parts[0].trim(), parts[1].trim(), parts[2].trim()};
    }

    /**
     * Initializes attributes for each contact object
     */
    public void contactInitializer(String[] parts) {
        this.name = parts[0];
        this.phone = validatePhone(parts[1]);
        this.email = parts[2];
    }

    /**
     * Validates a phone number.
     *
     * @param phone The phone number to validate.
     * @return The validated phone number if valid.
     * @throws IllegalArgumentException If the phone number is negative or does not have 8 digits.
     */
    public static String validatePhone(String phone) {
        int phoneNumLength = phone.length();
        if (Integer.parseInt(phone) < 0) { //Phone Number cannot be negative
            throw new IllegalArgumentException(negativePhoneNumMessage());
        } else if (phoneNumLength != 8) { //Phone number must have exactly 8 digits (Following SG HP Format)
            throw new IllegalArgumentException(invalidPhoneLengthMessage());
        }
        return phone;
    }

    /**
     * Checks if name, contact number and email are empty
     */
    public static String[] validateDescription(String description) {
        String[] parts = descriptionParser(description);
        if (parts[0].isEmpty()) {
            throw new IllegalArgumentException("Contact name cannot be empty.");
        } else if (parts[1].isEmpty()) {
            throw new IllegalArgumentException("Contact Number cannot be empty.");
        } else if (parts[2].isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        return parts;
    }

    //MESSAGE METHODS
    /**
     * Returns a success message when a contact is added.
     *
     * @return A message confirming the contact has been added.
     */
    public String messageString() {
        return "Successfully added to client list\n"
                + contactDetails();
    }

    /**
     * Returns individual contact details
     */
    public String contactDetails() {
        return "Contact Name: " + this.name
                + "\nPhone Number: " + this.phone
                + "\nEmail: " + this.email + "\n";
    }

    /**
     * Returns an error message for invalid phone number length.
     *
     * @return A message indicating the phone number length is incorrect.
     */
    public static String invalidPhoneLengthMessage() {
        return "Invalid phone number entered! Please enter an 8-digit phone number";
    }

    /**
     * Returns an error message for negative phone numbers.
     *
     * @return A message indicating the phone number cannot be negative.
     */
    public static String negativePhoneNumMessage() {
        return "Invalid phone number entered! Phone number cannot be negative";
    }

    /**
     * Returns an error message for invalid contact format.
     *
     * @return A message indicating the structure of command is incorrect.
     */
    public static String invalidFormatMessage() {
        return "Invalid format\n"
                + "Usage: contact <name> / <phone number> / <email> \n";
    }
}
