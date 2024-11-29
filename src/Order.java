import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int orderCounter = 0;
    private int orderId;
    private List<MenuItem> items;
    private String status;
    private String specialRequest;
    private double extraCharge;

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
        StringBuilder itemNames = new StringBuilder();
        for (MenuItem item : items) {
            if (itemNames.length() > 0) {
                itemNames.append(", ");
            }
            itemNames.append(item.getName());
        }

        String extraChargeInfo = extraCharge > 0 ? " (VIP Extra Charge: Rs." + String.format("%.2f", extraCharge) + ")" : "";

        return "Order ID: " + orderId +
                ", Items: " + itemNames.toString() +
                ", Status: " + status +
                ", Special Request: " + (specialRequest.isEmpty() ? "None" : specialRequest) +
                extraChargeInfo;
    }
    public void setExtraCharge(double extraCharge) {
        this.extraCharge = extraCharge;
    }

    public double getExtraCharge() {
        return this.extraCharge;
    }
}