package com.shop.dreampizza.service.impl.mongo.ws;

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
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitry on 10/16/2016.
 */
@Path("/service")
@Service
public class PizzaServiceREST extends SpringBeanAutowiringSupport implements PizzaService{

    private static final Logger LOG = Logger.getLogger(PizzaServiceREST.class);
    @Autowired private PizzaDao pizzaDao;
    @Autowired private ShopStockDao recipeDao;


    @GET
    @Produces("application/json")
    @Path("rest/getMenu")
    public Pizza[] getAllPizzas() {
        List<Pizza> pizzas = Arrays.asList(pizzaDao.getPizzas());
        for (Pizza pizza : pizzas) {
            Recipe[] recipe = recipeDao.getProductsByPizzaId(pizza.getId());
            LOG.info(recipe);
            pizza.setRecipe(recipe);
            pizza.setCost(pizzaDao.findCostPizza(pizza));
        }
        LOG.info(pizzas);
        return pizzas.toArray(new Pizza[pizzas.size()]);
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
