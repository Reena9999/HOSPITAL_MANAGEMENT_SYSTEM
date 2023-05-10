import java.sql.*;

public class DatabaseCreation {
    public static void main(String[] args) throws Exception {
        try{
            //loads the classes in the lib folder
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
            Statement statement=connection.createStatement();

            //Creating the tables of the database
            statement.executeUpdate("create table tblScheduledBooking(BookingID int(10) AUTO_INCREMENT,PatientID int(10),DOCID int (10),ScheduledDate DATE,ScheduledTime TIME,Registra int (10),PRIMARY KEY (BookingID))");
            statement.executeUpdate("create table tblRegistras(RegistraID int(10) AUTO_INCREMENT,FirstName varchar (50),LastName varchar (50),PhNumber varchar(10),Password varchar(50) NOT NULL,PRIMARY KEY (RegistraID))");
            statement.executeUpdate("create table tblDoctorAvailability(availableDate DATE,DOCID int(10),availabileStartTime TIME,availabileEndTime TIME,PatientToAttend int(5))");
            statement.executeUpdate("create table tblDoctor(DOCID int(10) AUTO_INCREMENT,FirstName varchar (50),LastName varchar(50),Specialisation int(10),PhNumber varchar(10),Email varchar(50), location varchar(50),password varchar(50),PRIMARY KEY(DOCID))");
            statement.executeUpdate("create table tblPatient(PatientID int(10) AUTO_INCREMENT,FirstName varchar(50),LastName varchar(50),PhNumber varchar(10),Email varchar(50),Sex int(1),DateOfBirth DATE,BloodGroup varchar(10),Password varchar(50),PRIMARY KEY(PatientID))");
            statement.executeUpdate("create table tblPatientMedicalHistory(Date DATE,PatientID int (10),DiagnosingDoctor int(10),Symptoms varchar(200))");
            statement.executeUpdate("create table tblSpecialists(specialisationID int(10) AUTO_INCREMENT,specialisation varchar(50),PRIMARY KEY(specialisationID))");
            
            //adding foreign keys to the table
            statement.executeUpdate("ALTER TABLE tblScheduledBooking ADD FOREIGN KEY (PatientID) REFERENCES tblPatient(PatientID)");
            statement.executeUpdate("ALTER TABLE tblScheduledBooking ADD FOREIGN KEY (DOCID) REFERENCES tblDoctor(DOCID)");
            statement.executeUpdate("ALTER TABLE tblScheduledBooking ADD FOREIGN KEY (Registra) REFERENCES tblRegistras(RegistraID)");
            statement.executeUpdate("ALTER TABLE tblPatientMedicalHistory ADD FOREIGN KEY (PatientID) REFERENCES tblPatient(PatientID)");
            statement.executeUpdate("ALTER TABLE tblPatientMedicalHistory ADD FOREIGN KEY (DiagnosingDoctor) REFERENCES tblDoctor(DOCID)");
            statement.executeUpdate("ALTER TABLE tblDoctor ADD FOREIGN KEY (Specialisation) REFERENCES tblSpecialists(specialisationID)");
            statement.executeUpdate("ALTER TABLE tblDoctorAvailability ADD FOREIGN KEY (DOCID) REFERENCES tblDoctor(DOCID)");
            
            System.out.println("Tables successfully created");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}

