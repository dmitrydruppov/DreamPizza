package com.shop.dreampizza.db.convertor.impl;

import com.shop.dreampizza.bean.Dough;
import com.shop.dreampizza.db.convertor.Convertor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public class DoughConvertor implements Convertor<Dough> {

    @Override
    public Dough convert(ResultSet resultSet) throws SQLException {
        Dough dough = new Dough();
        dough.setIdDough(resultSet.getInt("idDough"));
        dough.setName(resultSet.getString("name"));
        dough.setCost(resultSet.getBigDecimal("cost"));
        return  dough;
    }
}
