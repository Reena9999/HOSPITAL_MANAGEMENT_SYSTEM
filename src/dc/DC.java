package dc;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */


public class DC {
    public static Connection connection;
    public static Statement st;
    static{
    try {
         connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "********");
         st = connection.createStatement();
}
    catch(SQLException exception){
        System.out.println(exception.getMessage());
    }
    }
}
