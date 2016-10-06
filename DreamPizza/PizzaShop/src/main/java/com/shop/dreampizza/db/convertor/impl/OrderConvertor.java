package com.shop.dreampizza.db.convertor.impl;

import com.shop.dreampizza.bean.Order;
import com.shop.dreampizza.db.convertor.Convertor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class OrderConvertor implements Convertor<Order> {

    @Override
    public Order convert(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setDate(resultSet.getTimestamp("date"));
        order.setCost(resultSet.getBigDecimal("cost"));
        order.setId(resultSet.getString("orderId"));
        return order;
    }
}
