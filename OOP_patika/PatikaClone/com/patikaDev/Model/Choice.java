package com.patikaDev.Model;

import com.patikaDev.Helper.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Choice {
    private int id;
    private int question_id;
    private String choice_letter;
    private String choice_text;

    public Choice(int id, int question_id, String choice_letter, String choice_text) {
        this.id = id;
        this.question_id = question_id;
        this.choice_letter = choice_letter;
        this.choice_text = choice_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getChoice_letter() {
        return choice_letter;
    }

    public void setChoice_letter(String choice_letter) {
        this.choice_letter = choice_letter;
    }

    public String getChoice_text() {
        return choice_text;
    }

    public void setChoice_text(String choice_text) {
        this.choice_text = choice_text;
    }

    public static boolean addChoices(int questionID, Connection conn, String A, String B, String C, String D){
        String query = "INSERT INTO choices(question_id, choice_letter, choice_text) VALUES "
                + "(?, ?, ?),\n"
                + "(?, ?, ?),\n"
                + "(?, ?, ?),\n"
                + "(?, ?, ?)";
        boolean isAdded;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);

            pr.setInt(1, questionID);
            pr.setString(2, "A");
            pr.setString(3, A);

            pr.setInt(4, questionID);
            pr.setString(5, "B");
            pr.setString(6, B);

            pr.setInt(7, questionID);
            pr.setString(8, "C");
            pr.setString(9, C);

            pr.setInt(10, questionID);
            pr.setString(11, "D");
            pr.setString(12, D);

            int result = pr.executeUpdate();
            System.out.println("INSERT choices => affacted rows: " + result);

            isAdded = (result != 0);

            pr.close();

        } catch (SQLException e) {
            System.out.println("SQLException : " + e.getMessage() +
                    "\nSQLState : " + e.getSQLState() +
                    "\nVendorErrorCode : " + e.getErrorCode()
                    + "\nStackTrace: " + e.getStackTrace().toString());
            throw new RuntimeException(e);
        }

        return isAdded;
    }

    public static ArrayList<Choice> getChoices(int questionID){
        String query = "SELECT * FROM choices WHERE question_id = ?";
        ArrayList<Choice> choices = new ArrayList<>(4);
        Choice choice;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1, questionID);

            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int question_id = resultSet.getInt(2);
                String choice_letter = resultSet.getString(3);
                String choice_text = resultSet.getString(4);

                choice = new Choice(id, question_id, choice_letter, choice_text);
                choices.add(choice);
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
        return choices;
    }
}
