import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int orderCounter = 0;
    private int orderId;
    private List<MenuItem> items;
    private String status;
    private String specialRequest;

    public Order(List<MenuItem> items, String specialRequest) {
        this.orderId = ++orderCounter;
        this.items = items;
        this.status = "Received";
        this.specialRequest = specialRequest;
    }

    public int getOrderId() { return orderId; }
    public List<MenuItem> getItems() { return items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getSpecialRequest() { return specialRequest; }
    public void setSpecialRequest(String specialRequest) {
        this.specialRequest = specialRequest;
    }
    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Status: " + status + ", Items: " + items + ", Special Request: " + specialRequest;
    }
}