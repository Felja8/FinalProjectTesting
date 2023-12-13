package org.example;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class MethodsTest {

    @Test
    public void insertDataTest() throws SQLException, ClassNotFoundException {
        Methods methods = new Methods("jdbc:mysql://localhost/jdbcdemo", "root", "209rootYYZX");
        methods.insertData("1", "Oleg", "5");
        String query = "SELECT * FROM students WHERE id = 1";
        boolean dataInserted = false;
        try (Connection conn = methods.initializeConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String name = rs.getString("name"); // Assuming the column name is 'name'
                if ("Oleg".equals(name)) {
                    dataInserted = true;
                }
            }
        }

        assertTrue("Data was not inserted as expected", dataInserted);

        String cleanupQuery = "DELETE FROM students WHERE id = 1";
        try (Connection conn = methods.initializeConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(cleanupQuery);
        }
    }

    @Test
    public void updateDataTest() throws SQLException, ClassNotFoundException {
        Methods methods = new Methods("jdbc:mysql://localhost/jdbcdemo", "root", "209rootYYZX");


        methods.updateData( 4,1);


        boolean updateSuccessful = false;
        String query = "SELECT * FROM students WHERE id = 1";
        try (Connection conn = methods.initializeConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String gdp = rs.getString("gdp");
                if ("4".equals(gdp)) {
                    updateSuccessful = true;
                }
            }
        }


        assertTrue("Data was not updated as expected", updateSuccessful);


    }

    @Test
    public void deleteDataTest() throws SQLException, ClassNotFoundException {
        Methods methods = new Methods("jdbc:mysql://localhost/jdbcdemo", "root", "209rootYYZX");


        methods.insertData("1", "Oleg", "5");


        methods.deleteData("1");

        boolean deleteSuccessful = true;
        String query = "SELECT * FROM students WHERE id = 1";
        try (Connection conn = methods.initializeConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                deleteSuccessful = false;
            }
        }


        assertTrue("Data was not deleted as expected", deleteSuccessful);


    }

}