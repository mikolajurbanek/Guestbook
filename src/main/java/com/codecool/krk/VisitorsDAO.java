package com.codecool.krk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VisitorsDAO {

    ConnectionToDB connectionToDB;

    public VisitorsDAO(ConnectionToDB connectionToDB) {
        this.connectionToDB = connectionToDB;

    }

    public List<Visitor> selectAllVisit() throws SQLException {

        List<Visitor> visitorList = new ArrayList<>();
        ResultSet resultSet = getDataSet("SELECT * FROM visitors");

        while (resultSet.next()) {
            visitorList.add(new Visitor(resultSet.getString("Name"), resultSet.getString("Surname"), resultSet.getString("Message"), resultSet.getDate("Date")));
        }
        resultSet.close();
        return visitorList;

    }

    public void addNewVisitor(Visitor visitor) {
        connectionToDB.executeQuery(String.format("INSERT INTO visitors(name, surname, message, date) VALUES ('%s', '%s', '%s', '%s');", visitor.getName(), visitor.getSurname(), visitor.getMessage(), visitor.getDate()));
        System.out.println("added");
    }

    public ResultSet getDataSet(String query) {
        connectionToDB.connect();
        try {
            connectionToDB.statement = ConnectionToDB.connection.createStatement();
            ResultSet results = connectionToDB.statement.executeQuery(query);
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
