package com.shop.dreampizza.dao.impl;

import com.shop.dreampizza.bean.Dough;
import com.shop.dreampizza.dao.DoughDao;
import com.shop.dreampizza.db.connection.FactoryDataSource;
import com.shop.dreampizza.db.convertor.impl.DoughConvertor;
import com.shop.dreampizza.transaction.TransactionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public class DoughDaoImpl implements DoughDao {

    private static final String GET_DOUGH_BY_ID = "SELECT * FROM dough WHERE idDough=?";

    @Override
    public Dough getDoughById(int id) {
        Dough dough = null;
        ResultSet rs = null;
        Connection con = TransactionManager.getConnection();
        try(PreparedStatement preparedStatement = con.prepareStatement(GET_DOUGH_BY_ID);) {

            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if(rs.next()) {
                dough = new DoughConvertor().convert(rs);
            }
            return dough;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
