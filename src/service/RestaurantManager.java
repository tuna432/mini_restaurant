//MASA EKLEME 40 İLE 50. SATIR

package service;

import model.*;

import java.util.ArrayList;

public class RestaurantManager {

    private ArrayList<Product> products;
    private ArrayList<RestaurantTable> tables;

    public RestaurantManager() {

        products = new ArrayList<>();
        tables = new ArrayList<>();

        createProducts();
        createTables();
    }

    //son id 16, 17 kulanılacak yeni id eklenirse eger idi önceki ürün ile aynı olursa önce ürünü döndürür butonda sadece adı degişir.
    private void createProducts() {

        products.add(new Product(1,"Hamburger",180));
        products.add(new Product(2,"Pizza",220));
        products.add(new Product(3,"Makarna",160));
        products.add(new Product(4,"Cheesecake",120));
        products.add(new Product(5,"kahvaltılık",500));
        products.add(new Product(12,"omlet",200));
        products.add(new Product(15, "kebap" , 250));

        products.add(new Product(6,"Kola",50));
        products.add(new Product(7,"Ayran",35));
        products.add(new Product(8,"Su",20));
        products.add(new Product(9,"Gazoz",20));
        products.add(new Product(16 ,"meyve suyu", 50));

        products.add(new Product(10,"Baklava",250));
        products.add(new Product(11,"Sütlaç",200));
        products.add(new Product(13,"pasta",200));
        products.add(new Product(14, "ekler pasta", 120));

    }

    //bu methodan masa sayısını azaltıp artılılılabilir.
    private void createTables() {

        for(int i=1;i<=12;i++){

            tables.add(new RestaurantTable(i));

        }

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<RestaurantTable> getTables() {
        return tables;
    }

    //hesapı kapat tuşuna basıldıgında ürünleri temizler ve masayı boşa çıkartır.
    public void closeOrder(RestaurantTable table){

        table.closeOrder();

    }

    // MUTFAK SİPARİŞLERİ
    public ArrayList<KitchenOrder> getKitchenOrders(){

        ArrayList<KitchenOrder> kitchen=new ArrayList<>();

        for(RestaurantTable table:tables){

            for(OrderItem item:table.getOrder().getItems()){

                String name=item.getProduct().getName();

                //eger aşagda adı yazılı olanlar eklenirse içecek oldukları için aşcının ekranına düşmez.
                if(name.equalsIgnoreCase("Kola")
                        ||name.equalsIgnoreCase("Ayran")
                        ||name.equalsIgnoreCase("Su")
                        ||name.equalsIgnoreCase("meyve suyu")
                        ||name.equalsIgnoreCase("Gazoz")){

                    continue;
                }

                kitchen.add(new KitchenOrder(table,item));
            }

        }

        return kitchen;

    }
}