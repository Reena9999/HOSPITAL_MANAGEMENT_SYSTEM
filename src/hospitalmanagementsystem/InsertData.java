/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalmanagementsystem;

import java.sql.*;

public class InsertData {

    public static void main(String[] args) throws Exception {
        try {
            //loads the classes in the lib folder
            Statement statement = dc.DC.connection.createStatement();

            statement.executeUpdate("insert into tblspecialists(specialisation) value('Anesthesiology'),('Cardiology'),('Dermatology'),('Emergency medicine'),('Endocrinology'),('Gastroenterology'),('Hematology'),('Infectious diseases'),('Neurology'),('Gynecology'),('Oncology'),('Ophthalmology'),('Orthopedics'),('Otolaryngology'),('Pediatrics'),('Physical medicine and rehabilitation'),('Psychiatry'),('Pulmonology'),('Radiology'),('Rheumatology')");

            //insert into registra
            statement.executeUpdate("insert into tblRegistras(RegistraID,FirstName,LastName,PhNumber,Password) value(1,'Reena','Aseervadham','7619273936','12345')");

            //insert patient
            statement.executeUpdate("insert into tblPatient(FirstName,LastName,PhNumber,Email,Sex,DateOfBirth,BloodGroup,Password) value('Subramanya','Patil','1267348967','subu123@gmail.com',0,'2000-10-18','B+','12345')");

            //insert doctors
            statement.executeUpdate("insert into tblDoctor(FirstName,LastName,Specialisation,PhNumber,Email,Password) value('Manhindir','Gurpreeth',5,'8754264310','man23gur@gmail.com','5673'),('Indira','Vaadu',2,'3422113311','indy.Vaadu@gmail.com','5673'),('Vinutha','A',11,'5553131444','vinutha.a@gmail.com','5673')");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
