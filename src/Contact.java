import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Contact {
    // Private fields (Encapsulation)
    private final String contactId;  // Made final since it shouldn't change
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String category;
    private final LocalDateTime dateCreated;  // Made final since it shouldn't change
    
    // Constructor
    public Contact(String firstName, String lastName, String phoneNumber, String email, String category) {
        this.contactId = generateId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.category = category;
        this.dateCreated = LocalDateTime.now();
    }
    
    // Generate unique ID
    private String generateId() {
        return "CONTACT_" + System.currentTimeMillis();
    }
    
    // Getters and Setters (Encapsulation)
    public String getContactId() { return contactId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getFullName() { return firstName + " " + lastName; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public LocalDateTime getDateCreated() { return dateCreated; }
    
    // toString method for display
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("ID: %s | Name: %s | Phone: %s | Email: %s | Category: %s | Created: %s", 
            contactId, getFullName(), phoneNumber, email, category, dateCreated.format(formatter));
    }
    
    // equals method for comparison
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return contactId.equals(contact.contactId);
    }
    
    // hashCode method (recommended when overriding equals)
    @Override
    public int hashCode() {
        return contactId.hashCode();
    }
}
