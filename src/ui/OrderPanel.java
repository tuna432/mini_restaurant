package ui;

import model.OrderItem;
import model.Product;
import model.RestaurantTable;
import service.RestaurantManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderPanel extends JPanel {

    private MainFrame mainFrame;
    private RestaurantManager manager;
    private RestaurantTable currentTable;

    private JLabel tableLabel;
    private JLabel totalLabel;

    // Mevcut menü
    private JPanel menuPanel;

    // Sipariş JTable
    private JTable orderTable;
    private DefaultTableModel orderTableModel;

    public OrderPanel(MainFrame mainFrame, RestaurantManager manager) {

        this.mainFrame = mainFrame;
        this.manager = manager;

        setLayout(new BorderLayout(10, 10));
        setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        // =========================================================
        // MASA BAŞLIĞI
        // =========================================================

        tableLabel = new JLabel();

        tableLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        add(
                tableLabel,
                BorderLayout.NORTH
        );

        // =========================================================
        // ORTA KISIM
        // =========================================================

        JPanel centerPanel =
                new JPanel(
                        new GridLayout(
                                1, 2, 15, 0
                        )
                );

        // =========================================================
        // SOL TARAF - MENÜ
        // =========================================================

        menuPanel = new JPanel();

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        menuPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "MENÜ"
                )
        );

        JPanel menuContainer =
                new JPanel(
                        new BorderLayout()
                );

        menuContainer.add(
                menuPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                menuContainer
        );

        // =========================================================
        // SAĞ TARAF - SİPARİŞ
        // =========================================================

        JPanel orderContainer =
                new JPanel(
                        new BorderLayout()
                );

        orderContainer.setBorder(
                BorderFactory.createTitledBorder(
                        "SİPARİŞ"
                )
        );

        // ---------------------------------------------------------
        // SİPARİŞ TABLOSU
        // ---------------------------------------------------------

        orderTableModel =
                new DefaultTableModel(
                        new Object[]{
                                "Ürün",
                                "Fiyat",
                                "Miktar"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        orderTable =
                new JTable(
                        orderTableModel
                );

        orderTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        orderTable.setRowHeight(38);

        orderTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                15
                        )
                );

        orderTable.getTableHeader()
                .setReorderingAllowed(false);

        // Sütun genişlikleri
        orderTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(130);

        orderTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(80);

        orderTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(110);

        // ---------------------------------------------------------
        // MİKTAR SÜTUNU
        // ---------------------------------------------------------

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        orderTable.getColumnModel()
                .getColumn(1)
                .setCellRenderer(
                        centerRenderer
                );

        orderTable.getColumnModel()
                .getColumn(2)
                .setCellRenderer(
                        centerRenderer
                );

        // ---------------------------------------------------------
        // EKSİ / ARTI TIKLAMA
        // ---------------------------------------------------------

        orderTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        int row =
                                orderTable.rowAtPoint(
                                        e.getPoint()
                                );

                        int column =
                                orderTable.columnAtPoint(
                                        e.getPoint()
                                );

                        // Miktar sütunu
                        if (row == -1 ||
                                column != 2) {
                            return;
                        }

                        Rectangle cell =
                                orderTable.getCellRect(
                                        row,
                                        column,
                                        false
                                );

                        int relativeX =
                                e.getX() - cell.x;

                        /*
                         * Hücre:
                         *
                         * - x1 +
                         *
                         * Sol taraf = -
                         * Sağ taraf = +
                         */

                        if (relativeX <
                                cell.width / 2) {

                            decreaseOrderItem(row);

                        } else {

                            increaseOrderItem(row);
                        }
                    }
                }
        );

        orderContainer.add(
                new JScrollPane(
                        orderTable
                ),
                BorderLayout.CENTER
        );

        // =========================================================
        // TOPLAM
        // =========================================================

        totalLabel =
                new JLabel(
                        "TOPLAM: 0 TL"
                );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        totalLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        totalLabel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        5,
                        5,
                        5
                )
        );

        orderContainer.add(
                totalLabel,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                orderContainer
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =========================================================
        // ALT BUTONLAR
        // =========================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        JButton backButton =
                new JButton("Geri");

        JButton removeButton =
                new JButton("Ürün Çıkar");

        JButton closeButton =
                new JButton("Hesabı Kapat");

        backButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        removeButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        closeButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        backButton.addActionListener(
                e -> mainFrame.showTables()
        );

        removeButton.addActionListener(
                e -> removeSelectedItem()
        );

        closeButton.addActionListener(
                e -> closeOrder()
        );

        bottomPanel.add(
                backButton
        );

        bottomPanel.add(
                removeButton
        );

        bottomPanel.add(
                closeButton
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );
    }

    // =============================================================
    // MASA SEÇİLDİ
    // =============================================================

    public void setTable(
            RestaurantTable table
    ) {

        currentTable = table;

        tableLabel.setText(
                "MASA " +
                        table.getTableNumber()
        );

        createMenuButtons();

        refreshOrder();
    }

    // =============================================================
    // MENÜ
    // =============================================================

    private void createMenuButtons() {

        menuPanel.removeAll();

        for (Product product :
                manager.getProducts()) {

            /*
             * İçecekler başlamadan önce boşluk
             */
            if (product.getName()
                    .equals("Kola")) {

                menuPanel.add(
                        Box.createVerticalStrut(
                                20
                        )
                );

                JLabel iceceklerLabel =
                        new JLabel(
                                "İçecekler"
                        );

                iceceklerLabel.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                20
                        )
                );

                menuPanel.add(
                        iceceklerLabel
                );

                menuPanel.add(
                        Box.createVerticalStrut(
                                10
                        )
                );
            }

            JButton button =
                    new JButton(
                            product.getName()
                                    + " - "
                                    + formatPrice(
                                    product.getPrice()
                            )
                                    + " TL [+]"
                    );

            button.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            18
                    )
            );

            button.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            button.addActionListener(
                    e -> {

                        currentTable
                                .getOrder()
                                .addItem(
                                        product
                                );

                        currentTable
                                .updateStatus();

                        refreshOrder();
                    }
            );

            menuPanel.add(
                    button
            );

            menuPanel.add(
                    Box.createVerticalStrut(
                            5
                    )
            );
        }

        menuPanel.revalidate();
        menuPanel.repaint();
    }

    // =============================================================
    // SİPARİŞ TABLOSUNU GÜNCELLE
    // =============================================================

    private void refreshOrder() {

        orderTableModel.setRowCount(0);

        for (OrderItem item :
                currentTable
                        .getOrder()
                        .getItems()) {

            Product product =
                    item.getProduct();

            orderTableModel.addRow(
                    new Object[]{
                            product.getName(),

                            formatPrice(
                                    product.getPrice()
                            ) + " TL",

                            "− x"
                                    + item.getQuantity()
                                    + " +"
                    }
            );
        }

        totalLabel.setText(
                "TOPLAM: "
                        + formatPrice(
                        currentTable
                                .getOrder()
                                .calculateTotal()
                )
                        + " TL"
        );
    }

    // =============================================================
    // SİPARİŞTE +
    // =============================================================

    private void increaseOrderItem(
            int row
    ) {

        if (row < 0 ||
                row >= currentTable
                        .getOrder()
                        .getItems()
                        .size()) {

            return;
        }

        OrderItem item =
                currentTable
                        .getOrder()
                        .getItems()
                        .get(row);

        item.increaseQuantity();

        currentTable.updateStatus();

        refreshOrder();
    }

    // =============================================================
    // SİPARİŞTE -
    // =============================================================

    private void decreaseOrderItem(
            int row
    ) {

        if (row < 0 ||
                row >= currentTable
                        .getOrder()
                        .getItems()
                        .size()) {

            return;
        }

        OrderItem item =
                currentTable
                        .getOrder()
                        .getItems()
                        .get(row);

        if (item.getQuantity() > 1) {

            item.decreaseQuantity();

        } else {

            currentTable
                    .getOrder()
                    .getItems()
                    .remove(row);
        }

        currentTable.updateStatus();

        refreshOrder();
    }

    // =============================================================
    // ÜRÜNÜ TAMAMEN ÇIKAR
    // =============================================================

    private void removeSelectedItem() {

        int selectedRow =
                orderTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Lütfen çıkarılacak ürünü seçin."
            );

            return;
        }

        currentTable
                .getOrder()
                .getItems()
                .remove(
                        selectedRow
                );

        currentTable.updateStatus();

        refreshOrder();
    }

    // =============================================================
    // HESABI KAPAT
    // =============================================================

    private void closeOrder() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Hesabı kapatmak istediğinize emin misiniz?",
                        "Hesabı Kapat",
                        JOptionPane.YES_NO_OPTION
                );

        if (result ==
                JOptionPane.YES_OPTION) {

            manager.closeOrder(
                    currentTable
            );

            mainFrame.showTables();
        }
    }

    // =============================================================
    // FİYAT
    // =============================================================

    private String formatPrice(
            double price
    ) {

        if (price == (long) price) {

            return String.valueOf(
                    (long) price
            );
        }

        return String.format(
                "%.2f",
                price
        );
    }
}