package com.dream.pizza.convertor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/21/2016.
 */
public interface Convertor<T> {
    T convert(ResultSet resultSet) throws SQLException;
}
