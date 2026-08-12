//40. ile 50. satır arasında masa kodu var prdan ekleme veya çıkarma 8yapılabilir.

//kulanılan kütüphaneler
package service;

import model.Product;
import model.RestaurantTable;

import java.util.ArrayList;

public class RestaurantManager {

    //2 tane özel liste açılır 1.si sadece productsları listeier 2. sadece tablesları listeler
    private ArrayList<Product> products;
    private ArrayList<RestaurantTable> tables;

     //RestaurantManager metodu listeleri tanımlar ve oluşturur
    public RestaurantManager() {
        products = new ArrayList<>();
        tables = new ArrayList<>();

        createProducts();
        createTables();
    }

    //elle girilen veriler
    private void createProducts() {
        products.add(new Product(1, "Hamburger", 180));
        products.add(new Product(2, "Pizza", 220));
        products.add(new Product(3, "Makarna", 160));
        products.add(new Product(4, "Cheesecake", 120));


        products.add(new Product(6, "Kola", 50));
        products.add(new Product(7, "Ayran", 35));
        products.add(new Product(8, "su", 20));
        products.add(new Product(9, "gazoz", 20));

        products.add(new Product(10, "Baklava", 250));
        products.add(new Product(11, "Sütlaç", 200));
    }

    //elle eklenen masalar yani buradan masa sayısını artırabilirsiniz
    private void createTables() {
        for (int i = 1; i <= 6; i++) {
            tables.add(new RestaurantTable(i));
        }
    }

    //listeler
    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<RestaurantTable> getTables() {
        return tables;
    }

    //masa numarasını arar eger aynı numaralar ise masa bilgilerini yollar
    public RestaurantTable findTable(int tableNumber) {
        for (RestaurantTable table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }

        return null;
    }
//masayı kapatır
    public void closeOrder(RestaurantTable table) {
        table.closeOrder();
    }
}