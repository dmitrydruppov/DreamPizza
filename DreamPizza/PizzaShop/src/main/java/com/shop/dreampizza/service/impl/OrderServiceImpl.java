package com.shop.dreampizza.service.impl;

import com.shop.dreampizza.bean.*;
import com.shop.dreampizza.dao.*;
import com.shop.dreampizza.dao.impl.*;
import com.shop.dreampizza.db.connection.FactoryDataSource;
import com.shop.dreampizza.service.OrderService;
import com.shop.dreampizza.transaction.JDBCUtil;
import com.shop.dreampizza.transaction.TransactionManager;
import org.apache.log4j.Logger;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */

@WebService(endpointInterface = "com.shop.dreampizza.service.OrderService")
public class OrderServiceImpl implements OrderService{

    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private PizzaDao pizzaDao;
    private OrderDao orderDao;
    private ShopStockDao shopStockDao;
    private PizzaOrderDao pizzaOrderDao;
    private RecipeDao recipeDao;

    public OrderServiceImpl() {
        pizzaDao = new PizzaDaoImpl();
        shopStockDao = new ShopStockImpl();
        orderDao  = new OrderDaoImpl();
        pizzaOrderDao = new PizaOrderDaoImpl();
        recipeDao = new RecipeDaoImpl();
    }

    @Override
    public Order getOrderById(String id) {
        Connection connection = new FactoryDataSource().getConnection();
        TransactionManager transactionManager = new TransactionManager();
        try {
            Order order = orderDao.getOrderById(id);
            return order;
        } catch (Exception e) {
        JDBCUtil.rollBack(connection);
        throw new RuntimeException(e);
    } finally {
        JDBCUtil.close(connection);
        transactionManager.clearConnection();
    }
    }

    @Override
    public Order[] getAllOrders() {
        Connection connection = new FactoryDataSource().getConnection();
        TransactionManager transactionManager = new TransactionManager();
        try {
            connection.setAutoCommit(false);
            transactionManager.setConnection(connection);
            Order[] orders = orderDao.getAllOrders();
            for(Order order : orders) {
                order.setPizzaOrder(pizzaOrderDao.getPizzasByOrder(order.getId()));
            }
            connection.commit();
            return orders;
        } catch (Exception e) {
            JDBCUtil.rollBack(connection);
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection);
            transactionManager.clearConnection();
        }
    }

    private List<PizzaOrder> getPizzasOrderList(int[] idArrays, int[] amountArrays, Order order, BigDecimal fullCost) {
        List<PizzaOrder> pizzaOrderList = new ArrayList<>();
        for(int i = 0; i < idArrays.length; i++) {
            Pizza pizza = pizzaDao.getPizzaById(idArrays[i]);
            PizzaOrder pizzaOrder = new PizzaOrder();
            pizzaOrder.setOrder(order);
            pizzaOrder.setPizza(pizza);
            pizzaOrder.setAmount(amountArrays[i]);
            shopStockDao.update(recipeDao.getRecipeByPizaId(idArrays[i]));
            fullCost = fullCost.add(orderDao.findCostPizza(idArrays[i]).multiply(new BigDecimal(amountArrays[i])));
            pizzaOrderList.add(pizzaOrder);
        }
        return pizzaOrderList;
    }

    private void fillOrder(int[] idArrays, int[] amountArrays) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        BigDecimal fullCost = new BigDecimal(0);
        List<PizzaOrder> pizzaOrderList = getPizzasOrderList(idArrays, amountArrays, order, fullCost);
        order.setPizzaOrder(pizzaOrderList.toArray(new PizzaOrder[pizzaOrderList.size()]));
        order.setCost(fullCost);
        insertPizzasToOrder(order, pizzaOrderList);
    }

    private void insertPizzasToOrder(Order order, List<PizzaOrder> pizzaOrderList) {
        orderDao.makeOrder(order);
        for (PizzaOrder pizzaOrder : pizzaOrderList) {
            pizzaOrderDao.addPizzasToOrder(pizzaOrder);
        }
    }

    @Override
    public void makeOrder(@WebParam(name = "id") int[] idArrays, @WebParam(partName = "amount") int amountArrays[]) {
        Connection connection = new FactoryDataSource().getConnection();
        TransactionManager transactionManager = new TransactionManager();
        transactionManager.setConnection(connection);
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            fillOrder(idArrays,amountArrays);
            connection.commit();
        } catch (SQLException e) {
            JDBCUtil.rollBack(connection);
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(connection);
            transactionManager.clearConnection();
        }
    }
}
