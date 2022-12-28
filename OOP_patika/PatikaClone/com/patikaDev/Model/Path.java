package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Path {
    private int ID;
    private String name;

    public Path(int ID, String name){
        this.ID = ID;
        this.name = name;
    }

    public Path(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Path> getPathList(){
        String query = "SELECT * FROM path";
        Path tempPath = null;
        ArrayList<Path> paths = new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while(resultSet.next()){
                tempPath = new Path();
                tempPath.setID(resultSet.getInt(1));
                tempPath.setName(resultSet.getString(2));

                paths.add(tempPath);
            }
            st.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return paths;
    }

    public static boolean checkPathExists(String name){
        boolean doesExist = false;
        String query = "SELECT * FROM path WHERE path_name = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);

            ResultSet resultSet = pr.executeQuery();

            if(resultSet.next()){
                doesExist = true;
            }

            pr.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return  doesExist;
    }
    public static boolean addPath(String name){
        boolean isAdded = false;
        String query = "INSERT INTO path(path_name) VALUES(?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            int result = pr.executeUpdate();

            System.out.println("INSERT path => affected rows: " + result);

            isAdded = (result!=0);

            pr.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }

        return isAdded;
    }

    public static boolean updatePath(String name, int id){
        boolean isUpdated = false;
        String query = "UPDATE path SET path_name=? WHERE id=?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, id);

            int result = pr.executeUpdate();
            System.out.println("UPDATE path => affected rows: " + result);

            isUpdated = (result != 0);

            pr.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return isUpdated;
    }

    public static boolean deletePath(int pathID){
        boolean isDeleted = false;
        String query = "DELETE FROM path WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, pathID);

            int result = pr.executeUpdate();
            System.out.println("DELETE path => affected rows: " + result);

            isDeleted = (result != 0);

            pr.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return isDeleted;
    }

    public static Path fetchOne(int id){
        String query = "SELECT *  FROM path WHERE id=?";
        Path path = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);

            ResultSet resultSet = pr.executeQuery();

            if(resultSet.next()){
                path = new Path(resultSet.getInt("id"), resultSet.getString("path_name"));
            }
            pr.close();
            resultSet.close();

            // BY
            //DBConnector.getInstance().close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return path;

    }
}
