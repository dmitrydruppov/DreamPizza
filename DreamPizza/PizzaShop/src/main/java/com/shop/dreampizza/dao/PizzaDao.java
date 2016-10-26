package com.shop.dreampizza.dao;

import com.shop.dreampizza.bean.Pizza;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
public interface PizzaDao {

    Pizza[] getPizzas();

    Pizza getPizzaById(int id);

    String addPizza(Pizza pizza);

    boolean removePizza(int id);

    boolean updatePizza(int id, Pizza pizza);

    BigDecimal findCostPizza(Pizza pizza);

}
