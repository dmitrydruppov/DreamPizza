package com.shop.dreampizza.db.convertor.impl;

import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.bean.ShopStock;
import com.shop.dreampizza.db.convertor.Convertor;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public class RecipeConvertor implements Convertor<Recipe> {

    @Override
    public Recipe convert(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setAmount(resultSet.getInt("inPizza"));
        ShopStock shopStock = new ShopStock();
        shopStock.setId(resultSet.getInt("idStock"));
        shopStock.setName(resultSet.getString("name"));
        shopStock.setAmount(resultSet.getInt("amount"));
        shopStock.setPrice(resultSet.getBigDecimal("price"));
        recipe.setShopStock(shopStock);
        return recipe;
    }
}
