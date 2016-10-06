package com.shop.dreampizza.bean;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */
public class PizzaOrder {

    private Pizza pizza;
    private Order order;
    private int amount;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
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
