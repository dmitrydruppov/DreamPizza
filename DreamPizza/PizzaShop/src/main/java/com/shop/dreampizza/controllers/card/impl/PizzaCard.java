package com.shop.dreampizza.controllers.card.impl;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Dmytro_Druppov on 10/6/2016.
 */
public class PizzaCard {

    private List<Integer> countList = new ArrayList<>();
    private List<Integer> pizzaList = new ArrayList<>();


    public void addPizza(int pizza, int count) {
        if(pizzaList.contains(pizza)) {
            int index = pizzaList.indexOf(pizza);
            if(countList.get(index)!= null)
                countList.set(index, count);
        } else {
            pizzaList.add(pizza);
            countList.add(count);
        }
    }

    public void clear() {
        countList.clear();
        pizzaList.clear();
    }

    public boolean removePizza(int id) {
        boolean condition = false;
        if(pizzaList.contains(id)) {
            int index = pizzaList.indexOf(id);
            countList.remove(index);
            pizzaList.remove(index);
            condition = true;
        }
        return condition;
    }

    public PizzaCard getCard() {
        return new PizzaCard();
    }

    public List<Integer> getCountList() {
        return countList;
    }

    public void setCountList(List<Integer> countList) {
        this.countList = countList;
    }

    public List<Integer> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Integer> pizzaList) {
        this.pizzaList = pizzaList;
    }
}
