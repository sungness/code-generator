package com.sungness.code.generate.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract interface MapCmd<T> {
    public abstract T getObjecFromRs(ResultSet paramResultSet)
            throws SQLException;
}
