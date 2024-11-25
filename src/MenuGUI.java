import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MenuGUI {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private static final String MENU_PANEL = "Menu Panel";
    private static final String ORDERS_PANEL = "Orders Panel";
    private JTable menuTable;
    private JTable ordersTable;
    private Timer refreshTimer;
    private List<MenuItem> menu;
    private List<Order> orders;

    public MenuGUI() {
        loadDataFromFiles();
        initializeFrame();
        initializeComponents();
        setupRefreshTimer();
    }

    private void initializeFrame() {
        frame = new JFrame("Byte Me! Canteen Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        frame.add(cardPanel);
    }

    private void initializeComponents() {
        JPanel menuPanel = createMenuPanel();
        cardPanel.add(menuPanel, MENU_PANEL);
        JPanel ordersPanel = createOrdersPanel();
        cardPanel.add(ordersPanel, ORDERS_PANEL);
        JPanel navigationPanel = createNavigationPanel();
        frame.add(navigationPanel, BorderLayout.NORTH);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] columnNames = {"Name", "Price (Rs.)", "Category", "Available"};
        menuTable = new JTable();
        menuTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {}, columnNames
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        menuTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        menuTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        menuTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(menuTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Canteen Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createOrdersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Order ID", "Items", "Status", "Special Request"};
        ordersTable = new JTable();
        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {}, columnNames
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        ordersTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        ordersTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        ordersTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(ordersTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Pending Orders", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton menuButton = new JButton("View Menu");
        JButton ordersButton = new JButton("View Orders");

        menuButton.addActionListener(e -> cardLayout.show(cardPanel, MENU_PANEL));
        ordersButton.addActionListener(e -> cardLayout.show(cardPanel, ORDERS_PANEL));

        panel.add(menuButton);
        panel.add(ordersButton);

        return panel;
    }

    private void setupRefreshTimer() {
        // Refresh data every 2 seconds
        refreshTimer = new Timer(2000, e -> {
            loadDataFromFiles();
            refreshMenuData();
            refreshOrdersData();
        });
        refreshTimer.start();
    }

    private void loadDataFromFiles() {
        menu = DataManager.loadMenu();
        orders = DataManager.loadOrders();
    }

    public void refreshMenuData() {
        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) menuTable.getModel();
        model.setRowCount(0);

        for (MenuItem item : menu) {
            model.addRow(new Object[]{
                    item.getName(),
                    item.getPrice(),
                    item.getCategory(),
                    item.isAvailable() ? "Yes" : "No"
            });
        }
    }

    public void refreshOrdersData() {
        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) ordersTable.getModel();
        model.setRowCount(0);

        for (Order order : orders) {
            if (order.getStatus().equalsIgnoreCase("Received") ||
                    order.getStatus().equalsIgnoreCase("Preparing")) {
                model.addRow(new Object[]{
                        order.getOrderId(),
                        formatOrderItems(order.getItems()),
                        order.getStatus(),
                        order.getSpecialRequest()
                });
            }
        }
    }
    private String formatOrderItems(List<MenuItem> items) {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : items) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(item.getName());
        }
        return sb.toString();
    }
    public void show() {
        refreshMenuData();
        refreshOrdersData();
        frame.setVisible(true);
    }

    public void dispose() {
        refreshTimer.stop();
        frame.dispose();
    }
}