package com.example.tcss360_triviamaze.model;

import java.sql.*;
import java.util.Random;

public class Question {
    private String userAnswer;
    private String userOption1, userOption2, userOption3, userOption4;
    private String userQuestion;


    public Question(String questionType) throws ClassNotFoundException {

        Random tq = new Random();
        int temp = tq.nextInt(10)+1;
        System.out.println(temp);

        String dburl = "jdbc:sqlite:/C:\\Users\\jarri\\Documents\\GitHub\\TCSS360_TriviaMaze\\src\\qustions.db";
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;

        if (questionType.equals("multiple_choice")) {
            try {
                int answer = -1;
                conn = DriverManager.getConnection(dburl);
                String sql = "SELECT * FROM multiple_choice WHERE id=" + temp;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    userQuestion = rs.getString("question");
//                System.out.println(question);
                    userOption1 = rs.getString("choice_A");
//                System.out.println("1. " + a);
                    userOption2 = rs.getString("choice_B");
//                System.out.println("2. " + b);
                    userOption3 = rs.getString("choice_C");
//                System.out.println("3. " + c);
                    userOption4 = rs.getString("choice_D");
//                System.out.println("4. " + d);

                    answer = rs.getInt("answer");
                    if (answer == 1) {
                        userAnswer = userOption1;
                    } else if (answer == 2) {
                        userAnswer = userOption2;
                    } else if (answer == 3) {
                        userAnswer = userOption3;
                    } else if (answer == 4) {
                        userAnswer = userOption4;
                    }


                }
                rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (questionType.equals("Short_answer")) {
            try {

                conn = DriverManager.getConnection(dburl);
                String sql = "SELECT * FROM Short_answer WHERE id=" + temp;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    userQuestion = rs.getString("question");

                    userAnswer = rs.getString("answer");

                }
                rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (questionType.equals("true_or_false")) {
            try {

                conn = DriverManager.getConnection(dburl);
                String sql = "SELECT * FROM true_or_false WHERE id=" + temp;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    userQuestion = rs.getString("question");

                    userAnswer = rs.getString("answer");

                }
                rs.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public String getUserOption1() {
        return userOption1;
    }

    public String getUserOption2() {
        return userOption2;
    }

    public String getUserOption3() {
        return userOption3;
    }

    public String getUserOption4() {
        return userOption4;
    }

    public boolean isCorrect(String theAnswer) {
        return userAnswer.equals(theAnswer);
    }



}
