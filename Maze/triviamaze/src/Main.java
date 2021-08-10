import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static int userAnswer;
    public static void main(String[] args) throws ClassNotFoundException {

        Maze maze = new Maze();
        //Rooms
        Room start = maze.getRooms()[0][0];
        Room current = start;
        Room end = maze.getRooms()[3][3];
        int entry = maze.getRooms()[0][0].getRoomId();

        System.out.println("Current Room is : " + current.getRoomId());
        maze.displayMaze(current.getRoomId(),end.getRoomId());
        boolean correct ;

        while ((current.getRoomId() != end.getRoomId() && checkWay(current)) ) {
            int choice = displayChoice(current);
            correct = question();
            if(correct)
                System.out.println("YOU ARE RIGHT MOVED TO THE NEXT ROOM!!!");
            else
                System.out.println("YOU ARE WRONG");
            switch (choice) {
                case 1:
                    if(correct) {
                        current = current.getUp();
                        break;
                    }else {
                        current.setUp(null);
                        break;
                    }
                case 2:
                    if(correct) {
                        current = current.getRight();
                        break;
                    }else {
                        current.setRight(null);
                        break;
                    }
                case 3:
                    if(correct) {
                        current = current.getDown();
                        break;
                    }else {
                        current.setDown(null);
                        break;
                    }
                case 4:
                    if(correct) {
                        current = current.getLeft();
                        break;
                    }else{
                        current.setLeft(null);
                        break;
                    }

            }



            System.out.println("Current Room is : " + current.getRoomId());
            maze.displayMaze(current.getRoomId(),end.getRoomId());
        }
        if (current.getRoomId() == end.getRoomId()) {
            System.out.print("YOU WON !!!!");
        } else {
            System.out.print("YOU LOST !!!!");
        }
    }


    public static boolean checkWay(Room r) {
        if(r.getDown() == r && r.getLeft() == r && r.getUp() == r && r.getRight() == r )
            return false;
        return true;

    }

    public static int displayChoice(Room r) {
        int result = -1;
        Scanner input = new Scanner(System.in);
        if(checkWay(r)) {
            System.out.println("Which way do you want to move ? ");
            if(r.getUp() != r) {
                //System.out.println(r.getUp().getRoomId());
                System.out.println( "1.UP");
            }
            if(r.getRight() != r) {
                //System.out.println(r.getRight().getRoomId());
                System.out.println( "2.RIGHT");
            }
            if(r.getDown() != r ) {
                //System.out.println(r.getDown().getRoomId());
                System.out.println( "3.DOWN");
            }
            if(r.getLeft() != r) {
                //System.out.println(r.getLeft().getRoomId());
                System.out.println( "4.LEFT");
            }
        }
        else
            return -1;

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                result = 1;
                break;
            case 2:
                result = 2;
                break;
            case 3:
                result = 3;
                break;
            case 4:
                result = 4;
                break;
            default:
                System.out.println("ERROR WRONG COICE");
                result = 0;
        }
        return result;
    }
    public static boolean question() throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);
        Random tq =new Random();
        int temp = tq.nextInt(10)+1;
        System.out.println(temp);
        int answer = -1;
        String dburl = "jdbc:sqlite:/C:/sql/db/qustions.db";
        Class.forName("org.sqlite.JDBC");
        Connection conn =null;
        try {

            conn = DriverManager.getConnection(dburl);
            String sql = "SELECT * FROM multiple_choice WHERE id=" + temp;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                String question = rs.getString("question");
                System.out.println(question);
                String a= rs.getString("choice_A");
                System.out.println("1. " + a);
                String b= rs.getString("choice_B");
                System.out.println("2. " + b);
                String c= rs.getString("choice_C");
                System.out.println("3. " + c);
                String d= rs.getString("choice_D");
                System.out.println("4. " + d);
                answer= rs.getInt("answer");

            }
           // rs.close();
            //conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //accept user input
        userAnswer = input.nextInt();
        if (userAnswer == answer) {
            return true;
        }
        return false;
    }



}
