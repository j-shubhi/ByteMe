import java.util.HashMap;
import java.util.Map;

class Cart {
    private Map<MenuItem, Integer> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        cartItems.put(item, cartItems.getOrDefault(item, 0) + quantity);
        System.out.println(item.getName() + " added to cart.");
    }

    public void removeItem(MenuItem item) {
        cartItems.remove(item);
        System.out.println(item.getName() + " removed from cart.");
    }

    public void modifyQuantity(MenuItem item, int quantity) {
        if (cartItems.containsKey(item)) {
            if (quantity <= 0) {
                removeItem(item);
            }
            else {
                cartItems.put(item, quantity);
                System.out.println("Updated quantity of " + item.getName() + " to " + quantity);
            }
        }
        else {
            System.out.println("Item not in cart.");
        }
    }

    public double calculateTotal() {
        return cartItems.entrySet().stream().mapToDouble(e -> e.getKey().getPrice() * e.getValue()).sum();
    }

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }

    @Override
    public String toString() {
        return cartItems.toString();
    }
}