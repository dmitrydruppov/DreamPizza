package com.shop.dreampizza.dao.impl;

import com.shop.dreampizza.bean.Order;
import com.shop.dreampizza.dao.OrderDao;
import com.shop.dreampizza.db.convertor.Convertor;
import com.shop.dreampizza.db.convertor.impl.OrderConvertor;
import com.shop.dreampizza.transaction.TransactionManager;
import org.apache.log4j.Logger;


import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOG = Logger.getLogger(OrderDaoImpl.class);
    private static final String GET_SUM_BY_PizzaId = "SELECT ((SUM(s.price * r.amount / 100)) + (SUM(s.price * r.amount / 100)) * 10 / 100) AS costPizza FROM shop_stock AS s INNER JOIN recipe AS r ON s.idStock = r.stockId\n" +
            "                        INNER JOIN pizza ON r.pizzaId = pizza.idPizza WHERE pizza.idPizza = ?";

    private static final String GET_ALL_ORDERS = "SELECT * FROM dreampizzashop.order";
    private static final String INSERT_ORDER = "INSERT INTO dreampizzashop.order (orderId, date, cost) VALUES (?,NOW(),?)";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM dreampizzashop.order WHERE orderId = ?";

    public BigDecimal findCostPizza(int idPizza) {
        Connection connection = TransactionManager.getConnection();
        BigDecimal cost = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_SUM_BY_PizzaId)) {
            preparedStatement.setInt(1, idPizza);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                cost = rs.getBigDecimal("costPizza");
            }
            return cost;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Order[] getAllOrders() {
        Connection connection = TransactionManager.getConnection();
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS)) {
            OrderConvertor convertor = new OrderConvertor();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Order order = convertor.convert(resultSet);
                orderList.add(order);
            }
            return orderList.toArray(new Order[orderList.size()]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderById(String id) {
        Connection connection = TransactionManager.getConnection();
        Order order = new Order();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
            preparedStatement.setString(1, id);
            OrderConvertor convertor = new OrderConvertor();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order = convertor.convert(resultSet);
            }
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String makeOrder(Order order) {
        Connection connection = TransactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {
            LOG.info("order " + order.getId() +  ", " + order.getCost());
            preparedStatement.setString(1, order.getId());
            preparedStatement.setBigDecimal(2, order.getCost());
            preparedStatement.execute();

            return order.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
