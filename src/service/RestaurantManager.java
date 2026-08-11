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
        products.add(new Product(4, "Kola", 50));
        products.add(new Product(5, "Ayran", 35));
        products.add(new Product(6, "Cheesecake", 120));
    }

    //elle eklenen masalar
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

    //masa numarasını arar eger eşitse masa bilgilerini yollar
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