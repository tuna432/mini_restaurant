//model baglantı cümlesi.
package model;
import java.util.HashSet;

//RestaurantTableın classıdır
public class RestaurantTable {
    private HashSet<Integer> readyProducts;
    private int tableNumber;
    private TableStatus status;
    private Order order;

    //RestaurantTable classındır.
    public RestaurantTable(int tableNumber) {
        this.tableNumber = tableNumber;
        this.status = TableStatus.EMPTY;
        this.order = new Order();
        readyProducts = new HashSet<>();
    }

    //masa numarasını alır
    public int getTableNumber() {
        return tableNumber;
    }

    //masa boş mu dolu mu kontrol eder
    public TableStatus getStatus() {
        return status;
    }

    public boolean isProductReady(int productId) {
        return readyProducts.contains(productId);
    }

    public void setProductReady(int productId) {
        readyProducts.add(productId);
    }

    public void clearReadyProducts() {
        readyProducts.clear();
    }

    //siparişi alır ve geri deger döndürür
    public Order getOrder() {
        return order;
    }

    //masa durumunu kontrol edere eger sipariş boş ise msayı boş olarak atar eger başka bir dulum varsa masa dolu gösterilir.
    public void updateStatus() {
        if (order.isEmpty()) {
            status = TableStatus.EMPTY;
        } else {
            status = TableStatus.OCCUPIED;
        }
    }
//
    //siparişi kapatma metodu eklenir bu metod frontendeki koda bulunan butona baglanır tıklanıldıgında çalışır
    public void closeOrder() {
        order.clear();
        status = TableStatus.EMPTY;
    }
}
