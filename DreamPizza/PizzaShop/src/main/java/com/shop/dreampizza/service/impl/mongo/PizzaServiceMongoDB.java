package com.shop.dreampizza.service.impl.mongo;

import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.bean.ShopStock;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.dao.RecipeDao;
import com.shop.dreampizza.dao.ShopStockDao;
import com.shop.dreampizza.service.PizzaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
@Service
public class PizzaServiceMongoDB implements PizzaService {

    private static final Logger LOG = Logger.getLogger(PizzaServiceMongoDB.class);
    @Autowired private PizzaDao pizzaDao;
    @Autowired private ShopStockDao recipeDao;

    @Override
    public Pizza[] getAllPizzas() {
        Pizza pizzas[] = pizzaDao.getPizzas();
        for(Pizza pizza : pizzas) {
            Recipe[] recipe = recipeDao.getProductsByPizzaId(pizza.getId());
            LOG.info(recipe);
            pizza.setRecipe(recipeDao.getProductsByPizzaId(pizza.getId()));
            pizza.setCost(pizzaDao.findCostPizza(pizza));
        }
        LOG.info(Arrays.toString(pizzas));
        return pizzas;
    }

    public static void main(String[] args) {

    }
}
