import com.dream.pizza.service.Order;
import com.dream.pizza.service.OrderArray;
import com.dream.pizza.service.impl.OrderService;
import com.dream.pizza.service.impl.OrderServiceImplService;
import com.dream.pizza.service.impl.OrderServiceImplService2;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Dmytro_Druppov on 9/28/2016.
 */
public class ChefMenu {

    private final static String QUEUE_NAME = "order2";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                //String message = new String(body, "UTF-8");

                OrderServiceImplService orderServiceImplService = new OrderServiceImplService();
                OrderService orderService = orderServiceImplService.getOrderServiceImplPort();
                OrderArray orderArray = orderService.getAllOrders();
                List<Order> orderList = orderArray.getItem();
                Collections.sort(orderList, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        return o1.getDate().compare(o2.getDate());
                    }
                });

                Order order = orderList.get(orderList.size() - 1);
                System.out.println("Заказ: " + order.getDate());
                System.out.println("-------ПИЦЦЫ------");
                for(int i = 0; i < order.getPizzaOrder().size(); i++) {
                    com.dream.pizza.service.PizzaOrder po = order.getPizzaOrder().get(i);
                    System.out.println(po.getPizza().getName() + ", размер " + po.getPizza().getDough().getIdDough() +  ", кол-во " + po.getAmount());
                }
                    System.out.println("------------------");
                }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }

}
