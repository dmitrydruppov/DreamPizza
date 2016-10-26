package com.shop.dreampizza.dao.impl.mongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.bean.Recipe;
import com.shop.dreampizza.bean.ShopStock;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.dao.impl.PizzaDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
@Repository
public class PizzaDaoMongo implements PizzaDao {

    private static final String COLLECTION_NAME = "pizza";
    private static final int DEFAULT_WORK_PERCANTAGE = 10;
    @Autowired private MongoOperations mongoOperations;

    @Override
    public String addPizza(Pizza pizza) {
        mongoOperations.insert(pizza, COLLECTION_NAME);
        return String.valueOf(pizza.getId());
    }

    @Override
    public boolean removePizza(int id) {
        Pizza pizza = getPizzaById(id);
        mongoOperations.remove(pizza, COLLECTION_NAME);
        return true;
    }

    @Override
    public boolean updatePizza(int id, Pizza pizza) {
        Pizza pizz = getPizzaById(id);
        pizz.setName(pizza.getName());
        pizz.setRecipe(pizza.getRecipe());
        pizz.setDough(pizza.getDough());
        mongoOperations.save(pizz, COLLECTION_NAME);
        return true;
    }

    @Override
    public Pizza getPizzaById(int id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Pizza.class);
    }

    @Override
    public Pizza[] getPizzas() {
        List<Pizza> pizzaList =  mongoOperations.findAll(Pizza.class);
        return pizzaList.toArray(new Pizza[pizzaList.size()]);
    }

    public BigDecimal findCostPizza(Pizza pizza) {
        Recipe[] recipes = pizza.getRecipe();
        BigDecimal costOfPizza = new BigDecimal(pizza.getDough().getCost().doubleValue());

        for(Recipe recipe : recipes) {
            ShopStock product = recipe.getShopStock();
            BigDecimal costOfIngridients = product.getPrice().
                    multiply(BigDecimal.valueOf(recipe.getAmount())).
                    divide(BigDecimal.valueOf(100));
            //System.out.println("ingr: " + costOfIngridients);

            BigDecimal costOfWork = new BigDecimal(costOfIngridients.doubleValue());
            costOfWork = costOfWork.multiply(BigDecimal.valueOf(DEFAULT_WORK_PERCANTAGE))
                    .divide(BigDecimal.valueOf(100));

            costOfPizza = costOfPizza.add(costOfWork).add(costOfIngridients);
            //System.out.println("costWork: " +  costOfWork);
        }
        return costOfPizza.setScale(2, BigDecimal.ROUND_CEILING);
    }

//    public static void main(String[] args) {
//        PizzaDaoMongo pizzaDaoMongo = new PizzaDaoMongo();
//        Pizza pizza = new Pizza();
//        Recipe recipe = new Recipe();
//        recipe.setAmount(100);
//        ShopStock shopStock = new ShopStock();
//        shopStock.setId(1);
//        shopStock.setName("123");
//        shopStock.setAmount(2);
//        shopStock.setPrice(BigDecimal.valueOf(60));
//        recipe.setShopStock(shopStock);
//        Recipe[] recipe1 = new Recipe[] {recipe};
//        pizza.setRecipe(recipe1);
//        System.out.print(pizzaDaoMongo.findCostPizza(pizza));
//    }


//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
//        MongoOperations mongoOperations = (MongoOperations) context.getBean("mongoTemplate");
//        List<Pizza> pizzaList =  mongoOperations.findAll(Pizza.class);
//        System.out.print(pizzaList.get(0).getName());
//    }


}
