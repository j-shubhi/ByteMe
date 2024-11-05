*Collections*

ArrayList for Menu Items:

The menu is represented as a List<MenuItem>, specifically an ArrayList<MenuItem>. This collection allows dynamic resizing, making it easy to add or remove menu items as needed.

ArrayList for Orders:

The orders list is also an ArrayList<Order>, which stores all the orders placed by customers. This allows for easy iteration over orders and dynamic management of the order list.

HashMap for Cart Items:

The cart uses a Map<MenuItem, Integer> (specifically a HashMap) to keep track of items and their quantities. This allows for efficient retrieval and updating of item quantities in the cart.

List for Reviews:

Each MenuItem has a List<String> to store reviews. This allows multiple reviews to be associated with each menu item dynamically.

*OOPS*

1. Classes and Objects
Classes: The system is structured using multiple classes, each representing a distinct entity in the application:

MenuItem

Order

Cart

User

Admin and Customer

2. Encapsulation

Data Hiding: Attributes of classes are marked as private to protect their integrity.

3. Inheritance

User Class: The User class serves as a base class for both Admin and Customer, allowing shared functionality while enabling specific implementations in derived classes.

Polymorphism: The displayMenu method is defined as an abstract method in the User class and overridden in both derived classes (Admin and Customer).

4. Composition

Using Collections: The system uses various collections to manage data effectively:

ArrayList: Used for storing menu items (List<MenuItem> menu) and orders (List<Order> orders). This allows dynamic resizing as items are added or removed.

HashMap: Used in the Cart class to associate menu items with their quantities (Map<MenuItem, Integer cartItems). This provides efficient access for adding or modifying cart contents.

*Assumptions*
Sort by popularity
Top 5 items
Assuming a default quantity of 1
