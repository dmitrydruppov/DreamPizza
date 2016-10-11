import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.shop.dreampizza.service.bean.Order;
import com.shop.dreampizza.service.order.impl.OrderService;
import com.shop.dreampizza.service.order.impl.OrderServiceImplServiceShop1;
import com.shop.dreampizza.service.order.impl.OrderServiceImplServiceShop2;
import com.shop.dreampizza.service.order.net.IntArray;
import com.shop.dreampizza.service.pizza.Pizza;
import com.shop.dreampizza.service.pizza.PizzaArray;
import com.shop.dreampizza.service.pizza.Recipe;
import com.shop.dreampizza.service.pizza.impl.PizzaService;
import com.shop.dreampizza.service.pizza.impl.PizzaServiceImplServiceShop1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class OrderTestShop1 {

    private static final String QUEUE_NAME = "order";
    private static void showMenu() {
        System.out.println("Привет DreamPizza1 рада тебя встретить и угостить сочным куском нашей неповтаримой пиццы ");
        PizzaServiceImplServiceShop1 pizzaServiceImplService = new PizzaServiceImplServiceShop1();
        PizzaService service = pizzaServiceImplService.getPizzaServiceImplPort();
        PizzaArray pizzaArray = service.getAllPizzas();
        List<Pizza> pizzaList = pizzaArray.getItem();
        for(Pizza piz : pizzaList) {
            System.out.print("код: " + piz.getId() + ", " + piz.getName() + " -рецепт: ");
            for(Recipe recipe : piz.getRecipe()) {
                System.out.print(recipe.getShopStock().getName() + ", ");
            }
            System.out.println();
        }
    }

    public static void main(String args[]) throws IOException, TimeoutException {
        showMenu();

        OrderServiceImplServiceShop2 orderServiceImplService = new OrderServiceImplServiceShop2();
        OrderService orderService = orderServiceImplService.getOrderServiceImplPort();
        IntArray intArray1 = new IntArray();
        IntArray intArray2 = new IntArray();
        Map<Integer, Integer> pizzas = new HashMap<>();
        pizzas.put(3, 1);
        pizzas.put(2, 1);
        System.out.println("--------ЗАКАЗ---------");
        for(Map.Entry<Integer, Integer> entryPizza : pizzas.entrySet()) {

            intArray1.getItem().add(entryPizza.getKey());
            intArray2.getItem().add(entryPizza.getValue());
            System.out.println("код :  " + entryPizza.getKey()  + ", количество: " + entryPizza.getValue()  );
        }
        orderService.makeOrder(intArray1, intArray2);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "order made";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
