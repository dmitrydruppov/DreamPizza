package com.shop.dreampizza.dao.impl;

import com.shop.dreampizza.bean.PizzaOrder;
import com.shop.dreampizza.dao.PizzaOrderDao;
import com.shop.dreampizza.db.convertor.impl.PizzaOrderConvertor;
import com.shop.dreampizza.transaction.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class PizaOrderDaoImpl implements PizzaOrderDao {


    private static final String GET_PIZZAS_BY_ORDER = "SELECT p.idPizza, p.name, p.percent, p.sizeId, po.amount FROM pizza AS p INNER JOIN pizza_order AS po" +
            " ON p.idPizza = po.pizzaId INNER JOIN dreampizzashop.order AS o ON po.orderId = o.orderId WHERE o.orderId = ?";
    private static final String INSERT_PIZZA_ORDER = "INSERT INTO pizza_order (pizzaId, orderId, amount) VALUES (?,?,?)";

    @Override
    public PizzaOrder[] getPizzasByOrder(String id) {
        try {
            List<PizzaOrder> pizzaOrders = new ArrayList<>();
            Connection connection = TransactionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PIZZAS_BY_ORDER);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            PizzaOrderConvertor convertor = new PizzaOrderConvertor();
            while(resultSet.next()) {
                PizzaOrder pizzaOrder = convertor.convert(resultSet);
                pizzaOrders.add(pizzaOrder);
            }
            return pizzaOrders.toArray(new PizzaOrder[pizzaOrders.size()]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addPizzasToOrder(PizzaOrder pizzaOrder) {
        try {
            Connection connection = TransactionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PIZZA_ORDER);

            preparedStatement.setInt(1, pizzaOrder.getPizza().getId());
            preparedStatement.setString(2, pizzaOrder.getOrder().getId());
            preparedStatement.setInt(3, pizzaOrder.getAmount());

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
