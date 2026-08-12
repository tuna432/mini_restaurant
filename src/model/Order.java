//paket baglantı cümlesi.
package model;

//kütüphaneler
import java.util.ArrayList;

// yeni liste açılır.
public class Order {
    private ArrayList<OrderItem> items;

    // metod tanımlanır
    public Order()
    {
        items = new ArrayList<>();
    }

    // ürün ekleme
    public void addItem(Product product) {
        for (OrderItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity();
                return;
            }
        }

        items.add(new OrderItem(product));
    }

    //ürün silme
    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            OrderItem item = items.get(index);

            if (item.getQuantity() > 1) {
                item.decreaseQuantity();
            } else {
                items.remove(index);
            }
        }
    }

    // toplam hesaplama
    public double calculateTotal() {
        double total = 0;

        for (OrderItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    //ürün çagırma
    public ArrayList<OrderItem> getItems() {
        return items;
    }

    // ürünleri temizleme
    public void clear() {
        items.clear();
    }

    // indeks ile ürünü tamamen silme
    public void removeItemAt(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }

    //boş mu kontrolü
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
