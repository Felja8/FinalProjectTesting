package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            Methods methods = new Methods("jdbc:mysql://localhost/jdbcdemo", "root", "209rootYYZX");

            methods.printAllStudents();
            methods.insertData("4", "Adolf", "2");


            methods.printAllStudents();
            methods.updateData( 3,6);

            methods.printAllStudents();
            methods.deleteData("id = 2");

            methods.printAllStudents();

            methods.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}




