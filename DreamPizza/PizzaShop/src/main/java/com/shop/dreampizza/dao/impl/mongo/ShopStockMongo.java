package com.shop.dreampizza.dao.impl.mongo;

import com.mongodb.Mongo;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.bean.ShopStock;
import com.shop.dreampizza.dao.ShopStockDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */

@Repository
public class ShopStockMongo implements ShopStockDao {

    private static final Logger LOG = Logger.getLogger(ShopStock.class);
    @Autowired private MongoOperations mongoOperations;

    @Override
    public Recipe[] getProductsByPizzaId(int id) {
        Pizza pizza = mongoOperations.findOne(Query.query(Criteria.where("_id").is(id)), Pizza.class);
        for(Recipe recipe : pizza.getRecipe()) {
            int productId = recipe.getShopStock().getId();
            LOG.info("id: " + productId);
                ShopStock shopStock = mongoOperations.findOne(Query.query(Criteria.where("_id").is(productId)), ShopStock.class);
                LOG.info("* " + shopStock);
                LOG.info("product " + productId + " - " + shopStock.getName());
                recipe.setShopStock(shopStock);
        }
        System.out.println(Arrays.toString(pizza.getRecipe()));
        return pizza.getRecipe();
    }

    @Override
    public void update(Recipe[] recipe) {
        for(Recipe product : recipe) {
            int fullAmount = product.getShopStock().getAmount() - product.getAmount();
            mongoOperations.updateFirst(Query.query(Criteria.where("id").is(1)), Update.update("amount", fullAmount), ShopStock.class);
        }
    }

    public static void main(String[] args) throws Exception {
        MongoOperations mongoOperations = new MongoTemplate(new Mongo(), "dreampizza");
        Pizza shopStock = mongoOperations.findOne(Query.query(Criteria.where("id").is(1)), Pizza.class);
        System.out.println(shopStock);
    }
}
