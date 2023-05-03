import java.sql.*;
public class InsertData {
    public static void main(String[] args) throws Exception {
        try{
            //loads the classes in the lib folder
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
            Statement statement=connection.createStatement();

            statement.executeUpdate("insert into specialist value('SP001','Anesthesiology'),('SP002','Cardiology'),('SP003','Dermatology'),('SP004','Emergency medicine'),('SP005','Endocrinology'),('SP006','Gastroenterology'),('SP007','Hematology'),('SP008','Infectious diseases'),('SP009','Neurology'),('SP010','Gynecology'),('SP011','Oncology'),('SP012','Ophthalmology'),('SP013','Orthopedics'),('SP014','Otolaryngology'),('SP015','Pediatrics'),('SP016','Physical medicine and rehabilitation'),('SP017','Psychiatry'),('SP018','Pulmonology'),('SP019','Radiology'),('SP020','Rheumatology')");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
