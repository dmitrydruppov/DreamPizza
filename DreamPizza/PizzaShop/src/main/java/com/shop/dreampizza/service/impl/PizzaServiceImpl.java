package com.shop.dreampizza.service.impl;

import com.mysql.jdbc.log.Log;
import com.shop.dreampizza.bean.Ingredient;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.dao.DoughDao;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.dao.RecipeDao;
import com.shop.dreampizza.dao.impl.DoughDaoImpl;
import com.shop.dreampizza.dao.impl.PizzaDaoImpl;
import com.shop.dreampizza.dao.impl.RecipeDaoImpl;
import com.shop.dreampizza.db.connection.FactoryDataSource;
import com.shop.dreampizza.service.PizzaService;
import com.shop.dreampizza.transaction.JDBCUtil;
import com.shop.dreampizza.transaction.TransactionManager;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.sql.Connection;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */

@WebService(endpointInterface = "com.shop.dreampizza.service.PizzaService")
public class PizzaServiceImpl implements PizzaService {

    private static final Logger LOG = Logger.getLogger(PizzaServiceImpl.class);
    private PizzaDao pizzaDao;
    private DoughDao doughDao;
    private RecipeDao recipeDao;

    public PizzaServiceImpl() {
        pizzaDao = new PizzaDaoImpl();
        doughDao = new DoughDaoImpl();
        recipeDao = new RecipeDaoImpl();
    }

    @Override
    public Pizza[] getAllPizzas() {
        Connection con = new FactoryDataSource().getConnection();
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.setConnection(con);
        Pizza[] pizzas = null;
         try {
            pizzas = pizzaDao.getPizzas();
            LOG.info("start set recipe and dough");
            for (Pizza pizza : pizzas) {
                LOG.info("pizza " + pizza.getDough().getIdDough());

                pizza.setDough(doughDao.getDoughById(pizza.getDough().getIdDough()));
                Recipe[] recipe = recipeDao.getRecipeByPizaId(pizza.getId());

                LOG.info("recipe " + recipeDao.getRecipeByPizaId(pizza.getId()));
                if (recipe != null && recipe.length > 0)
                    pizza.setRecipe(recipeDao.getRecipeByPizaId(pizza.getId()));
            }
            LOG.info("return getAllPizzas");

        } catch (Exception e) {
             JDBCUtil.rollBack(con);
        } finally {
             JDBCUtil.close(con);
             transactionManager.clearConnection();
         }
        return pizzas;
    }

}
