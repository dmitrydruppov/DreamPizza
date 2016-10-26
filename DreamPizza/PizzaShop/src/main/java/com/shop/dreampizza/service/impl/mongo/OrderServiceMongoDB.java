package com.shop.dreampizza.service.impl.mongo;

import com.shop.dreampizza.bean.*;
import com.shop.dreampizza.dao.OrderDao;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.dao.ShopStockDao;
import com.shop.dreampizza.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
@Service
@Qualifier("order")
public class OrderServiceMongoDB implements OrderService {

    private static final Logger LOG = Logger.getLogger(OrderService.class);
    @Autowired private PizzaDao pizzaDao;
    @Autowired private OrderDao orderDao;
    @Autowired private ShopStockDao recipeDao;
    private static final String DEFAULT_ADDRESS = "Pushkinskaya st";

    public Order[] getAllOrders() {
        return orderDao.getAllOrders();
    }


    public Order getOrderById(String order) {
        return orderDao.getOrderById(order);
    }


//    public String makeOrderService(OrderBody orderBody) {
//        List<PizzaOrder> orderedPizzas = new ArrayList<>();
//        System.out.println(Arrays.toString(orderBody.getAmountArray()));
//        System.out.println(Arrays.toString(orderBody.getIdArray()));
//
//        Integer[] idArray = orderBody.getIdArray();
//        Integer[] amountArray = orderBody.getAmountArray();
//
//        BigDecimal costOrder = new BigDecimal(0);
//        for(int i = 0; i < idArray.length; i++) {
//            PizzaOrder pizzaOrder = new PizzaOrder();
//            Pizza pizza = pizzaDao.getPizzaById(idArray[i]);
//            Recipe[] recipe = recipeDao.getProductsByPizzaId(pizza.getId());
//            pizza.setRecipe(recipe);
//            pizza.setCost(pizzaDao.findCostPizza(pizza));
//            pizzaOrder.setPizza(pizza);
//            orderedPizzas.add(pizzaOrder);
//            costOrder = costOrder.add(new BigDecimal(pizzaOrder.getPizza().getCost().doubleValue()).multiply(new BigDecimal(amountArray[i])));
//        }
//        Order order = new Order();
//        order.setCost(costOrder);
//        order.setPizzaOrder(orderedPizzas.toArray(new PizzaOrder[orderedPizzas.size()]));
//        order.setDate(new Date());
//        orderDao.makeOrder(order);
//        return "ok: " + order.getId();
//    }


    @Override
    public String makeOrder(int[] idArray, int[] amountArray) {
        List<PizzaOrder> orderedPizzas = new ArrayList<>();

        BigDecimal costOrder = new BigDecimal(0);
        for(int i = 0; i < idArray.length; i++) {
            PizzaOrder pizzaOrder = new PizzaOrder();
            Pizza pizza = pizzaDao.getPizzaById(idArray[i]);
            Recipe[] recipe = recipeDao.getProductsByPizzaId(pizza.getId());
            pizza.setRecipe(recipe);
            pizza.setCost(pizzaDao.findCostPizza(pizza));
            pizzaOrder.setPizza(pizza);
            orderedPizzas.add(pizzaOrder);
            costOrder = costOrder.add(new BigDecimal(pizzaOrder.getPizza().getCost().doubleValue()).multiply(new BigDecimal(amountArray[i])));
        }
        Order order = new Order();
        order.setCost(costOrder);
        order.setPizzaOrder(orderedPizzas.toArray(new PizzaOrder[orderedPizzas.size()]));
        order.setDate(new Date());
        orderDao.makeOrder(order);
        return "ok: " + order.getId();
    }
}
