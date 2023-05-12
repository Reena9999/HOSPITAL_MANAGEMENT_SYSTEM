# General Overview

This is a scaled down medical appointment booking system.  

The system exists to allow doctors to easily post their availability, time and date, for patients to view. Based on the Doctors' availability, Patients can make booking. Registras help fecilitate the functions of both patients and doctors.

FUNCTIONALITES OF EACH USER:
**Registra**: the registra behaves as the administrator in the system.   
Therefore, registras can 
* add new doctors or Registras. But when a registra creates a new registra user, the database logs which existing registra created the new admin.
* view Patient medical history
* view booking, search through them and cancel bookings
* schedule appointment bookings for patients
* post a doctors availability details like date and time

**Patients**:
Patients can perform the following functions:
* view doctor availability
* make appointments with doctors
* view their medical history

**Doctor**:  
Doctors have the functionalities of
* posting their availability  
* view their bookings for the day
* view patients medical records

The system has three user types:  
1. DOCTORS  
2. PATIENTS  
3. REGISTRAS : acts as the administator for the entire system




# Database ERD
![ERD](https://user-images.githubusercontent.com/118504536/235449554-beda071c-a617-46c1-8241-cec8a26557ce.png)

TABLES IN THE DATABASE    
+------------------------------------+
| Tables_in_hospitalmanagementsystem |
+------------------------------------+
| tbldoctor                          |
| tbldoctoravailability              |
| tblpatient                         |
| tblregistras                       |
| tblscheduledbooking                |
| tblspecialists                     |
+------------------------------------+   
    
mysql> desc tblDoctor;    
+----------------+-------------+------+-----+---------+----------------+
| Field          | Type        | Null | Key | Default | Extra          |
+----------------+-------------+------+-----+---------+----------------+
| DOCID          | int         | NO   | PRI | NULL    | auto_increment |
| FirstName      | varchar(50) | YES  |     | NULL    |                |
| LastName       | varchar(50) | YES  |     | NULL    |                |
| Specialisation | int         | YES  | MUL | NULL    |                |
| PhNumber       | varchar(10) | YES  | UNI | NULL    |                |
| Email          | varchar(50) | YES  | UNI | NULL    |                |
| location       | varchar(50) | YES  |     | NULL    |                |
| password       | varchar(50) | YES  |     | NULL    |                |
+----------------+-------------+------+-----+---------+----------------+

mysql> desc tbldoctoravailability;  
+---------------------+------+------+-----+---------+-------+
| Field               | Type | Null | Key | Default | Extra |
+---------------------+------+------+-----+---------+-------+
| availableDate       | date | YES  |     | NULL    |       |
| DOCID               | int  | YES  | MUL | NULL    |       |
| availabileStartTime | time | YES  |     | NULL    |       |
| availabileEndTime   | time | YES  |     | NULL    |       |
| PatientToAttend     | int  | YES  |     | NULL    |       |
+---------------------+------+------+-----+---------+-------+
  
mysql> desc tblPatient;    
+-------------+-------------+------+-----+---------+----------------+
| Field       | Type        | Null | Key | Default | Extra          |
+-------------+-------------+------+-----+---------+----------------+
| PatientID   | int         | NO   | PRI | NULL    | auto_increment |
| FirstName   | varchar(50) | YES  |     | NULL    |                |
| LastName    | varchar(50) | YES  |     | NULL    |                |
| PhNumber    | varchar(10) | YES  | UNI | NULL    |                |
| Email       | varchar(50) | YES  | UNI | NULL    |                |
| Sex         | int         | YES  |     | NULL    |                |
| DateOfBirth | date        | YES  |     | NULL    |                |
| BloodGroup  | varchar(10) | YES  |     | NULL    |                |
| Password    | varchar(50) | YES  |     | NULL    |                |
+-------------+-------------+------+-----+---------+----------------+  

mysql> desc tblRegistras;  
+------------+-------------+------+-----+---------+----------------+
| Field      | Type        | Null | Key | Default | Extra          |
+------------+-------------+------+-----+---------+----------------+
| RegistraID | int         | NO   | PRI | NULL    | auto_increment |
| FirstName  | varchar(50) | YES  |     | NULL    |                |
| LastName   | varchar(50) | YES  |     | NULL    |                |
| PhNumber   | varchar(10) | YES  | UNI | NULL    |                |
| Password   | varchar(50) | NO   |     | NULL    |                |
+------------+-------------+------+-----+---------+----------------+
   
mysql> desc tblScheduledBooking;  
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| BookingID     | int          | NO   | PRI | NULL    | auto_increment |
| PatientID     | int          | YES  | MUL | NULL    |                |
| DOCID         | int          | YES  | MUL | NULL    |                |
| ScheduledDate | date         | YES  |     | NULL    |                |
| ScheduledTime | time         | YES  |     | NULL    |                |
| Registra      | int          | YES  | MUL | NULL    |                |
| Status        | int          | YES  |     | NULL    |                |
| Symptoms      | varchar(200) | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+ 

mysql> desc tblspecialists;  
+------------------+-------------+------+-----+---------+----------------+  
| Field            | Type        | Null | Key | Default | Extra          |  
+------------------+-------------+------+-----+---------+----------------+  
| specialisationID | int         | NO   | PRI | NULL    | auto_increment |  
| specialisation   | varchar(50) | YES  |     | NULL    |                |  
+------------------+-------------+------+-----+---------+----------------+    

