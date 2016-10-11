package com.shop.dreampizza.controllers.card;

import com.shop.dreampizza.bean.Pizza;

import java.util.Map;

/**
 * Created by Dmytro_Druppov on 10/6/2016.
 */
public interface PizzaCard {

    void addPizza(Pizza pizza, int count);

    void removePizza(int id);

    Map<Pizza, Integer> getCard();

    void clear();

}
