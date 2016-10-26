package com.shop.dreampizza.dao.impl;

import com.shop.dreampizza.bean.*;
import com.shop.dreampizza.dao.PizzaDao;
import com.shop.dreampizza.db.connection.FactoryDataSource;
import com.shop.dreampizza.db.convertor.impl.PizzaConvertor;
import com.shop.dreampizza.transaction.TransactionManager;
import org.apache.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
public class PizzaDaoImpl implements PizzaDao {

    private static final Logger LOG = Logger.getLogger(PizzaDaoImpl.class);
    private static final String SQL_GET_PIZZAS = "SELECT * FROM pizza";
    private static final String SQL_GET_PIZZA = "SELECT * FROM pizza WHERE idPizza=?";
    //private static final String SQL_GET_DOUGH = "SELECT * FROM dough WHERE idDough=?";

    @Override
    public String addPizza(Pizza pizza) {
        return null;
    }

    @Override
    public boolean removePizza(int id) {
        return false;
    }

    @Override
    public boolean updatePizza(int id, Pizza pizza) {
        return false;
    }

    @Override
    public Pizza[] getPizzas() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        Connection con = TransactionManager.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement(SQL_GET_PIZZAS);
            ResultSet rs = preparedStatement.executeQuery();)
        {
            while(rs.next()) {
                Pizza pizza = new PizzaConvertor().convert(rs);
                pizzas.add(pizza);
            }
            LOG.info("give pizzas to client");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pizzas.toArray(new Pizza[pizzas.size()]);
    }

    @Override
    public BigDecimal findCostPizza(Pizza pizza) {
        return null;
    }

    @Override
    public Pizza getPizzaById(int id) {
        Pizza pizza = null;
        Connection con = TransactionManager.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement(SQL_GET_PIZZA)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()) {
                    pizza = new PizzaConvertor().convert(rs);
                }
                LOG.info("give pizzas to client");
            return pizza;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
