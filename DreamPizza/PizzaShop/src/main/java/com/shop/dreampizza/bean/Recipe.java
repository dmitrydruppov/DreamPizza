package com.shop.dreampizza.bean;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
@XmlRootElement(name = "Recipe")
public class Recipe implements Serializable {

    @DBRef
    private ShopStock shopStock;
    @DBRef
    private Pizza pizza;
    private int amount;

    @XmlElement(name = "shopStock")
    public ShopStock getShopStock() {
        return shopStock;
    }

    public void setShopStock(ShopStock shopStock) {
        this.shopStock = shopStock;
    }

    @XmlElement(name = "pizza")
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
