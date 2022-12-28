package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Content {
    private int id;
    private int course_id;
    private String title;
    private String description;
    private String link;

    private Course course;

    public Content(int id, int course_id, String title, String description, String link) {
        this.id = id;
        this.course_id = course_id;
        this.title = title;
        this.description = description;
        this.link = link;
        //this.course = Course.fetchOne(course_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static ArrayList<Content> getCourseContents(int course_id){
        String query = "SELECT * FROM content WHERE course_id = ?";
        ArrayList<Content> contents = new ArrayList<>();
        Content content;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, course_id);

            ResultSet resultSet = pr.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int course_id_ = resultSet.getInt("course_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String link = resultSet.getString("link");

                content = new Content(id, course_id_, title, description, link);

                contents.add(content);
            }

            pr.close();
            resultSet.close();

            // BY
            //DBConnector.getInstance().close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
        }
        return contents;
    }



    public static ArrayList<Content> getContents(String user_id){
        String query = "SELECT * FROM content LEFT JOIN\n" +
                "course ON content.course_id = course.id\n" +
                "WHERE course.user_id = ?";
        ArrayList<Content> contents = new ArrayList<>();
        Content content;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, user_id);

            ResultSet resultSet = pr.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int course_id = resultSet.getInt("course_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String link = resultSet.getString("link");

                content = new Content(id, course_id, title, description, link);

                contents.add(content);
            }

            pr.close();
            resultSet.close();

            // BY
            //DBConnector.getInstance().close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
        }
        return contents;
    }

    public static boolean addContent(int course_id, String title, String description, String link){
        String query = "INSERT INTO content(course_id, title, description, link) VALUES(?, ?, ?, ?);";
        boolean isAdded = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, course_id);
            pr.setString(2, title);
            pr.setString(3, description);
            pr.setString(4, link);

            int result = pr.executeUpdate();
            System.out.println("INSERT content => affected rows: " + result);

            isAdded = (result != 0);

            pr.close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
        }
        return isAdded;
    }

    public static boolean updateContent(int id, int course_id, String title, String description, String link){
        String query = "UPDATE content SET course_id=?, title=?, description=?, link=? WHERE id=?";
        boolean isUpdated = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, course_id);
            pr.setString(2, title);
            pr.setString(3, description);
            pr.setString(4, link);
            pr.setInt(5, id);

            int result = pr.executeUpdate();
            System.out.println("UPDATE content => affected rows: " + result);

            isUpdated = (result != 0);

            pr.close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
        }
        return isUpdated;
    }
    public static boolean doesContentExist(String title, String link){
        String query = "SELECT * FROM content WHERE title = ? OR link = ?";
        boolean doesExist = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, title);
            pr.setString(2, link);

            ResultSet resultSet = pr.executeQuery();

            if(resultSet.next()){
                doesExist = true;
            }

            pr.close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + Arrays.stream(e.getStackTrace()).sorted());
        }
        return doesExist;
    }

    public static boolean deleteContent(int contentID){
        String query = "DELETE FROM content WHERE id = ?";
        boolean isDeleted = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, contentID);

            int result = pr.executeUpdate();

            isDeleted = (result != 0);

            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

}
