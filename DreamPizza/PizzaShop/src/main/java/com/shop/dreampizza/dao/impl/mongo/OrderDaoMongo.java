package com.shop.dreampizza.dao.impl.mongo;

import com.shop.dreampizza.bean.Order;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
@Repository
public class OrderDaoMongo implements OrderDao {

    @Autowired private MongoOperations mongoOperations;

    @Override
    public Order getOrderById(String id) {
        return null;
    }

    @Override
    public BigDecimal findCostPizza(int idPizza) {
        return null;
    }

    @Override
    public String makeOrder(Order order) {
        mongoOperations.insert(order);
        return "ok";
    }

    @Override
    public Order[] getAllOrders() {
        return new Order[0];
    }
}
