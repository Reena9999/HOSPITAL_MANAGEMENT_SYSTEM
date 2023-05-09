import java.sql.*;
public class InsertData {
    public static void main(String[] args) throws Exception {
        try{
            //loads the classes in the lib folder
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
            Statement statement=connection.createStatement();

            statement.executeUpdate("insert into specialist value('001','Anesthesiology'),('002','Cardiology'),('003','Dermatology'),('004','Emergency medicine'),('005','Endocrinology'),('006','Gastroenterology'),('007','Hematology'),('008','Infectious diseases'),('009','Neurology'),('010','Gynecology'),('011','Oncology'),('012','Ophthalmology'),('013','Orthopedics'),('014','Otolaryngology'),('015','Pediatrics'),('016','Physical medicine and rehabilitation'),('017','Psychiatry'),('018','Pulmonology'),('019','Radiology'),('020','Rheumatology')");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
