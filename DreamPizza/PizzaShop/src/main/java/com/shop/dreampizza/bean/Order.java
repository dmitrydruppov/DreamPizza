package com.shop.dreampizza.bean;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */

@Document(collection = Order.COLLECTION_NAME)
public class Order {

    public static final String COLLECTION_NAME = "order";
    private String id;
    private Date date;
    private String address;
    private String customerName;
    private BigDecimal cost;
    private PizzaOrder[] pizzaOrder;
    //private Ingredient ingredients;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public PizzaOrder[] getPizzaOrder() {
        return pizzaOrder;
    }

    public void setPizzaOrder(PizzaOrder[] pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
    }
//
//    public Ingredient getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(Ingredient ingredients) {
//        this.ingredients = ingredients;
//    }
}
