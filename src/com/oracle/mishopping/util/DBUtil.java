package com.oracle.mishopping.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    /**
     * 获得c3p0连接池对象
     */
    private static ComboPooledDataSource ds = new ComboPooledDataSource();

    /**
     * 获取数据库连接对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获的c3p0对象
     */
    public static DataSource getDataSource(){
        return ds;
    }
}
