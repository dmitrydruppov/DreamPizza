package com.shop.dreampizza.dao.impl.mongo;

import com.shop.dreampizza.bean.Order;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
@Repository
public class OrderDaoMongo implements OrderDao {

    @Autowired private MongoOperations mongoOperations;

    @Override
    public Order getOrderById(String id) {
        return mongoOperations.findOne(Query.query(Criteria.where("id").is(id)), Order.class);
    }

    @Override
    public String makeOrder(Order order) {
        mongoOperations.insert(order);
        return "ok";
    }

    @Override
    public Order[] getAllOrders() {
        List<Order> orderList = mongoOperations.findAll(Order.class);
        return orderList.toArray(new Order[orderList.size()]);
    }
}
