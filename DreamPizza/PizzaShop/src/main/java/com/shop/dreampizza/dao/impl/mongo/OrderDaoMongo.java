package com.shop.dreampizza.dao.impl.mongo;

import com.shop.dreampizza.bean.Order;
import com.shop.dreampizza.bean.Pizza;
import com.shop.dreampizza.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
        TypedAggregation<Pizza> agg = Aggregation.newAggregation(Pizza.class, Aggregation.match(Criteria.where("_id").is(idPizza)));
        return null;
    }

    @Override
    public boolean makeOrder(Order order) {
        return false;
    }

    @Override
    public Order[] getAllOrders() {
        return new Order[0];
    }
}
