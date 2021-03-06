package com.shop.dreampizza.service.impl.mongo;

import com.shop.dreampizza.bean.ListPizza;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.dao.ShopStockDao;
import com.shop.dreampizza.dao.impl.mongo.PizzaDaoMongo;
import com.shop.dreampizza.dao.impl.mongo.ShopStockMongo;
import com.shop.dreampizza.service.PizzaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */



@Service
@Qualifier("site")
public class PizzaServiceMongoDB implements PizzaService {

    private static final Logger LOG = Logger.getLogger(PizzaServiceMongoDB.class);
    @Autowired private PizzaDao pizzaDao;
    @Autowired private ShopStockDao recipeDao;

    public Pizza[] getAllPizzas() {
        List<Pizza> pizzas = Arrays.asList(pizzaDao.getPizzas());
        for(Pizza pizza : pizzas) {
            Recipe[] recipe = recipeDao.getProductsByPizzaId(pizza.getId());
            LOG.info(recipe);
            pizza.setRecipe(recipe);
            pizza.setCost(pizzaDao.findCostPizza(pizza));
        }
        LOG.info(pizzas);
        return pizzas.toArray(new Pizza[pizzas.size()]);
//        ListPizza newListPizzas = new ListPizza();
//        newListPizzas.setName("Hello world");
//        newListPizzas.setListPizzas(pizzas);
//        return newListPizzas;
    }


    public Pizza getPizzaById(int id) {
        Pizza pizza = pizzaDao.getPizzaById(id);
        Recipe[] recipe = recipeDao.getProductsByPizzaId(pizza.getId());
        LOG.info(recipe);
        pizza.setRecipe(recipe);
        pizza.setCost(pizzaDao.findCostPizza(pizza));
        LOG.info(pizza);
        return pizza;
    }

    public static void main(String[] args) {

    }
}
