package ui;

import model.OrderItem;
import model.Product;
import model.RestaurantTable;
import service.RestaurantManager;

import javax.swing.*;
import java.awt.*;

public class OrderPanel extends JPanel {
    private MainFrame mainFrame;
    private RestaurantManager manager;
    private RestaurantTable currentTable;

    private DefaultListModel<String> orderListModel;
    private JList<String> orderList;
    private JLabel tableLabel;
    private JLabel totalLabel;
    private JPanel menuPanel;

    public OrderPanel(MainFrame mainFrame, RestaurantManager manager) {
        this.mainFrame = mainFrame;
        this.manager = manager;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        tableLabel = new JLabel();
        tableLabel.setFont(new Font("Arial", Font.BOLD, 22));
        add(tableLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createTitledBorder("MENÜ"));

        JPanel menuContainer = new JPanel(new BorderLayout());
        menuContainer.add(menuPanel, BorderLayout.NORTH);
        centerPanel.add(menuContainer);

        JPanel orderContainer = new JPanel(new BorderLayout());
        orderContainer.setBorder(BorderFactory.createTitledBorder("SİPARİŞ"));

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);

        orderContainer.add(new JScrollPane(orderList), BorderLayout.CENTER);

        totalLabel = new JLabel("TOPLAM: 0 TL");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderContainer.add(totalLabel, BorderLayout.SOUTH);

        centerPanel.add(orderContainer);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton backButton = new JButton("Geri");
        JButton removeButton = new JButton("Ürün Çıkar");
        JButton closeButton = new JButton("Hesabı Kapat");

        backButton.addActionListener(e -> mainFrame.showTables());
        removeButton.addActionListener(e -> removeSelectedItem());
        closeButton.addActionListener(e -> closeOrder());

        bottomPanel.add(backButton);
        bottomPanel.add(removeButton);
        bottomPanel.add(closeButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setTable(RestaurantTable table) {
        currentTable = table;
        tableLabel.setText("MASA " + table.getTableNumber());

        createMenuButtons();
        refreshOrder();
    }

    private void createMenuButtons() {
        menuPanel.removeAll();

        for (Product product : manager.getProducts()) {
            JButton button = new JButton(
                    product.getName() + " - " + formatPrice(product.getPrice()) + " TL [+]"
            );

            button.setAlignmentX(Component.LEFT_ALIGNMENT);

            button.addActionListener(e -> {
                currentTable.getOrder().addItem(product);
                currentTable.updateStatus();
                refreshOrder();
            });

            menuPanel.add(button);
            menuPanel.add(Box.createVerticalStrut(5));
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }

    private void refreshOrder() {
        orderListModel.clear();

        for (OrderItem item : currentTable.getOrder().getItems()) {
            orderListModel.addElement(
                    item.getProduct().getName()
                            + " x" + item.getQuantity()
                            + "  " + formatPrice(item.getSubtotal()) + " TL"
            );
        }

        totalLabel.setText(
                "TOPLAM: "
                        + formatPrice(currentTable.getOrder().calculateTotal())
                        + " TL"
        );
    }

    private void removeSelectedItem() {
        int selectedIndex = orderList.getSelectedIndex();

        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lütfen çıkarılacak ürünü seçin."
            );
            return;
        }

        currentTable.getOrder().removeItem(selectedIndex);
        currentTable.updateStatus();
        refreshOrder();
    }

    private void closeOrder() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Hesabı kapatmak istediğinize emin misiniz?",
                "Hesabı Kapat",
                JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            manager.closeOrder(currentTable);
            mainFrame.showTables();
        }
    }

    private String formatPrice(double price) {
        if (price == (long) price) {
            return String.valueOf((long) price);
        }

        return String.format("%.2f", price);
    }
}
