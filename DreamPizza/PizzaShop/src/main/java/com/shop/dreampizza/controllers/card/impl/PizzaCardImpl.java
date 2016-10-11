package com.shop.dreampizza.controllers.card.impl;

import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.controllers.card.PizzaCard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro_Druppov on 10/6/2016.
 */
@Component
public class PizzaCardImpl implements PizzaCard {

    private Map<Pizza, Integer> shopCard;

    public PizzaCardImpl() {
        shopCard = new HashMap<>();
    }

    @Override
    public void addPizza(Pizza pizza, int count) {
        shopCard.put(pizza, count);
    }

    @Override
    public void clear() {
        shopCard.clear();
    }

    @Override
    public void removePizza(int id) {
        Pizza pizza = null;
        for(Map.Entry<Pizza, Integer> pizzaEntry : shopCard.entrySet()) {
            if(pizzaEntry.getKey().getId() == id) {
                pizza = pizzaEntry.getKey();
            }
        }
        if(pizza != null) {
            shopCard.remove(pizza);
        }
    }

    @Override
    public Map<Pizza, Integer> getCard() {
        return Collections.unmodifiableMap(shopCard);
    }
}
