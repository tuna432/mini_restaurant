    //restaurant baglantı cümlesi
package restaurant;

    //kütüphaneler
import service.RestaurantManager;
import ui.MainFrame;
import javax.swing.SwingUtilities;

//
    //kodun başlangıcı.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            //manager tanımlanması
            RestaurantManager manager = new RestaurantManager();

            // main frame tanımlanması
            MainFrame frame = new MainFrame(manager);

            //framei görünür kılma
            frame.setVisible(true);
        });
    }
}
