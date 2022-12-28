package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Question {

    private int id;
    private int quiz_id;
    private String q_text;
    private String correct_choice;

    public Question(int id, int quiz_id, String q_text, String correct_choice){
        this.id = id;
        this.quiz_id = quiz_id;
        this.q_text = q_text;
        this.correct_choice = correct_choice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQ_text() {
        return q_text;
    }

    public void setQ_text(String q_text) {
        this.q_text = q_text;
    }

    public String getCorrect_choice() {
        return correct_choice;
    }

    public void setCorrect_choice(String correct_choice) {
        this.correct_choice = correct_choice;
    }

    public static ArrayList<Question> getQuestions(int quizID){
        String query = "SELECT * FROM question WHERE quiz_id = ?";
        ArrayList<Question> questions = new ArrayList<>();
        Question question;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, quizID);

            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int quiz_id = resultSet.getInt(2);
                String q_text = resultSet.getString(3);
                String correct_choice = resultSet.getString(4);

                question = new Question(id, quiz_id, q_text, correct_choice);
                questions.add(question);
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
        return questions;
    }


    public static int getNumOfQuestion(int quizID){
        String query = "SELECT count(*) from question where quiz_id = ?";
        int num = 0;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, quizID);

            ResultSet resultSet = pr.executeQuery();

            if (resultSet.next()){
                num = resultSet.getInt(1);
            }

            pr.close();
        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
        return num;
    }

    public static boolean addQuestion(int contentID, String q_text, String correct_choice, String A, String B, String C, String D){
        String[] questionID = {"id"};
        int question_id = -1;
        String queryQuestion = "INSERT INTO question(quiz_id, q_text, correct_choice) VALUES(?, ?, ?)";
        Connection conn = null;
        try {
            // Connection'ı parametre olarak diğer metotlara da verdik. Eğer hata olursa
            // rollback edilecek olan connection aynı connection olmalı.
            conn = DBConnector.getInstance();
            conn.setAutoCommit(false);

            int quizID = Quiz.doesQuizExist(contentID);
            if (quizID == -1){
                try{
                    Quiz.addQuiz(contentID, conn);
                    quizID = Quiz.doesQuizExist(contentID);
                }catch (RuntimeException ex){
                    throw new SQLException(ex);
                }
            }

            System.out.println(Quiz.doesQuizExist(contentID));

            PreparedStatement pr = conn.prepareStatement(queryQuestion, questionID);
            pr.setInt(1, quizID);
            pr.setString(2, q_text);
            pr.setString(3, correct_choice);

            int result = pr.executeUpdate();
            ResultSet resultSet = null;

            System.out.println("INSERT question => affected rows: " + result);
            if (result>0){
                resultSet = pr.getGeneratedKeys();
                if(resultSet.next()){
                    question_id = resultSet.getInt(1);
                }
            }


            if(question_id == -1) throw new RuntimeException("Question Insertion failed.");

            try{
                Choice.addChoices(question_id, conn, A, B, C, D);
            }catch (RuntimeException ex){
                throw new SQLException(ex);
            }

            conn.commit();

            if(resultSet != null){
                resultSet.close();
            }
            pr.close();

        } catch (SQLException e) {
            try{
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Rolling Back :)");
                }
            }catch (SQLException ex){
                System.out.println(e.getMessage());
            }

            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            return false; // throw new RuntimeException(e); idi ancak mesasge göstermek için false döndürdük.
        }
        return true;
    }

    public static boolean delete(int questionID){
        String query = "DELETE FROM question WHERE id = ?";
        boolean isDeleted = false;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, questionID);

            int result = pr.executeUpdate();
            System.out.println("DELETE question => affected rows: " + result);

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

    public static boolean update(int questionID, String qText, String correctChoice, String A, String B, String C, String D){
        String updateQuestion = "UPDATE question SET q_text = ?, correct_choice = ? WHERE id = ?";

        String updateChoices = "UPDATE choices\n" +
                                "SET choice_text = CASE choice_letter\n" +
                                                "WHEN 'A' THEN ?\n" +
                                                "WHEN 'B' THEN ?\n" +
                                                "WHEN 'C' THEN ?\n" +
                                                "WHEN 'D' THEN ?\n" +
                                                "ELSE choice_text\n" +
                                                "END\n" +
                                "WHERE question_id = ?";

        Connection conn = null;
        try {
            conn = DBConnector.getInstance();
            conn.setAutoCommit(false);

            PreparedStatement pr = conn.prepareStatement(updateQuestion);
            pr.setString(1, qText);
            pr.setString(2, correctChoice);
            pr.setInt(3, questionID);

            int result = pr.executeUpdate();
            System.out.println("UPDATE question => affected rows: " + result);

            pr = conn.prepareStatement(updateChoices);
            pr.setString(1, A);
            pr.setString(2, B);
            pr.setString(3, C);
            pr.setString(4, D);
            pr.setInt(5, questionID);

            result = pr.executeUpdate();
            System.out.println("UPDATE choices => affected rows: " + result);

            conn.commit();
            pr.close();


        } catch (SQLException e) {
            try{
               if(conn != null){
                   conn.rollback();
                   System.out.println("Rolled Back :)");
               }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }
        return true;
    }
}
