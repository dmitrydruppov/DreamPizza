package com.shop.dreampizza.service.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Dmytro_Druppov on 9/28/2016.
 */

public class Order {

    private Map<Integer, Integer> orderedPizza;

    public Map<Integer, Integer> getOrderedPizza() {
        return orderedPizza;
    }

    public void setOrderedPizza(Map<Integer, Integer> orderedPizza) {
        this.orderedPizza = orderedPizza;
    }
}
