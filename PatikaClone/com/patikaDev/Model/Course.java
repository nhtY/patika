package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private String user_id;
    private int path_id;
    private String name;
    private String lang;
    private Path path;
    private User educator;

    private ArrayList<Content> contents;

    public Course(int id, String user_id, int path_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.path_id = path_id;
        this.name = name;
        this.lang = lang;
        this.path = Path.fetchOne(path_id);
        this.educator = User.fetchOneEducator(user_id);
        //this.contents = Content.getCourseContents(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getPath_id() {
        return path_id;
    }

    public void setPath_id(int path_id) {
        this.path_id = path_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public void setContents(ArrayList<Content> contents) {
        this.contents = contents;
    }

    public static ArrayList<Course> getCourseList(){
        String query = "SELECT * FROM course";
        ArrayList<Course> courses = new ArrayList<>();
        Course course;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet resultSet = st.executeQuery(query);

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String user_id = resultSet.getString("user_id");
                int path_id = resultSet.getInt("path_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");

                course = new Course(id, user_id, path_id, name, lang);
                courses.add(course);
            }

            st.close();
            resultSet.close();
            // BY
            //DBConnector.getInstance().close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    +"\nStackTrace: " + e.getStackTrace().toString());
        }
        return courses;
    }

    public static boolean addCourse(String user_id, int path_id, String name, String lang){
        boolean isAdded = false;
        String query = "INSERT INTO course(user_id, path_id, name, lang) VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, user_id);
            pr.setInt(2, path_id);
            pr.setString(3, name);
            pr.setString(4, lang);

            int result = pr.executeUpdate();
            System.out.println("INSERT course => affected rows: " + result);

            isAdded = (result != 0);

            pr.close();


        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return isAdded;
    }

    public static Course fetchOne(int id){
        String query = "SELECT * FROM course WHERE id = ?";
        Course course = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, id);
            ResultSet resultSet = pr.executeQuery();

            if(resultSet.next()){
                int id_ = resultSet.getInt("id");
                String user_id = resultSet.getString("user_id");
                int path_id = resultSet.getInt("path_id");
                String name = resultSet.getString("name");
                String lang  = resultSet.getString("lang");

                course = new Course(id_, user_id, path_id, name, lang);

                pr.close();
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return course;
    }

    public static Course fetchOne(String name){
        String query = "SELECT * FROM course WHERE name = ?";
        Course course = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, name);
            ResultSet resultSet = pr.executeQuery();

            if(resultSet.next()){
                int id_ = resultSet.getInt("id");
                String user_id = resultSet.getString("user_id");
                int path_id = resultSet.getInt("path_id");
                String name_ = resultSet.getString("name");
                String lang  = resultSet.getString("lang");

                course = new Course(id_, user_id, path_id, name_, lang);

                pr.close();
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\n" + e.getStackTrace().toString());
        }
        return course;
    }

    public static ArrayList<Course> getCourseList(String userID){
        String query = "SELECT * FROM course WHERE user_id = ?";
        ArrayList<Course> courses = new ArrayList<>();
        Course course;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, userID);

            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String user_id = resultSet.getString("user_id");
                int path_id = resultSet.getInt("path_id");
                String name = resultSet.getString("name");
                String lang = resultSet.getString("lang");

                course = new Course(id, user_id, path_id, name, lang);

                courses.add(course);
            }

            pr.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage()+
                    "\nSQLState: " + e.getSQLState()+
                    "\nVendorErrorCode: " + e.getErrorCode()+
                    "\nStackTrace: " + e.getStackTrace());
        }
        System.out.println(courses.size());
        return courses;
    }
}
