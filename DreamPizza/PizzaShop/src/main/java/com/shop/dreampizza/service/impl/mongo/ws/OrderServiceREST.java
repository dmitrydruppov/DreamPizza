package com.shop.dreampizza.service.impl.mongo.ws;

import com.shop.dreampizza.bean.*;
import com.shop.dreampizza.dao.OrderDao;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.dao.RecipeDao;
import com.shop.dreampizza.dao.ShopStockDao;
import com.shop.dreampizza.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Dmytro_Druppov on 10/17/2016.
 */
@Service
@Produces(MediaType.APPLICATION_JSON)
@Path("/serviceOrder")
public class OrderServiceREST extends SpringBeanAutowiringSupport implements OrderService {

    @Autowired private OrderDao orderDao;
    @Autowired private PizzaDao pizzaDao;
    @Autowired private ShopStockDao recipeDao;

    @GET
    @Path("/getAllOrders")
    public Order[] getAllOrders() {
        return new Order[0];
    }

    @GET
    @Path("/getOrder/{id}")
    public Order getOrderById(@PathParam("order") String order) {
        return null;
    }

//    @POST
//    @Path("/makeOrder")
//    public void makeOrder(OrderBody orderBody) throws Exception {
//
//        List<PizzaOrder> orderedPizzas = new ArrayList<>();
////        JSONArray ids = orderBody.getJSONArray("idArray");
////        JSONArray amount = orderBody.getJSONArray("amountArray");
//
//        Integer[] idArray = orderBody.getIdArray();
//        Integer[] amountArray = orderBody.getAmountArray();
//
////        for(int i = 0; i < ids.length(); i++) {
////            idArray[i] = ids.getInt(i);
////            amountArray[i] = amount.getInt(i);
////        }
//
//        BigDecimal costOrder = new BigDecimal(0);
//        for(int i = 0; i < idArray.length; i++) {
//            PizzaOrder pizzaOrder = new PizzaOrder();
//            pizzaOrder.setPizza(pizzaDao.getPizzaById(idArray[i]));
//            orderedPizzas.add(pizzaOrder);
//            costOrder = costOrder.add(new BigDecimal(pizzaOrder.getPizza().getCost().doubleValue()).multiply(new BigDecimal(amountArray[i])));
//        }
//        Order order = new Order();
//        order.setCost(costOrder);
//        order.setPizzaOrder(orderedPizzas.toArray(new PizzaOrder[orderedPizzas.size()]));
//        order.setDate(new Date());
//        orderDao.makeOrder(order);
//    }

    @POST
    @Path("/makeOrder")
    public void makeOrderService(OrderBody orderBody) {
        List<PizzaOrder> orderedPizzas = new ArrayList<>();


        System.out.println(Arrays.toString(orderBody.getAmountArray()));
        System.out.println(Arrays.toString(orderBody.getIdArray()));

        Integer[] idArray = orderBody.getIdArray();
        Integer[] amountArray = orderBody.getAmountArray();

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
    }

    @Override
    public String makeOrder(int[] idArray, int[] amountArray) {
        return null;
    }
}
