package ContactManagementApplication;

/**
 *
 * @author Guillermo
 */
import java.util.*;

public class ContactDirectory {
    private List<Contact> contacts; // ArrayList
    private Set<String> contactTypes; // HashSet
    private Map<String, Contact> contactMap; // HashMap

    public ContactDirectory() {
        contacts = new ArrayList<>();
        contactTypes = new HashSet<>();
        contactMap = new HashMap<>();
    }

    // Add a new contact
    public void addContact(Contact contact) {
        contacts.add(contact);
        contactMap.put(contact.getName(), contact);
        contactTypes.add(contact.getContactType());
    }

    // Update existing contact by name
    public void updateContact(String name, String phoneNumber, String email, String contactType) {
        Contact contact = contactMap.get(name);
        if (contact != null) {
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contact.setContactType(contactType);
            contactTypes.add(contactType);
            System.out.println("Contact updated successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Display all contacts
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    // Display contacts sorted by name
    public void displaySortedContacts() {
        contacts.sort(Comparator.comparing(Contact::getName));
        System.out.println("\n--- Contacts Sorted by Name ---");
        displayContacts();
    }

    // Display unique contact types
    public void displayContactTypes() {
        System.out.println("\n--- Unique Contact Types ---");
        for (String type : contactTypes) {
            System.out.println(type);
        }
    }

    // Search for a contact by name
    public void searchContactByName(String name) {
        Contact contact = contactMap.get(name);
        if (contact != null) {
            System.out.println("Contact found: " + contact);
        } else {
            System.out.println("Contact not found.");
        }
    }
}
