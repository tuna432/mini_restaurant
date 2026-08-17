package ui;

import model.RestaurantTable;
import service.RestaurantManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private TablePanel tablePanel;
    private OrderPanel orderPanel;
    private KitchenPanel kitchenPanel;

    public MainFrame(RestaurantManager manager){

        setTitle("Mini Restaurant Manager");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);

        tablePanel=new TablePanel(this,manager);
        orderPanel=new OrderPanel(this,manager);
        kitchenPanel=new KitchenPanel(this,manager);

        mainPanel.add(tablePanel,"masalar");
        mainPanel.add(orderPanel,"order");
        mainPanel.add(kitchenPanel,"kitchen");

        add(mainPanel);

        showTables();

    }

    public void showTables(){

        tablePanel.refresh();
        cardLayout.show(mainPanel,"masalar");

    }

    public void showOrder(RestaurantTable table){

        orderPanel.setTable(table);
        cardLayout.show(mainPanel,"order");

    }

    public void showKitchen(){

        kitchenPanel.refresh();
        cardLayout.show(mainPanel,"kitchen");

    }

}