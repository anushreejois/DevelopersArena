import java.util.*;
import java.util.stream.Collectors;

public class ContactManager {
    // Use HashMap for fast lookups and ArrayList for ordered display
    private final Map<String, Contact> contactsById;
    private final List<Contact> contactsList;
    
    // Constructor
    public ContactManager() {
        this.contactsById = new HashMap<>();
        this.contactsList = new ArrayList<>();
    }
    
    // Add a new contact
    public boolean addContact(String firstName, String lastName, String phoneNumber, String email, String category) {
        // Input validation
        if (firstName == null || firstName.trim().isEmpty()) {
            System.out.println("❌ Error: First name is required!");
            return false;
        }
        
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.out.println("❌ Error: Phone number is required!");
            return false;
        }
        
        // Check for duplicate phone numbers
        if (isPhoneNumberExists(phoneNumber)) {
            System.out.println("❌ Error: Contact with this phone number already exists!");
            return false;
        }
        
        // Create new contact
        Contact newContact = new Contact(firstName, lastName, phoneNumber, email, category);
        
        // Add to both data structures
        contactsById.put(newContact.getContactId(), newContact);
        contactsList.add(newContact);
        
        System.out.println("✅ Contact added successfully! ID: " + newContact.getContactId());
        return true;
    }
    
    // Check if phone number already exists
    private boolean isPhoneNumberExists(String phoneNumber) {
        return contactsList.stream()
                .anyMatch(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }
    
    // Search contacts by name, phone, or email
    public List<Contact> searchContacts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchTerm = query.toLowerCase().trim();
        
        return contactsList.stream()
                .filter(contact -> 
                    contact.getFullName().toLowerCase().contains(searchTerm) ||
                    contact.getPhoneNumber().contains(searchTerm) ||
                    (contact.getEmail() != null && contact.getEmail().toLowerCase().contains(searchTerm))
                )
                .collect(Collectors.toList());
    }
    
    // Get all contacts
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contactsList);
    }
    
    // Get contacts by category
    public List<Contact> getContactsByCategory(String category) {
        return contactsList.stream()
                .filter(contact -> contact.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    // Delete contact by ID
    public boolean deleteContact(String contactId) {
        Contact contact = contactsById.get(contactId);
        if (contact == null) {
            System.out.println("❌ Error: Contact not found!");
            return false;
        }
        
        contactsById.remove(contactId);
        contactsList.remove(contact);
        
        System.out.println("✅ Contact deleted successfully: " + contact.getFullName());
        return true;
    }
    
    // Update contact information
    public boolean updateContact(String contactId, String firstName, String lastName, String phoneNumber, String email, String category) {
        Contact contact = contactsById.get(contactId);
        if (contact == null) {
            System.out.println("❌ Error: Contact not found!");
            return false;
        }
        
        // Check if new phone number conflicts with existing contacts
        if (!contact.getPhoneNumber().equals(phoneNumber) && isPhoneNumberExists(phoneNumber)) {
            System.out.println("❌ Error: Another contact with this phone number already exists!");
            return false;
        }
        
        // Update contact details
        if (firstName != null && !firstName.trim().isEmpty()) {
            contact.setFirstName(firstName);
        }
        if (lastName != null && !lastName.trim().isEmpty()) {
            contact.setLastName(lastName);
        }
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            contact.setPhoneNumber(phoneNumber);
        }
        if (email != null) {
            contact.setEmail(email);
        }
        if (category != null && !category.trim().isEmpty()) {
            contact.setCategory(category);
        }
        
        System.out.println("✅ Contact updated successfully: " + contact.getFullName());
        return true;
    }
    
    // Get contact count
    public int getContactCount() {
        return contactsList.size();
    }
    
    // Display all contacts in a formatted way
    public void displayAllContacts() {
        if (contactsList.isEmpty()) {
            System.out.println("📭 No contacts found.");
            return;
        }
        
        System.out.println("\n📊 All Contacts (" + contactsList.size() + " total):");
        System.out.println("=" + "=".repeat(80));
        
        for (Contact contact : contactsList) {
            System.out.println(contact);
        }
        System.out.println("=" + "=".repeat(80));
    }
    
    // Display contacts by category
    public void displayContactsByCategory() {
        Map<String, List<Contact>> categoryGroups = contactsList.stream()
                .collect(Collectors.groupingBy(Contact::getCategory));
        
        if (categoryGroups.isEmpty()) {
            System.out.println("📭 No contacts found.");
            return;
        }
        
        System.out.println("\n📂 Contacts by Category:");
        System.out.println("=" + "=".repeat(50));
        
        for (Map.Entry<String, List<Contact>> entry : categoryGroups.entrySet()) {
            System.out.println("\n📁 " + entry.getKey().toUpperCase() + " (" + entry.getValue().size() + " contacts):");
            for (Contact contact : entry.getValue()) {
                System.out.println("  • " + contact.getFullName() + " - " + contact.getPhoneNumber());
            }
        }
        System.out.println("=" + "=".repeat(50));
    }
}
