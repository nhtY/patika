package com.patikaDev.Helper;

import java.sql.*;

public class DBConnector {
    private static Connection connection = null;

    public Connection connectDB(){

        try {
            connection = DriverManager.getConnection(Config.DB_URL, Config.DB_USERNAME, Config.DB_PASSWORD);
            System.out.println("Connected to DB");
        }catch(SQLException e){
            System.out.println("SQLException : " + e.getMessage()+
                    "\n" + "SQlState: " + e.getSQLState() +
                    "\n" + "VendorErrorCode: " + e.getErrorCode());
        }

        return connection;
    }

    public static Connection getInstance(){
        if(connection==null){
            connection = new DBConnector().connectDB();
        }
        return connection;
    }

}
