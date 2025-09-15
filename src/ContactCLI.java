import java.util.*;

public class ContactCLI {
    private final ContactManager manager;
    private final Scanner scanner;
    
    public ContactCLI() {
        this.manager = new ContactManager();
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        try (scanner) {
            System.out.println("🚀 Welcome to Contact Management System!");
            System.out.println("=" + "=".repeat(45));
            
            boolean running = true;
            while (running) {
                displayMainMenu();
                int choice = getMenuChoice();
                
                switch (choice) {
                    case 1 -> addNewContact();
                    case 2 -> searchContacts();
                    case 3 -> displayAllContacts();
                    case 4 -> displayByCategory();
                    case 5 -> updateContact();
                    case 6 -> deleteContact();
                    case 0 -> {
                        System.out.println("👋 Thank you for using Contact Management System!");
                        running = false;
                    }
                    default -> System.out.println("❌ Invalid option! Please try again.");
                }
                
                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
        }
    }
    
    private void displayMainMenu() {
        System.out.println("\n📞 CONTACT MANAGEMENT SYSTEM");
        System.out.println("=" + "=".repeat(30));
        System.out.println("1. ➕ Add New Contact");
        System.out.println("2. 🔍 Search Contacts");
        System.out.println("3. 📋 Display All Contacts");
        System.out.println("4. 📂 Display by Category");
        System.out.println("5. ✏️  Update Contact");
        System.out.println("6. 🗑️  Delete Contact");
        System.out.println("0. 🚪 Exit");
        System.out.println("=" + "=".repeat(30));
    }
    
    private int getMenuChoice() {
        System.out.print("👉 Enter your choice (0-6): ");
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Please enter a valid number (0-6): ");
            scanner.next(); // consume invalid input
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return choice;
    }
    
    private void addNewContact() {
        System.out.println("\n➕ ADD NEW CONTACT");
        System.out.println("-".repeat(20));
        
        System.out.print("First Name: ");
        String firstName = getValidatedInput("First name cannot be empty!");
        
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.print("Phone Number: ");
        String phoneNumber = getValidatedInput("Phone number cannot be empty!");
        
        System.out.print("Email (optional): ");
        String email = scanner.nextLine().trim();
        
        System.out.print("Category (Work/Personal/Family/Friends): ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) {
            category = "Personal"; // default category
        }
        
        boolean success = manager.addContact(firstName, lastName, phoneNumber, email, category);
        if (success) {
            System.out.println("🎉 Contact added successfully!");
        }
    }
    
    private void searchContacts() {
        System.out.println("\n🔍 SEARCH CONTACTS");
        System.out.println("-".repeat(20));
        System.out.print("Enter search term (name, phone, or email): ");
        String searchTerm = scanner.nextLine().trim();
        
        if (searchTerm.isEmpty()) {
            System.out.println("❌ Please enter a search term!");
            return;
        }
        
        List<Contact> results = manager.searchContacts(searchTerm);
        
        if (results.isEmpty()) {
            System.out.println("📭 No contacts found matching: " + searchTerm);
        } else {
            System.out.println("\n🎯 Search Results (" + results.size() + " found):");
            System.out.println("=" + "=".repeat(50));
            for (Contact contact : results) {
                System.out.println("📞 " + contact);
            }
            System.out.println("=" + "=".repeat(50));
        }
    }
    
    private void displayAllContacts() {
        System.out.println("\n📋 ALL CONTACTS");
        System.out.println("-".repeat(20));
        manager.displayAllContacts();
    }
    
    private void displayByCategory() {
        System.out.println("\n📂 CONTACTS BY CATEGORY");
        System.out.println("-".repeat(25));
        manager.displayContactsByCategory();
    }
    
    private void updateContact() {
        System.out.println("\n✏️ UPDATE CONTACT");
        System.out.println("-".repeat(20));
        
        // First, let user search for the contact
        System.out.print("Enter contact name or phone to find: ");
        String searchTerm = scanner.nextLine().trim();
        
        List<Contact> results = manager.searchContacts(searchTerm);
        if (results.isEmpty()) {
            System.out.println("📭 No contacts found!");
            return;
        }
        
        // Display found contacts
        System.out.println("\nFound contacts:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getFullName() + " - " + results.get(i).getPhoneNumber());
        }
        
        System.out.print("Select contact number to update (1-" + results.size() + "): ");
        int choice = getValidChoice(1, results.size());
        
        Contact selectedContact = results.get(choice - 1);
        System.out.println("Updating: " + selectedContact.getFullName());
        
        // Get new details (allow empty to keep current)
        System.out.print("New First Name (current: " + selectedContact.getFirstName() + "): ");
        String firstName = scanner.nextLine().trim();
        if (firstName.isEmpty()) firstName = selectedContact.getFirstName();
        
        System.out.print("New Last Name (current: " + selectedContact.getLastName() + "): ");
        String lastName = scanner.nextLine().trim();
        if (lastName.isEmpty()) lastName = selectedContact.getLastName();
        
        System.out.print("New Phone (current: " + selectedContact.getPhoneNumber() + "): ");
        String phone = scanner.nextLine().trim();
        if (phone.isEmpty()) phone = selectedContact.getPhoneNumber();
        
        System.out.print("New Email (current: " + selectedContact.getEmail() + "): ");
        String email = scanner.nextLine().trim();
        if (email.isEmpty()) email = selectedContact.getEmail();
        
        System.out.print("New Category (current: " + selectedContact.getCategory() + "): ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) category = selectedContact.getCategory();
        
        manager.updateContact(selectedContact.getContactId(), firstName, lastName, phone, email, category);
    }
    
    private void deleteContact() {
        System.out.println("\n🗑️ DELETE CONTACT");
        System.out.println("-".repeat(20));
        
        System.out.print("Enter contact name or phone to find: ");
        String searchTerm = scanner.nextLine().trim();
        
        List<Contact> results = manager.searchContacts(searchTerm);
        if (results.isEmpty()) {
            System.out.println("📭 No contacts found!");
            return;
        }
        
        // Display found contacts
        System.out.println("\nFound contacts:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getFullName() + " - " + results.get(i).getPhoneNumber());
        }
        
        System.out.print("Select contact number to delete (1-" + results.size() + "): ");
        int choice = getValidChoice(1, results.size());
        
        Contact selectedContact = results.get(choice - 1);
        
        System.out.print("⚠️ Are you sure you want to delete '" + selectedContact.getFullName() + "'? (y/N): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        
        if (confirmation.equals("y") || confirmation.equals("yes")) {
            manager.deleteContact(selectedContact.getContactId());
        } else {
            System.out.println("❌ Deletion cancelled.");
        }
    }
    
    private String getValidatedInput(String errorMessage) {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("❌ " + errorMessage);
                System.out.print("Please try again: ");
            }
        } while (input.isEmpty());
        return input;
    }
    
    private int getValidChoice(int min, int max) {
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Please enter a valid number (" + min + "-" + max + "): ");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice < min || choice > max) {
            System.out.print("❌ Please enter a number between " + min + " and " + max + ": ");
            return getValidChoice(min, max);
        }
        return choice;
    }
}
