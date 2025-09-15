public class Main {
    @SuppressWarnings("unlikely-arg-type")
    public static void main(String[] args) {
        // Check if user wants interactive mode or demo mode
        if (args.length > 0 && args.equals("demo")) {
            // Demo mode - your current testing code
            runDemoMode();
        } else {
            // Interactive mode - full CLI application
            runInteractiveMode();
        }
    }
    
    private static void runDemoMode() {
        System.out.println("🚀 Contact Management System - Demo Mode\n");
        
        ContactManager manager = new ContactManager();
        
        // Add test contacts
        manager.addContact("John", "Doe", "1234567890", "john@email.com", "Work");
        manager.addContact("Jane", "Smith", "0987654321", "jane@email.com", "Friends");
        manager.addContact("Bob", "Johnson", "5555555555", "bob@email.com", "Family");
        
        manager.displayAllContacts();
        
        System.out.println("\n🔍 Search Results for 'John':");
        var searchResults = manager.searchContacts("John");
        for (Contact contact : searchResults) {
            System.out.println("  " + contact);
        }
        
        manager.displayContactsByCategory();
        System.out.println("\n✨ Demo completed successfully!");
    }
    
    private static void runInteractiveMode() {
        ContactCLI cli = new ContactCLI();
        cli.start();
    }
}
