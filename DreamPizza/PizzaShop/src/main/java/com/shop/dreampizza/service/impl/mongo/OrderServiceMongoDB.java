package com.shop.dreampizza.service.impl.mongo;

import com.dream.pizza.service.OrderArray;
import com.dream.pizza.service.impl.OrderService;
import com.dream.pizza.service.net.IntArray;
import com.shop.dreampizza.bean.Order;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
public class OrderServiceMongoDB implements com.shop.dreampizza.service.OrderService {

    @Override
    public Order[] getAllOrders() {
        return new Order[0];
    }

    @Override
    public Order getOrderById(String order) {
        return null;
    }

    @Override
    public void makeOrder(int[] idArray, int[] amountArray) {

    }
}
