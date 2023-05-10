import java.sql.*;
public class InsertData {
    public static void main(String[] args) throws Exception {
        try{
            //loads the classes in the lib folder
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
            Statement statement=connection.createStatement();

            statement.executeUpdate("insert into tblspecialists(specialisation) value('Anesthesiology'),('Cardiology'),('Dermatology'),('Emergency medicine'),('Endocrinology'),('Gastroenterology'),('Hematology'),('Infectious diseases'),('Neurology'),('Gynecology'),('Oncology'),('Ophthalmology'),('Orthopedics'),('Otolaryngology'),('Pediatrics'),('Physical medicine and rehabilitation'),('Psychiatry'),('Pulmonology'),('Radiology'),('Rheumatology')");
            statement.executeUpdate("insert into tblRegistras(FirstName,LastName,PhNumber,Password) value('Reena','Aseervadham','7619273936','94807279')");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
