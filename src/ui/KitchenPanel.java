package ui;

import model.OrderItem;
import model.RestaurantTable;
import service.RestaurantManager;

import javax.swing.*;
import java.awt.*;

public class KitchenPanel extends JPanel {

    private MainFrame mainFrame;
    private RestaurantManager manager;
    private JPanel cardsPanel;

    public KitchenPanel(MainFrame mainFrame, RestaurantManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        setLayout(new BorderLayout());
        setBackground(new Color(28, 28, 30));

        JLabel title = new JLabel("🍳 MUTFAK EKRANI");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JButton back = new JButton("Geri");
        back.addActionListener(e -> mainFrame.showTables());

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(new Color(28, 28, 30));
        top.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        top.add(title, BorderLayout.WEST);
        top.add(back, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        cardsPanel = new JPanel();
        cardsPanel.setBackground(new Color(28, 28, 30));
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(cardsPanel);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(28, 28, 30));

        add(scroll, BorderLayout.CENTER);
    }

    public void refresh() {

        cardsPanel.removeAll();

        for (RestaurantTable table : manager.getTables()) {

            if (table.getOrder().isEmpty()) {
                continue;
            }

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(45, 45, 48));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(70, 70, 70), 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1000));

            JLabel masa = new JLabel("Masa " + table.getTableNumber());
            masa.setFont(new Font("Arial", Font.BOLD, 26));
            masa.setForeground(Color.WHITE);

            card.add(masa);
            card.add(Box.createVerticalStrut(15));

            boolean hasKitchenItem = false;

            for (OrderItem item : table.getOrder().getItems()) {

                String name = item.getProduct().getName();

                // İçecekleri mutfakta gösterme
                if (name.equalsIgnoreCase("Kola")
                        || name.equalsIgnoreCase("Ayran")
                        || name.equalsIgnoreCase("Su")
                        || name.equalsIgnoreCase("Gazoz")) {
                    continue;
                }

                hasKitchenItem = true;

                JPanel row = new JPanel(new BorderLayout());
                row.setBackground(new Color(45, 45, 48));

                JLabel urun = new JLabel(name + "  x" + item.getQuantity());
                urun.setForeground(Color.WHITE);
                urun.setFont(new Font("Arial", Font.PLAIN, 20));

                JButton ready = new JButton();

                if (table.isProductReady(item.getProduct().getId())) {

                    ready.setText("Hazır");
                    ready.setBackground(new Color(46, 204, 113));
                    ready.setEnabled(false);

                } else {

                    ready.setText("Hazır Yap");
                    ready.setBackground(new Color(243, 156, 18));

                    ready.addActionListener(e -> {

                        int secim = JOptionPane.showConfirmDialog(
                                this,
                                "\"" + name + "\" hazırlandı mı?",
                                "Siparişi Onayla",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE
                        );

                        if (secim == JOptionPane.YES_OPTION) {

                            table.setProductReady(item.getProduct().getId());

                            refresh();
                        }

                    });

                }

                row.add(urun, BorderLayout.WEST);
                row.add(ready, BorderLayout.EAST);

                card.add(row);
                card.add(Box.createVerticalStrut(12));
            }

            // Kartı sadece mutfak ürünü varsa ekle
            if (hasKitchenItem) {
                cardsPanel.add(card);
                cardsPanel.add(Box.createVerticalStrut(20));
            }
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }
}