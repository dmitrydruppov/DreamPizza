package com.dream.pizza.dao;

import com.dream.pizza.service.Order;

/**
 * Created by Dmytro_Druppov on 9/27/2016.
 */
public interface OrderDao {

    void makeOrder(Order order);

    Order getOrderById(String id);

}
