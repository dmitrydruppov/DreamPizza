package com.shop.dreampizza.bean;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
public class Ingredient {

    private Order order;
    private ShopStock shopStock;
    private Ingredient ingredient;
    private int amount;

    public ShopStock getShopStock() {
        return shopStock;
    }

    public void setShopStock(ShopStock shopStock) {
        this.shopStock = shopStock;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
