import com.shop.dreampizza.bean.Order;
import com.shop.dreampizza.service.impl.OrderServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by Dmitry on 10/14/2016.
 */
public class OrderPublisher {

    public static void main(String[] args) {
        OrderServiceImpl service = new OrderServiceImpl();
        service.getAllOrders();
        Order order = service.getOrderById("1cc0c7e0-1e1c-4cab-94af-614d1c08b106");
        System.out.println(order);
        //Endpoint.publish("http://localhost:8080/orderService", new OrderServiceImpl());
    }
}
