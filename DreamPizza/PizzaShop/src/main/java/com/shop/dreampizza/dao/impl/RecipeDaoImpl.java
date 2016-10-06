package com.shop.dreampizza.dao.impl;

import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.dao.RecipeDao;
import com.shop.dreampizza.db.connection.FactoryDataSource;
import com.shop.dreampizza.db.convertor.impl.RecipeConvertor;
import com.shop.dreampizza.transaction.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public class RecipeDaoImpl implements RecipeDao {

    private static final String GET_RECIPE_BY_PIZZA_ID = "SELECT  s.idStock, s.amount, s.price, s.name, r.amount AS \n" +
            "            inPizza FROM shop_stock AS s INNER JOIN recipe AS r ON s.idStock = r.stockId\n" +
            "            INNER JOIN pizza ON r.pizzaId = pizza.idPizza WHERE pizza.idPizza = ?";

    @Override
    public Recipe[] getRecipeByPizaId(int id) {
        List<Recipe> recipeList = new ArrayList<>();
        ResultSet rs = null;
        Connection con = TransactionManager.getConnection();
        try(
            PreparedStatement preparedStatement = con.prepareStatement(GET_RECIPE_BY_PIZZA_ID);) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Recipe recipe = new RecipeConvertor().convert(rs);
                recipeList.add(recipe);
            }
            return recipeList.toArray(new Recipe[recipeList.size()]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
