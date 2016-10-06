package com.shop.dreampizza.transaction;

import java.sql.Connection;

/**
 * Created by Dmytro_Druppov on 9/25/2016.
 */
public class TransactionManager {

    private static final ThreadLocal<Connection> connection = new ThreadLocal<>();

    public static Connection getConnection() {
        return connection.get();
    }

    public void setConnection(Connection connection) {
        this.connection.set(connection);
    }

    public void clearConnection(){
        connection.set(null);
    }

}
