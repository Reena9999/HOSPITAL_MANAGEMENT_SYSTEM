import java.sql.*;

public class DatabaseCreation {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");


        try{
            //loads the classes in the lib folder
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
            Statement statement=connection.createStatement();
            ResultSet resultSet1=statement.executeQuery("create table tblScheduledBooking(BookingID varchar(10),PatientID varchar (10),DOCID varchar (10),ScheduledDate DATE,ScheduledTime TIME,Registra varchar (10),PRIMARY KEY (BookingID))");
            ResultSet resultSet2=statement.executeQuery("create table tblRegistras(RegistraID varchar(10),FirstName varchar (50),LastName varchar (50),PhNumber varchar(10),PRIMARY KEY (RegistraID))");
            ResultSet resultSet3=statement.executeQuery("create table tblDoctorAvailability(availableDate DATE,DOCID varchar (10),availabileStartTime TIME,availabileEndTime TIME,PatientToAttend int(5))");
            ResultSet resultSet4=statement.executeQuery("create table tblDoctor(DOCID varchar (10),FirstName varchar (50),LastName varchar (50),Specilization varchar(50),MobileNo varchar(10),Email_ID varchar(10), Address varchar(20))");
            ResultSet resultSet5=statement.executeQuery("create table tblPatient(PatientID varchar(10),FirstName varchar (50),LastName varchar(50),MobileNO varchar(10),EmailID varchar(10), Sex int(1), BloodGroup varchar(10))");
            ResultSet resultSet6=statement.executeQuery("create table tblPatientMedicalHistory(Date date,PatientID varchar (10),DiagnosingDoctor varcahr(50),Symptoms varchar(20))");
            ResultSet resultSet7=statement.executeQuery("create table tblSpecialists(SPID varchar(10),Specialization varcahr(50))");




        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
