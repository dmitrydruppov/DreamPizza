package com.shop.dreampizza.db.convertor.impl;

import com.shop.dreampizza.bean.Dough;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.bean.PizzaOrder;
import com.shop.dreampizza.db.convertor.Convertor;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/27/2016.
 */
public class PizzaOrderConvertor implements Convertor<PizzaOrder> {

    @Override
    public PizzaOrder convert(ResultSet resultSet) throws SQLException {
        PizzaOrder pizzaOrder = new PizzaOrder();
        pizzaOrder.setAmount(resultSet.getInt("amount"));
        Pizza pizza = new Pizza();
        pizza.setName(resultSet.getString("name"));
        pizza.setId(resultSet.getInt("idPizza"));
        Dough dough = new Dough();
        dough.setIdDough(resultSet.getInt("sizeId"));
        pizza.setDough(dough);
        pizzaOrder.setPizza(pizza);
        return  pizzaOrder;
    }
}
