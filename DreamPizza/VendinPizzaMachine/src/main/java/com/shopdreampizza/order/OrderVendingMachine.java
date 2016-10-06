package com.shopdreampizza.order;

import java.util.Map;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */
public interface OrderVendingMachine {

    void getAllPizzas();

    void removeFromOrder();

    void addPizza(int id);

    void addIngridient(int id);

    void makeOrder(Map<Integer, Integer> pizzas);
}
