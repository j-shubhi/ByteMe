import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<MenuItem> menu = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    static {
        menu.add(new MenuItem("Burger", 50, "Snacks", true));
        menu.add(new MenuItem("Pizza", 800, "Meals", true));
        menu.add(new MenuItem("Soda", 20, "Beverages", true));
        menu.add(new MenuItem("Fries", 110, "Snacks", true));
    }

    public static void main(String[] args) {

        Admin admin = new Admin("admin1");
        Customer customer1 = new Customer("student1");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Byte Me!");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            int choice = getValidInteger(scanner);
            switch (choice) {
                case 1:
                    admin.displayMenu(menu);
                    break;

                case 2:
                    boolean exitCustomerLoop = false;
                    while (!exitCustomerLoop) {

                        customer1.displayMenu(menu);
                        exitCustomerLoop=true;
                    }
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid integer: ");
            }
        }
    }
}