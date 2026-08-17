package model;

public class KitchenOrder {

    private RestaurantTable table;
    private OrderItem item;

    public KitchenOrder(RestaurantTable table, OrderItem item) {
        this.table = table;
        this.item = item;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public OrderItem getItem() {
        return item;
    }
}