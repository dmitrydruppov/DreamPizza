package com.shop.dreampizza.service.impl;

import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.service.PizzaService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */

@WebService(endpointInterface = "com.shop.dreampizza.service.PizzaService")
public class PizzaServiceImpl implements PizzaService {

    @Override
    public List<Pizza> getAllPizzas() {
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setName("Margarita");

        Pizza pizza2 = new Pizza();
        pizza2.setId(2);
        pizza2.setName("Mozzarella");

        pizzas.add(pizza1);
        pizzas.add(pizza2);
        return pizzas;
    }

}
