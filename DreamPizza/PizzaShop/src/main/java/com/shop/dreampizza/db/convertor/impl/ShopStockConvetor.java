package com.shop.dreampizza.db.convertor.impl;

import com.shop.dreampizza.bean.ShopStock;
import com.shop.dreampizza.db.convertor.Convertor;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class ShopStockConvetor implements Convertor<ShopStock> {

    @Override
    public ShopStock convert(ResultSet resultSet) throws SQLException {
        ShopStock shopStock = new ShopStock();
        shopStock.setPrice(resultSet.getBigDecimal("price"));
        shopStock.setAmount(resultSet.getInt("amount"));
        shopStock.setName(resultSet.getString("name"));
        shopStock.setId(resultSet.getInt("idStock"));
        return shopStock;
    }
}
