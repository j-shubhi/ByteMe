import java.io.*;
import java.util.*;

public class DataManager {
    private static final String MENU_FILE = "menu_data.ser";
    private static final String ORDERS_FILE = "orders_data.ser";

    public static void saveData(List<MenuItem> menu, List<Order> orders) {
        try (ObjectOutputStream menuOut = new ObjectOutputStream(
                new FileOutputStream(MENU_FILE));
             ObjectOutputStream ordersOut = new ObjectOutputStream(
                     new FileOutputStream(ORDERS_FILE))) {

            menuOut.writeObject(new ArrayList<>(menu));
            ordersOut.writeObject(new ArrayList<>(orders));

        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<MenuItem> loadMenu() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(MENU_FILE))) {
            return (List<MenuItem>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading menu: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Order> loadOrders() {
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(ORDERS_FILE))) {
            return (List<Order>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading orders: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}