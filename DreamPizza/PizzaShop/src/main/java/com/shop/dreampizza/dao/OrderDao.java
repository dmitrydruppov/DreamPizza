package com.shop.dreampizza.dao;

import com.shop.dreampizza.bean.Order;

import java.math.BigDecimal;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */
public interface OrderDao {

    String makeOrder(Order order);

    Order[] getAllOrders();

    Order getOrderById(String id);

}
