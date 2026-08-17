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

    //
    private void createProducts() {

        products.add(new Product(1,"Hamburger",180));
        products.add(new Product(2,"Pizza",220));
        products.add(new Product(3,"Makarna",160));
        products.add(new Product(4,"Cheesecake",120));
        products.add(new Product(4,"kahvaltılık",500));

        products.add(new Product(6,"Kola",50));
        products.add(new Product(7,"Ayran",35));
        products.add(new Product(8,"Su",20));
        products.add(new Product(9,"Gazoz",20));

        products.add(new Product(10,"Baklava",250));
        products.add(new Product(11,"Sütlaç",200));
    }

    private void createTables() {

        for(int i=1;i<=6;i++){

            tables.add(new RestaurantTable(i));

        }

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<RestaurantTable> getTables() {
        return tables;
    }

    public void closeOrder(RestaurantTable table){

        table.closeOrder();

    }

    // MUTFAK SİPARİŞLERİ
    public ArrayList<KitchenOrder> getKitchenOrders(){

        ArrayList<KitchenOrder> kitchen=new ArrayList<>();

        for(RestaurantTable table:tables){

            for(OrderItem item:table.getOrder().getItems()){

                String name=item.getProduct().getName();

                if(name.equalsIgnoreCase("Kola")
                        ||name.equalsIgnoreCase("Ayran")
                        ||name.equalsIgnoreCase("Su")
                        ||name.equalsIgnoreCase("Gazoz")){

                    continue;
                }

                kitchen.add(new KitchenOrder(table,item));
            }

        }

        return kitchen;

    }
}