import java.util.*;

class Customer extends User {

    private Cart cart;
    private List<Order> orderHistory;
    private boolean isVIP;

    public Customer(String username) {
        super(username);
        cart = new Cart();
        orderHistory = new ArrayList<>();
        this.isVIP = false;
    }

    public void setVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    @Override
    public void displayMenu(List<MenuItem> menu) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Canteen Menu");
            System.out.println("2. Track Delivery");
            System.out.println("3. Cart Operations");
            System.out.println("4. Reviews");
            System.out.println("5. Exit");

            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    viewCanteenMenu(menu);
                    break;
                case 2:
                    trackDelivery(orderHistory);
                    break;
                case 3:
                    handleCart(orderHistory);
                    break;
                case 4:
                    manageReviews(menu);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private void viewCanteenMenu(List<MenuItem> menu) {
        System.out.println("Canteen Menu:");
        for (MenuItem item : menu) {
            if (item.isAvailable()) {
                System.out.println(item);
            }
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Search Menu");
            System.out.println("2. Filter by Category");
            System.out.println("3. Sort by Price");
            System.out.println("4. Back");

            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    searchMenu(menu);
                    break;
                case 2:
                    filterByCategory(menu);
                    break;
                case 3:
                    sortByPrice(menu);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public void searchMenu(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();

        System.out.println("Search Results:");
        for (MenuItem item : menu) {
            if (item.getName().toLowerCase().contains(keyword)) {
                System.out.println(item);
            }
        }
    }
    public void filterByCategory(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter category to filter (e.g., Snacks, Meals): ");
        String category = scanner.nextLine();

        System.out.println("Filtered Results:");
        for (MenuItem item : menu) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                System.out.println(item);
            }
        }
    }

    public void sortByPrice(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Sort by price (1 for Ascending, 2 for Descending): ");
        int sortChoice = getValidInteger(scanner);

        if (sortChoice == 1) {
            menu.sort(Comparator.comparingDouble(MenuItem::getPrice));
        }
        else if (sortChoice == 2) {
            menu.sort(Comparator.comparingDouble(MenuItem::getPrice).reversed());
        }
        else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Sorted Menu:");
        for (MenuItem item : menu) {
            if (item.isAvailable()) {
                System.out.println(item);
            }
        }
    }
    public void trackDelivery(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        boolean exitTrackingLoop = false;

        while (!exitTrackingLoop) {
            System.out.println("\nOrder Tracking Options:");
            System.out.println("1. Check Order Status");
            System.out.println("2. Cancel Order");
            System.out.println("3. View Order History");
            System.out.println("4. Exit");

            int choice = getValidInteger(scanner);

            switch (choice) {

                case 1:
                    checkOrderStatus(orders);
                    break;

                case 2:
                    cancelOrder(orders);
                    break;

                case 3:
                    viewOrderHistory(orders);
                    break;

                case 4:
                    exitTrackingLoop = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    private void checkOrderStatus(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your order ID: ");
        int orderId = getValidInteger(scanner);

        for (Order order : orders) {
            if (order.getOrderId() == orderId && order.getStatus().equalsIgnoreCase("Received")) {
                System.out.println(order.toString());
                return;
            }
        }
        System.out.println("Order not found or already processed.");
    }
    private void cancelOrder(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your order ID to cancel: ");
        int orderId = getValidInteger(scanner);

        for (Order order : orders) {
            if (order.getOrderId() == orderId && order.getStatus().equalsIgnoreCase("Received")) {
                order.setStatus("Canceled");
                System.out.println("Order ID " + orderId + " has been canceled.");
                return;
            }
        }
        System.out.println("Order not found or cannot be canceled.");
    }

    public void viewOrderHistory(List<Order> orders) {
        System.out.println("Order History for " + username + ":");
        for (Order order : orders) {
            System.out.println(order.toString());
        }
    }
    public void handleCart(List<Order> orders) {
        Scanner scanner = new Scanner(System.in);
        boolean exitCartLoop = false;

        while (!exitCartLoop) {
            System.out.println("\nCart Options:");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item Quantity");
            System.out.println("3. Remove Item");
            System.out.println("4. View Cart");
            System.out.println("5. Checkout");
            System.out.println("6. Exit Cart");

            int choice = getValidInteger(scanner);

            switch (choice) {

                case 1:
                    System.out.print("Enter the name of the item to add: ");

                    String itemNameToAdd = scanner.nextLine();
                    boolean flag = true;
                    for (MenuItem item : Main.menu) {
                        if (item.getName().equalsIgnoreCase(itemNameToAdd)) {
// Assuming a default quantity of 1
                            addToCart(item, 1);
                            flag = false;
                            System.out.print("Item added");
                            break;
                        }
                    }
                    if (flag) {
                        System.out.println("Invalid");
                    }

                    break;

                case 2:
                    System.out.print("Enter the name of the item to update: ");
                    String itemNameToUpdate = scanner.nextLine();
                    for (MenuItem item : Main.menu) {
                        if (item.getName().equalsIgnoreCase(itemNameToUpdate)) {
                            System.out.print("Enter new quantity: ");
                            int newQuantity = scanner.nextInt();
                            cart.modifyQuantity(item, newQuantity);
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter the name of the item to remove: ");
                    String itemNameToRemove = scanner.nextLine();
                    for (MenuItem item : Main.menu) {
                        if (item.getName().equalsIgnoreCase(itemNameToRemove)) {
                            cart.removeItem(item);
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Current Cart: " + cart.toString());
                    System.out.println("Total Amount: $" + cart.calculateTotal());
                    break;

                case 5:
                    checkout(orders);
                    break;

                case 6:
                    exitCartLoop = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public void addToCart(MenuItem item, int quantity) {
        if (item.isAvailable()) {
            cart.addItem(item, quantity);
        }
        else {
            System.out.println(item.getName() + " is not available.");
        }
    }

    public void checkout(List<Order> orders) {
        double total = cart.calculateTotal();

        if (total == 0) {
            System.out.println("Cart is empty!");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter any special requests: ");
        String specialRequest = scanner.nextLine().trim();

        List<MenuItem> itemsOrdered = new ArrayList<>();
        for (Map.Entry<MenuItem, Integer> entry : cart.getCartItems().entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                itemsOrdered.add(item);
            }
        }
        Order order = new Order(itemsOrdered, specialRequest);
        Main.orders.add(order);         // Add to main orders list
        orderHistory.add(order);        // Add to customer's history
        System.out.println("Order placed with ID: " + order.getOrderId());
        cart = new Cart();
    }

    public void manageReviews(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nManage Reviews:");
            System.out.println("1. Leave a Review");
            System.out.println("2. View Reviews");
            System.out.println("3. Exit");

            int choice = getValidInteger(scanner);

            switch (choice) {
                case 1:
                    leaveReview(menu);
                    break;
                case 2:
                    viewReviews(menu);
                    break;
                case 3:
                    return;
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

    private void leaveReview(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the item you want to review: ");
        String itemName = scanner.nextLine();

        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                System.out.print("Enter your review for " + item.getName() + ": ");
                String reviewText = scanner.nextLine();
                item.addReview(reviewText);
                System.out.println("Review added!");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private void viewReviews(List<MenuItem> menu) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the item you want to view reviews for: ");
        String itemName = scanner.nextLine();

        for (MenuItem item : menu) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                List<String> reviews = item.getReviews();
                if (reviews.isEmpty()) {
                    System.out.println("No reviews for " + item.getName());
                } else {
                    System.out.println("Reviews for " + item.getName() + ":");
                    for (String review : reviews) {
                        System.out.println("- " + review);
                    }
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }

}