package model;

//product sınıfı yazılır ve içinde olacaklar yazılır
public class Product {
    private int id;
    private String name;
    private double price;

    //product sınıfındaki bilgiler tanımlanır.
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // ürüne özel olan id yi alan ve geri döndüren metod.
    public int getId() {
        return id;
    }

    // ürün adını alan ve geri döndüren metod
    public String getName() {
        return name;
    }

    // ürün fiyatını küsüratlı olarak alan ve geri döndüren metod
    public double getPrice() {
        return price;
    }
}
