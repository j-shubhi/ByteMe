import java.util.*;

class Admin extends User {
    private List<Order> orders;
    public Admin(String username) {

        super(username);
        orders = Main.orders;
    }
    @Override
    public void displayMenu(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Menu Items");
            System.out.println("2. Process Orders");
            System.out.println("3. Order Management");
            System.out.println("4. Report Generation");
            System.out.println("5. Exit");

            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    manageMenu(menu);
                    break;
                case 2:
                    processOrders(Main.orders);
                    break;
                case 3:
                    orderManagement();
                    break;
                case 4:
                    generateReport();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public void manageMenu(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nManage Menu Items:");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Remove Item");
            System.out.println("4. Back");
            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    addMenuItem(menu);
                    break;
                case 2:
                    updateMenuItem(menu);
                    break;
                case 3:
                    removeMenuItem(menu);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private void addMenuItem(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = getValidDouble(scanner);
        System.out.print("Enter item category: ");
        String category = scanner.nextLine();

        menu.add(new MenuItem(name, price, category, true));
        System.out.println("Menu item added successfully.");
    }

    private void updateMenuItem(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name to update: ");
        String nameToUpdate = scanner.nextLine();

        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(nameToUpdate)) {
                System.out.print("Enter new price: ");
                double newPrice = getValidDouble(scanner);
                item.setAvailable(true);
                System.out.println("Updated item: " + item.getName());
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private void removeMenuItem(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name to remove: ");
        String nameToRemove = scanner.nextLine();

        menu.removeIf(item -> item.getName().equalsIgnoreCase(nameToRemove));
        System.out.println("Removed item: " + nameToRemove);
    }

    public void processOrders(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus().equalsIgnoreCase("Received")) {
                pendingOrders.add(order);
            }
        }

        if (pendingOrders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        System.out.println("\nPending Orders:");
        for (Order order : pendingOrders) {
            System.out.println(order.toString());
        }

        System.out.print("Enter Order ID to process: ");
        int orderId = getValidInteger(scanner);

        for (Order order : pendingOrders) {
            if (order.getOrderId() == orderId) {
                System.out.println("Current Status: " + order.getStatus());
                System.out.println("1. Mark as Preparing");
                System.out.println("2. Mark as Completed");
                System.out.println("3. Deny Order");
                System.out.println("4. Back");
                int choice = getValidInteger(scanner);

                switch (choice) {
                    case 1:
                        order.setStatus("Preparing");
                        System.out.println("Order marked as Preparing.");
                        break;
                    case 2:
                        order.setStatus("Completed");
                        System.out.println("Order marked as Completed.");
                        break;
                    case 3:
                        order.setStatus("Denied");
                        System.out.println("Order denied.");
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid");
                }
                return;
            }
        }

        System.out.println("Order ID not found.");
    }

    private void orderManagement() {
        Scanner scanner = new Scanner(System.in);

        if (Main.orders.isEmpty()) {
            System.out.println("No orders to manage.");
            return;
        }

        System.out.println("\nPending Orders:");
        for (Order order : Main.orders) {
            if (order.getStatus().equalsIgnoreCase("Received")) {
                System.out.println(order);
            }
        }

        System.out.print("Enter Order ID to update or refund: ");
        int orderId = getValidInteger(scanner);

        for (Order order : Main.orders) {
            if (order.getOrderId() == orderId) {
                System.out.println("Current Status: " + order.getStatus());
                System.out.println("1. Update Order Status");
                System.out.println("2. Process Refund");
                System.out.println("3. Handle Special Requests");
                System.out.println("4. Back");
                int choice = getValidInteger(scanner);

                switch (choice) {
                    case 1:
                        updateOrderStatus(order);
                        break;
                    case 2:
                        processRefund(order);
                        break;
                    case 3:
                        handleSpecialRequests(order);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
                return;
            }
        }
        System.out.println("Order ID not found.");
    }
    private void updateOrderStatus(Order order) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update Status Options:");
        System.out.println("1. Mark as Preparing");
        System.out.println("2. Mark as Completed");
        System.out.println("3. Deny Order");
        System.out.println("4. Back");

        int choice = getValidInteger(scanner);

        switch (choice) {
            case 1:
                order.setStatus("Preparing");
                break;
            case 2:
                order.setStatus("Completed");
                break;
            case 3:
                order.setStatus("Denied");
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Order status updated to: " + order.getStatus());
    }

    private void processRefund(Order order) {
        if (!order.getStatus().equalsIgnoreCase("Denied")) {
            order.setStatus("Refunded");
            System.out.println("Processed refund for Order ID: " + order.getOrderId());
        }
        else {
            System.out.println("Cannot refund a denied order.");
        }
    }

    private void handleSpecialRequests(Order order) {
        Scanner scanner = new Scanner(System.in);

        if (order.getSpecialRequest() != null && !order.getSpecialRequest().isEmpty()) {
            System.out.println("Current Special Request: " + order.getSpecialRequest());
            System.out.print("Enter new special request: ");
            String newRequest = scanner.nextLine();
            order.setSpecialRequest(newRequest);
            System.out.println("Updated Special Request: " + newRequest);
        }
        else {
            System.out.print("Enter special request: ");
            String specialRequest = scanner.nextLine();
            order.setSpecialRequest(specialRequest);
            System.out.println("Added Special Request: " + specialRequest);
        }
    }

    private void generateReport() {

        double totalSales = 0;
        Map<String, Integer> itemPopularity = new HashMap<>();

        for (Order order : orders) {
            totalSales += order.getItems().stream().mapToDouble(MenuItem::getPrice).sum();

            for (MenuItem item : order.getItems()) {
                itemPopularity.put(item.getName(), itemPopularity.getOrDefault(item.getName(), 0) + 1);
            }
        }

        int totalOrders = orders.size();

        System.out.println("\n--- Daily Sales Report ---");
        System.out.println("Total Sales: Rs." + totalSales);
        System.out.println("Total Orders Processed: " + totalOrders);

        System.out.println("\nMost Popular Items:");
        itemPopularity.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by popularity
                .limit(5) // Top 5 items
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " orders"));
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

    private static double getValidDouble(Scanner scanner) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
}