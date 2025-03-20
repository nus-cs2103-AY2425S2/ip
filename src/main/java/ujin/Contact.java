package ujin;

public class Contact {
    /**
     * The name of the person.
     */
    private String name;

    /**
     * The phone number of the person.
     */
    private String phoneNumber;

    /**
     * The constructor of the contact object.
     * @param name The name of the contact.
     * @param phoneNumber The number of the contact.
     */
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * The public method to get the name of the contact.
     * @return The name of the contact.
     */
    public String getName() {
        return name;
    }

    /**
     * The public method to get the number of the contact.
     * @return The number of the contact.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}