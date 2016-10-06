package com.shop.dreampizza.db.convertor.impl;

import com.shop.dreampizza.bean.Dough;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.db.convertor.Convertor;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public class PizzaConvertor implements Convertor<Pizza> {

    @Override
    public Pizza convert(ResultSet resultSet) throws SQLException {
            Pizza pizza = new Pizza();
            pizza.setName(resultSet.getString("name"));
            pizza.setId(resultSet.getInt("idPizza"));
            Dough dough = new Dough();
            dough.setIdDough(resultSet.getInt("sizeId"));
            pizza.setDough(dough);
            return pizza;
    }
}
