import com.dream.pizza.dao.OrderDao;
import com.dream.pizza.dao.impl.OrderDaoImpl;
import com.dream.pizza.service.Order;
import com.dream.pizza.service.OrderArray;
import com.dream.pizza.service.impl.OrderService;
import com.dream.pizza.service.impl.OrderServiceImplService;
import com.dream.pizza.service.impl.OrderServiceImplService2;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dmytro_Druppov on 9/27/2016.
 */
public class ExecuteEveryDay {

    private ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private OrderDao orderDao = new OrderDaoImpl();


    private class Replication implements Runnable {
        List<Service> listShops = new ArrayList<>();
        public Replication() {

            OrderServiceImplService orderServiceImplService = new OrderServiceImplService();
            OrderServiceImplService2 orderServiceImplService2 = new OrderServiceImplService2();

            listShops.add(orderServiceImplService);
            listShops.add(orderServiceImplService2);
        }

        private OrderService getOrderService(Service shop) {
            OrderService orderService = null;
            if(shop instanceof OrderServiceImplService) {
                orderService = ((OrderServiceImplService) shop).getOrderServiceImplPort();
            } if(shop instanceof OrderServiceImplService2) {
                orderService = ((OrderServiceImplService2) shop).getOrderServiceImplPort();
            }
            return orderService;
        }

        private void printOrder(Order order) {
            System.out.println("код: " + order.getId() + ";дата: " + order.getDate() + ";сумма: " + order.getCost());
        }

        @Override
        public void run() {

            for(Service shop : listShops) {
                OrderService orderService = getOrderService(shop);
                OrderArray orderArray = orderService.getAllOrders();
                List<Order> orderList = orderArray.getItem();
                for(Order order : orderList ) {
                    printOrder(order);
                    Order existOrder = orderDao.getOrderById(order.getId());
                    if(existOrder == null) {
                        orderDao.makeOrder(order);
                    }
                }
            }
        }
    }

    public void doTask() {
        scheduledExecutorService.scheduleAtFixedRate(new Replication(), 0 , 10, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
       new ExecuteEveryDay().doTask();
    }

}
