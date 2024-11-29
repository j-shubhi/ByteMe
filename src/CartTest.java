import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CartTest {
    private Cart cart;
    private MenuItem burger;
    private MenuItem pizza;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        burger = new MenuItem("Burger", 50.0, "Snacks", true);
        pizza = new MenuItem("Pizza", 800.0, "Meals", true);
    }

    @Test
    public void testAddItemToCart() {
        cart.addItem(burger, 2);

        assertEquals(100.0, cart.calculateTotal(), 0.001,
                "Total price should reflect the added items");

        assertEquals(2, cart.getCartItems().get(burger),
                "Cart should have correct quantity of added item");
    }

    @Test
    public void testModifyItemQuantity() {
        cart.addItem(burger, 1);

        cart.modifyQuantity(burger, 3);

        assertEquals(150.0, cart.calculateTotal(), 0.001,
                "Total price should update with new quantity");

        assertEquals(3, cart.getCartItems().get(burger),
                "Cart should have updated quantity of item");
    }

    @Test
    public void testPreventNegativeQuantity() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cart.addItem(burger, 2);

        cart.modifyQuantity(burger, -1);

        assertFalse(cart.getCartItems().containsKey(burger),
                "Item should be removed when quantity is set to negative");

        assertEquals(0.0, cart.calculateTotal(), 0.001,
                "Total should be zero after removing all items");
    }

    @Test
    public void testOrderUnavailableItem() {
        MenuItem unavailableItem = new MenuItem("Dosa", 70.0, "Lunch", false);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Customer customer = new Customer("testUser");
        customer.addToCart(unavailableItem, 1);

        assertTrue(customer.getCart().getCartItems().isEmpty(),
                "Cart should remain empty for unavailable items");

        assertTrue(outContent.toString().contains("is not available"),
                "Should display message that item is not available");
    }
}