package com.codecool.krk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDB {

    protected static Connection connection;
    protected Statement statement;

    public void connect() {

        this.statement = null;


        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Guestbook", "postgres", "asdf");
                Class.forName("org.postgresql.Driver");
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }


    public void executeQuery(String query) {
        connect();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
