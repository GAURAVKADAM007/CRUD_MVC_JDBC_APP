package com.stackninjas.crudapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public static final String HOST = System.getenv("JDBC_HOST1")!=null ? System.getenv("JDBC_HOST") : "localhost";
    public static final String PORT = System.getenv("JDBC_PORT1")!=null ? System.getenv("JDBC_PORT") : "5432";
    public static final String DBNAME = System.getenv("JDBC_DBNAME1")!=null ? System.getenv("JDBC_DBNAME") : "CrudDB";
    public static final String USERNAME = System.getenv("JDBC_USERNAME1")!=null ? System.getenv("JDBC_USERNAME") : "postgres";
    public static final String PASSWORD = System.getenv("JDBC_PASSWORD1")!=null ? System.getenv("JDBC_PASSWORD") : "1234";

    public static final String URL = "jdbc:postgresql://"+HOST+":"+PORT+"/"+DBNAME;

    static {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }
    }

    public static Connection fetchConnection() throws SQLException {
        return DriverManager.getConnection(URL,USERNAME,PASSWORD);
    }
}
