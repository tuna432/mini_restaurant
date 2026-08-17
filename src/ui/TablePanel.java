package ui;

import model.RestaurantTable;
import model.TableStatus;
import service.RestaurantManager;

import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {

    private MainFrame mainFrame;
    private RestaurantManager manager;
    private JPanel tablesPanel;

    public TablePanel(MainFrame mainFrame, RestaurantManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // =========================
        // ÜST KISIM
        // =========================

        JLabel title = new JLabel("MINI RESTAURANT MANAGER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton kitchenButton = new JButton("🍳 Mutfak");
        kitchenButton.setFont(new Font("Arial", Font.BOLD, 16));
        kitchenButton.addActionListener(e -> mainFrame.showKitchen());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(title, BorderLayout.CENTER);
        topPanel.add(kitchenButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // =========================
        // MASALAR
        // =========================

        tablesPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        add(tablesPanel, BorderLayout.CENTER);

        refresh();
    }

    public void refresh() {

        tablesPanel.removeAll();

        for (RestaurantTable table : manager.getTables()) {

            String status = table.getStatus() == TableStatus.EMPTY
                    ? "BOŞ"
                    : "DOLU";

            JButton button = new JButton(
                    "Masa " + table.getTableNumber() + " - " + status
            );

            if (table.getStatus() == TableStatus.EMPTY) {
                button.setBackground(new Color(47, 249, 36));
                button.setForeground(Color.WHITE);
            }

            if (table.getStatus() == TableStatus.OCCUPIED) {
                button.setBackground(new Color(150, 0, 0));
                button.setForeground(Color.WHITE);
            }

            button.setFont(new Font("Arial", Font.BOLD, 18));

            button.addActionListener(e -> mainFrame.showOrder(table));

            tablesPanel.add(button);
        }

        tablesPanel.revalidate();
        tablesPanel.repaint();
    }
}