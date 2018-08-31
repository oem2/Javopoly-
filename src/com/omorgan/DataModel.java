package com.omorgan;

import java.sql.*;

public class DataModel {

    String username;
    Integer age;

    DataModel(String username, int age) {

        //initialize class
        this.username = username;
        this.age = age;
    }

    private Connection connect() {
        //SQLite connection string
        String url = "jdbc:sqlite:game.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createDBGameTables() {

        //Create the database tables for the game
        String sql_1 = "CREATE TABLE IF NOT EXISTS users ( \n"
                + "ID           INT     PRIMARY KEY, \n"
                + "username     TEXT    NOT NULL, \n"
                + "age          INT     NOT NULL \n"
                + ");";

        String sql_2 = "CREATE TABLE IF NOT EXISTS stores ( \n"
                + "ID           INT     PRIMARY KEY, \n"
                + "storeName    STRING  NOT NULL, \n"
                + "storeType    STRING  NOT NULL \n"
                + ");";

        String sql_3 = "CREATE TABLE IF NOT EXISTS items ( \n"
                + "ID           INT     PRIMARY KEY, \n"
                + "itemName     STRING  NOT NULL, \n"
                + "price        DOUBLE  NOT NULL \n"
                + ");";

        String sql_4 = "CREATE TABLE IF NOT EXISTS inventory ( \n"
                + "ID           INT     PRIMARY KEY, \n"
                + "quantity     INT     NOT NULL, \n"
                + "itemID       INT,     \n"
                + "storeID      INT,     \n"
                + "userID       INT,     \n"
                + "FOREIGN KEY (itemID) REFERENCES items (ID), \n"
                + "FOREIGN KEY (storeID) REFERENCES stores (ID), \n"
                + "FOREIGN KEY (userID) REFERENCES stores (ID) \n"
                + ");";

        String sql_5 = "CREATE TABLE IF NOT EXISTS wallet ( \n"
                + "ID           INT     PRIMARY KEY, \n"
                + "cash         DOUBLE  NOT NULL, \n"
                + "credit       DOUBLE  NOT NULL, \n"
                + "userID       INT,     \n"
                + "FOREIGN KEY (userID) REFERENCES stores (ID) \n"
                + ");";

        try (Connection conn = this.connect();
               Statement stmt = conn.createStatement()) {

            //create batch statement to execute all queries at once
            stmt.addBatch(sql_1);
            stmt.addBatch(sql_2);
            stmt.addBatch(sql_3);
            stmt.addBatch(sql_4);
            stmt.addBatch(sql_5);
            stmt.executeBatch();
            System.out.println("users table successfully created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUserInfo(String username, int age) {

        String sql = "INSERT INTO users(username, age) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            System.out.println("user info successfully recorded in database");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkIfUserExists(String username) {

        String sql = "SELECT * FROM users WHERE username = ?";

        boolean userExists = false;

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            boolean val = rs.next();
            if(val==false) {
                System.out.println("That username is available. Welcome " + username + " Let the games begin.");
                userExists = false;
            }
            else{
                while (rs.next()) {

                    String uID = rs.getString("ID");
                    String uname = rs.getString("username");

                    // System.out.println("A user already exists with the username: " + uname);
                    // System.out.println("Please pick another username.");
                }
                userExists = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       return userExists;
    }

    public static void initNewUser(String username) {

        //Grab username and create new wallet for said user
        String sql = "INSERT INTO wallet(cash, credit) VALUES (?, ?) WHERE userID = ?";
    }

}
