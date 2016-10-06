package com.dream.pizza;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Dmytro_Druppov on 9/27/2016.
 */
public class DataBaseConnection {

    public Connection getConection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3307/globalstorage", "root", "");
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return connection;
    }

}
