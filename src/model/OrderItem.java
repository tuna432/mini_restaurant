//model paketinin baglantısı.
package model;

//ürünler ve miktarın tanımlandıgı sınıftır.
public class OrderItem {
    private Product product;
    private int quantity;

    //listeye ürün miktarının elşenmesini saglayan method, yani   "this.quantity = 1   ;" satırına 2 yazarsanız menüden tıklayınca 2 tane ekrar tek tıklayışta sadece ilk tıklama içindir.
    //degiştirmeniz önerilmez.
    public OrderItem(Product product) {
        this.product = product;
        this.quantity = 1   ;
    }
//
    // ürün çagırılır
    public Product getProduct() {
        return product;
    }

    //miktar alınır
    public int getQuantity() {
        return quantity;
    }

    //miktar artırma yapılabilir.
    public void increaseQuantity() {
        quantity++;
    }

    //eger miktar 1 den büyükse miktar azaltma yapılabilir
    public void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
        }
    }

    //miktar ile ürün fiyatı çarpılır ve toplam gösterilir
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
