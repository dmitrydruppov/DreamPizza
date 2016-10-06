package com.shop.dreampizza.dao;

import com.shop.dreampizza.bean.PizzaOrder;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public interface PizzaOrderDao {

    void addPizzasToOrder(PizzaOrder pizzaOrder);

    PizzaOrder[] getPizzasByOrder(String id);

}
