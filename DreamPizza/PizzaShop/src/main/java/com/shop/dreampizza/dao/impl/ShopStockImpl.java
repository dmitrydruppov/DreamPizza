package com.shop.dreampizza.dao.impl;

import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.bean.ShopStock;
import com.shop.dreampizza.dao.ShopStockDao;
import com.shop.dreampizza.db.convertor.impl.RecipeConvertor;
import com.shop.dreampizza.transaction.TransactionManager;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class ShopStockImpl implements ShopStockDao {

    private static final Logger LOG = Logger.getLogger(ShopStockImpl.class);
    private static final String GET_RECIPE_BY_PIZZA_ID = "SELECT  s.idStock, s.amount, s.price, s.name, r.amount AS \n" +
            "            inPizza FROM shop_stock AS s INNER JOIN recipe AS r ON s.idStock = r.stockId\n" +
            "            INNER JOIN pizza ON r.pizzaId = pizza.idPizza WHERE pizza.idPizza = ?";

    private static final String UPDATE_PRODUCT_BY_ID = "UPDATE shop_stock SET amount = ? WHERE idStock = ?";

    @Override
    public Recipe[] getProductsByPizzaId(int id) {
        Connection connection = new TransactionManager().getConnection();
        List<Recipe> products = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_RECIPE_BY_PIZZA_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Recipe product = new RecipeConvertor().convert(rs);
                if(product != null)
                    products.add(product);
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return products.toArray(new Recipe[products.size()]);
    }

    @Override
    public void update(Recipe[] recipe) {
        Connection connection = TransactionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID);
            for(Recipe product : recipe) {
                //TODO Need to specify amount correctly
                int fullAmount = product.getShopStock().getAmount() - product.getAmount();
                LOG.info("product amount " + product.getShopStock().getAmount() + ", for pizza " + product.getAmount());
                if (fullAmount >= 0) {
                    preparedStatement.setInt(1, fullAmount);
                    int productId = product.getShopStock().getId();
                    preparedStatement.setInt(2, productId);
                    preparedStatement.execute();
                } else {
                    throw new RuntimeException("Not enough product " + product.getShopStock().getId()  + " to perform order");
                }
            }
        } catch (SQLException e) { throw  new RuntimeException(e); }
    }
}
