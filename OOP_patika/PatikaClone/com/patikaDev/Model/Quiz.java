package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz {
    private int id;
    private int content_id;

    private String contentTitle;
    private int course_id;
    private String courseName;
    private int numOfQuestion;


    public Quiz(int id, int content_id, int course_id, String contentTitle){
        this.id = id;
        this.content_id = content_id;

        this.contentTitle = contentTitle;
        this.course_id = course_id;
        this.courseName = Course.fetchOne(course_id).getName();
        this.numOfQuestion = Question.getNumOfQuestion(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumOfQuestion() {
        return numOfQuestion;
    }

    public void setNumOfQuestion(int numOfQuestion) {
        this.numOfQuestion = numOfQuestion;
    }

    public static ArrayList<Quiz> getQuizList(String educatorID){
        String query = "SELECT quiz.id, content_id, course_id, title FROM quiz LEFT JOIN\n" +
                "content ON content.id = quiz.content_id\n" +
                "WHERE content.course_id IN  (\n" +
                "SELECT id FROM course WHERE user_id = ?)";
        ArrayList<Quiz> quizes = new ArrayList<>();
        Quiz quiz;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1, educatorID);

            ResultSet resultSet = pr.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int content_id = resultSet.getInt(2);
                int course_id = resultSet.getInt(3);
                String content_title = resultSet.getString(4);

                quiz = new Quiz(id, content_id, course_id, content_title);

                quizes.add(quiz);
            }

            pr.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }

        return quizes;
    }

    public static ArrayList<Quiz> getQuizListByCourseName(int courseID){
        String query = "SELECT quiz.id, content_id, course_id, title FROM quiz LEFT JOIN\n" +
                "content ON content.id = quiz.content_id\n" +
                "WHERE content.course_id = ?";
        ArrayList<Quiz> quizes = new ArrayList<>();
        Quiz quiz;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, courseID);

            ResultSet resultSet = pr.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int content_id = resultSet.getInt(2);
                int course_id = resultSet.getInt(3);
                String content_title = resultSet.getString(4);

                quiz = new Quiz(id, content_id, course_id, content_title);

                quizes.add(quiz);
            }

            pr.close();
            resultSet.close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }

        return quizes;
    }

    public static int doesQuizExist(int content_id){
        String query = "SELECT * FROM quiz WHERE content_id = ?";
        int id = -1; // not exists
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, content_id);

            ResultSet resultSet = pr.executeQuery();

            if(resultSet.next()){
                id = resultSet.getInt(1);
            }

            pr.close();
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
        return id;
    }

    public static int addQuiz(int contentID, Connection conn){
        String[] generatedID = {"id"};
        String query = "INSERT INTO quiz(content_id) VALUES(?)";
        int insertedID = -1;
        try {
            PreparedStatement pr = conn.prepareStatement(query, generatedID);
            pr.setInt(1, contentID);

            int result = pr.executeUpdate();
            System.out.println("INSERT quiz => affected rows: " + result);

            if(result>0){
                try {
                    ResultSet resultSet = pr.getGeneratedKeys();
                    if (resultSet.next()) {
                        insertedID = resultSet.getInt(1);
                    }
                    pr.close();
                    resultSet.close();

                    if(insertedID == -1){
                        throw new Exception("Quiz insertion failed for the quiz with content_id: " + contentID);
                    }

                }catch (Exception e){
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
        return insertedID;
    }

    public static boolean deleteQuiz(int quizID){
        String query = "DELETE FROM quiz WHERE id = ?";
        boolean isDeleted = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, quizID);

            int result = pr.executeUpdate();
            System.out.println("DELETE quiz => affected rows: " + result);

            isDeleted = (result != 0);

            pr.close();
        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

}
