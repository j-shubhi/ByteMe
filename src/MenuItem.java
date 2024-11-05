import java.util.ArrayList;
import java.util.List;

class MenuItem {
    private String name;
    private double price;
    private String category;
    private boolean available;
    private List<String> reviews;

    public MenuItem(String name, double price, String category, boolean available) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.available = available;
        this.reviews = new ArrayList<>();
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public void addReview(String review) {
        reviews.add(review);
    }

    public List<String> getReviews() {
        return reviews;
    }
    @Override
    public String toString() {
        return name + " - Rs." + price + " [" + category + "]" + (available ? "" : " (Unavailable)");
    }
}