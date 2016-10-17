package com.shop.dreampizza.service.impl.mongo;

import com.dream.pizza.service.OrderArray;
import com.dream.pizza.service.impl.OrderService;
import com.dream.pizza.service.net.IntArray;
import com.shop.dreampizza.bean.Order;
import org.springframework.stereotype.Service;

import javax.jws.WebParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * Created by Dmytro_Druppov on 9/30/2016.
 */
public class OrderServiceMongoDB implements com.shop.dreampizza.service.OrderService {

    public Order[] getAllOrders() {
        return new Order[0];
    }


    public Order getOrderById(String order) {
        return null;
    }


    public void makeOrder(int[] idArray, int[] amountArray) {

    }
}
