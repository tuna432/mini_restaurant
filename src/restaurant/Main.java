package restaurant;

import service.RestaurantManager;
import ui.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RestaurantManager manager = new RestaurantManager();
            MainFrame frame = new MainFrame(manager);
            frame.setVisible(true);
        });
    }
}
