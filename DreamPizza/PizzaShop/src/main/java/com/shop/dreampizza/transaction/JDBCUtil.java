package com.shop.dreampizza.transaction;

import com.shop.dreampizza.exception.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class JDBCUtil {

    public static void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void close(PreparedStatement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void rollBack(Connection connection) {
        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
