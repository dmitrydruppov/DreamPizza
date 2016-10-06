package com.dream.pizza.dao.impl;

import com.dream.pizza.DataBaseConnection;
import com.dream.pizza.convertor.OrderConvertor;
import com.dream.pizza.dao.OrderDao;
import com.dream.pizza.service.Order;

import java.sql.*;

/**
 * Created by Dmytro_Druppov on 9/27/2016.
 */
public class OrderDaoImpl implements OrderDao {

    private static final String ORDER_BY_ID = "SELECT * FROM globalstorage.order WHERE id=?";
    private static final String MAKE_ORDER = "INSERT INTO globalstorage.order (id, date, cost) VALUES (?,?,?)";

    @Override
    public void makeOrder(Order order) {
        try(Connection connection = new DataBaseConnection().getConection();
            PreparedStatement preparedStatement = connection.prepareStatement(MAKE_ORDER)) {
            preparedStatement.setString(1, order.getId());
            System.out.println(order.getDate());
            preparedStatement.setTimestamp(2, new Timestamp(order.getDate().toGregorianCalendar().getTimeInMillis()));
            preparedStatement.setBigDecimal(3, order.getCost());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderById(String id) {
        Order order = null;
        try(Connection connection = new DataBaseConnection().getConection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ORDER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            OrderConvertor convertor = new OrderConvertor();
            if(rs.next()) {
                order = convertor.convert(rs);
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
