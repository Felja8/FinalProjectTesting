package org.example;

import java.net.Inet4Address;
import java.sql.*;

public class Methods {

    final String url;
    final String username;
    final String password;
    private Connection connection;

    public Methods(String url, String username, String password) throws ClassNotFoundException, SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        connection = initializeConnection();
    }

    public Connection initializeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public void insertData(String column1Value, String column2Value, String column3Value) throws SQLException {
        String sqlInsert = "INSERT INTO students (id, name, gdp) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, column1Value);
            preparedStatement.setString(2, column2Value);
            preparedStatement.setString(3, column3Value);
            preparedStatement.executeUpdate();
        }
    }

    public void updateData( Integer newGDP, Integer iD) throws SQLException {
        String sqlUpdate = "UPDATE students SET gdp = ?  WHERE id = ?" ;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setInt(1, newGDP);
            preparedStatement.setInt(2, iD);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteData(String condition) throws SQLException {
        String sqlDelete = "DELETE FROM students WHERE " + condition;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.executeUpdate();
        }
    }

    public void printAllStudents() throws SQLException {
        String query = "SELECT * FROM students";
        System.out.println("\n");
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float gdp = resultSet.getFloat("gdp");
                System.out.println("ID: " + id + ", Name: " + name + ", GDP: " + gdp);
            }
        }

    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
