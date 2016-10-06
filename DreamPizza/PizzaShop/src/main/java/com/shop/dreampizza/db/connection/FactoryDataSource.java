package com.shop.dreampizza.db.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Dmytro_Druppov on 9/19/2016.
 */
public class FactoryDataSource {

    private static final Logger LOG = Logger.getLogger(FactoryDataSource.class);
    private DataSource dataSource;
    private String defaultPath = "java:/comp/env/jdbc/MyLocalPizzaDB";

    public FactoryDataSource(String path) {
        defaultPath = path;
    }

    public FactoryDataSource() {}

    public Connection getConnection() {
        if(dataSource == null) {
            try {
            LOG.info("create connection");
            Context initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup(defaultPath);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            LOG.info("create connection");
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
