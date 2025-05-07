package ContactManagementApplication;

/**
 *
 * @author Guillermo
 */
public class Contact {
    private int contactId;
    private String name;
    private String phoneNumber;
    private String email;
    private String contactType; // e.g., "Personal" or "Professional"

    public Contact(int contactId, String name, String phoneNumber, String email, String contactType) {
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactType = contactType;
    }

    // Getters and Setters
    public int getContactId() { return contactId; }
    public String getName() { return name; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getContactType() { return contactType; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String email) { this.email = email; }
    public void setContactType(String contactType) { this.contactType = contactType; }

    @Override
    public String toString() {
        return "ContactId: " + contactId + ", Name: " + name + ", Phone: " + phoneNumber + 
               ", Email: " + email + ", Type: " + contactType;
    }
}

