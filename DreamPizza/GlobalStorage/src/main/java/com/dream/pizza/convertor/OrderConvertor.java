package com.dream.pizza.convertor;

import com.dream.pizza.service.Order;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class OrderConvertor implements Convertor<Order> {

    @Override
    public Order convert(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        XMLGregorianCalendar calendar = new XMLGregorianCalendarImpl();
        calendar.toGregorianCalendar().setTime(resultSet.getTimestamp("date"));
        order.setDate(calendar);
        order.setCost(resultSet.getBigDecimal("cost"));
        order.setId(resultSet.getString("orderId"));
        return order;
    }
}
