package com.shop.dreampizza.bean;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */

public class Recipe {

    @DBRef
    private ShopStock shopStock;
    @DBRef
    private Pizza pizza;
    private int amount;

    public ShopStock getShopStock() {
        return shopStock;
    }

    public void setShopStock(ShopStock shopStock) {
        this.shopStock = shopStock;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "shopStock=" + shopStock +
                ", amount=" + amount +
                '}';
    }
}
