package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;
import com.patikaDev.Helper.Helper;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private String id;
    private String name;
    private String username;
    private String password;
    private String role;

    public User(String id, String name, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // fetch all the rows from the table user of database. Add them to an arrayList and return it.
    public static ArrayList<User> getListOfUsers(){
        ArrayList<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        User tempUser;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while(resultSet.next()){
                tempUser = new User();
                tempUser.setId(resultSet.getString("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setUsername(resultSet.getString("username"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setRole(resultSet.getString("user_role"));

                users.add(tempUser);
            }
            st.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }

        return users;
    }

    // if user exists return true; else return false
    public static boolean checkUserExists(String username){
        String query = "SELECT * FROM user WHERE username= ?";
        boolean doesUserExist = false;

        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);

            ResultSet resultSet = pr.executeQuery();

            // fetchOne() : ilk satırı al.
            if (resultSet.next()){
                doesUserExist = true;
            }

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }
        return doesUserExist;
    }

    public static User fetchOneEducator(String userID){
        String query = "SELECT * FROM user WHERE id= ? AND user_role = 'educator'";
        User user = null;

        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, userID);

            ResultSet resultSet = pr.executeQuery();

            // fetchOne() : ilk satırı al.
            if (resultSet.next()){
                user = new Educator();

                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("user_role"));
            }

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
            System.out.println(e.getStackTrace().toString());
        }
        return user;
    }

    // if user exists return true; else return false
    public static User fetchUserForLogin(String username, String password){
        String query = "SELECT * FROM user WHERE username= ? AND password= sha2(?, 256)";
        User user = null;

        try{
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);

            ResultSet resultSet = pr.executeQuery();

            // fetchOne() : ilk satırı al.
            if (resultSet.next()){
                String role = resultSet.getString("user_role");

                // When logging in, it needs an operator to navigate to Operator Screen,
                // a student to navigate to Student Screen, and an educator to Educator Screen.
                // If we do not use polymorphism here, type casting will be a problem and
                // it will throw runtime exception.
                // #####
                // For ex: Dog myDog = (Dog) new Animal();
                // this code causes runtime exception.
                // The reason is: 'every dog is an animal, but every animal may not be a dog'
                // #####
                // So, we used polymorphism to avoid the error.
                // Animal animal = new Dog();
                // This animal is actually a dog. So, we can later cast the animal to dog easily:
                // Dog myDog = (Dog) animal;
                switch (role){
                    case "operator":
                        user = new Operator();
                        break;
                    case "educator":
                        user = new Educator();
                        break;
                    case "student":
                        user = new Student();
                        break;
                    default:
                        user = new User();
                }

                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(role);
            }

        }catch(SQLException e){
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }
        return user;
    }

    // insert a user to the DB
    public static boolean add(String name, String username, String password, String userRole){
        String query = "INSERT INTO user(id, name, username, password, user_role) VALUES(uuid(), ?, ?, sha2(?, 256), ?)";
        boolean isAdded = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, password);
            pr.setString(4, userRole);

            int result = pr.executeUpdate();
            System.out.println("INSERT => affected rows: " + result);

            pr.close();

            isAdded = (result != 0);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }
        return isAdded;
    }

    // delete a user from DB
    public static boolean deleteUser(String username){
        boolean isDeleted = false;
        String query = "DELETE FROM user WHERE username = ?";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setString(1, username);
            int result = pr.executeUpdate(); // here, result is the affected rows

            System.out.println("DELETE => affected rows: " + result);

            isDeleted = (result != 0);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }

        return isDeleted;
    }

    public static boolean updateUserWithPassword(String ID, String name, String username, String password, String role){
        boolean isUpdated = false;
        String query = "UPDATE user SET name=?, username=?, password=sha2(?, 256), user_role=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, password);
            pr.setString(4, role);
            pr.setString(5, ID);

            int result = pr.executeUpdate();

            System.out.println("UPDATE => affected rows: " + result);

            isUpdated = (result!=0);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }

        return  isUpdated;
    }

    public static boolean updateUserWithoutPassword(String ID, String name, String username, String role){
        boolean isUpdated = false;
        String query = "UPDATE user SET name=?, username=?, user_role=? WHERE id=?";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            pr.setString(2, username);
            pr.setString(3, role);
            pr.setString(4, ID);

            int result = pr.executeUpdate();

            System.out.println("UPDATE => affected rows: " + result);

            isUpdated = (result!=0);

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }

        return  isUpdated;
    }

    // fetch all the rows satisfying search filter from the table user of database. Add them to an arrayList and return it.
    public static ArrayList<User> searchUsers(String name, String username, String role){
        ArrayList<User> users = new ArrayList<>();
        String query = prepareSearchQuery(name, username, role);
        User tempUser;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while(resultSet.next()){
                tempUser = new User();
                tempUser.setId(resultSet.getString("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setUsername(resultSet.getString("username"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setRole(resultSet.getString("user_role"));

                users.add(tempUser);
            }
            st.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }

        return users;
    }

    private static String prepareSearchQuery(String name, String username, String role){
        String query = "SELECT * FROM user WHERE name LIKE '%{{name}}%' AND username LIKE '%{{username}}%' AND user_role LIKE '%{{role}}%'";
        query = query.replace("{{name}}", name);
        query = query.replace("{{username}}", username);
        query = query.replace("{{role}}", role);

        System.out.println("query: " + query);
        return  query;
    }

    public static ArrayList<User> getListOfEducators(){
        ArrayList<User> educators = new ArrayList<>();
        String query = "SELECT * FROM user WHERE user_role = 'educator'";
        User tempUser;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while(resultSet.next()){
                tempUser = new Educator();
                tempUser.setId(resultSet.getString("id"));
                tempUser.setName(resultSet.getString("name"));
                tempUser.setUsername(resultSet.getString("username"));
                tempUser.setPassword(resultSet.getString("password"));
                tempUser.setRole(resultSet.getString("user_role"));

                educators.add(tempUser);
            }
            st.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorErrorCode: " + e.getErrorCode());
        }

        return educators;
    }
}
