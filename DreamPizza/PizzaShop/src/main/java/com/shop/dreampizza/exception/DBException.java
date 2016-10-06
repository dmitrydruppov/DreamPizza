package com.shop.dreampizza.exception;

import java.sql.SQLException;

/**
 * Created by Dmytro_Druppov on 9/26/2016.
 */
public class DBException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DBException(SQLException e) {
        super(e);
    }
}
