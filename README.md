# General Overview

This is a scaled down medical appointment booking system.

The system has three user types:  
1. DOCTORS  
2. PATIENTS  
3. REGISTRAS  

# Database ERD
![ERD](https://user-images.githubusercontent.com/118504536/235449554-beda071c-a617-46c1-8241-cec8a26557ce.png)

TABLES IN THE DATABASE    
+------------------------------------+    
| Tables_in_hospitalmanagementsystem |    
+------------------------------------+    
| tbldoctor                          |    
| tbldoctoravailability              |    
| tblpatient                         |    
| tblpatientmedicalhistory           |    
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
| Specialization | int         | YES  | MUL | NULL    |                |  
| PhNumber       | varchar(10) | YES  |     | NULL    |                |  
| Email          | varchar(50) | YES  |     | NULL    |                |  
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
| PhNumber    | varchar(10) | YES  |     | NULL    |                |  
| Email       | varchar(10) | YES  |     | NULL    |                |  
| Sex         | int         | YES  |     | NULL    |                |  
| DateOfBirth | date        | YES  |     | NULL    |                |  
| BloodGroup  | varchar(10) | YES  |     | NULL    |                |  
| Password    | varchar(50) | YES  |     | NULL    |                |  
+-------------+-------------+------+-----+---------+----------------+  

mysql> desc tblPatientMedicalHistory;  
+------------------+-------------+------+-----+---------+-------+  
| Field            | Type        | Null | Key | Default | Extra |  
+------------------+-------------+------+-----+---------+-------+  
| Date             | date        | YES  |     | NULL    |       |  
| PatientID        | int         | YES  | MUL | NULL    |       |  
| DiagnosingDoctor | int         | YES  | MUL | NULL    |       |  
| Symptoms         | varchar(50) | YES  |     | NULL    |       |  
+------------------+-------------+------+-----+---------+-------+  

mysql> desc tblRegistras;  
+------------+-------------+------+-----+---------+----------------+  
| Field      | Type        | Null | Key | Default | Extra          |  
+------------+-------------+------+-----+---------+----------------+  
| RegistraID | int         | NO   | PRI | NULL    | auto_increment |  
| FirstName  | varchar(50) | YES  |     | NULL    |                |  
| LastName   | varchar(50) | YES  |     | NULL    |                |  
| PhNumber   | varchar(10) | YES  |     | NULL    |                |  
| Password   | varchar(50) | NO   |     | NULL    |                |  
+------------+-------------+------+-----+---------+----------------+  
   
mysql> desc tblScheduledBooking;  
+---------------+------+------+-----+---------+----------------+  
| Field         | Type | Null | Key | Default | Extra          |  
+---------------+------+------+-----+---------+----------------+  
| BookingID     | int  | NO   | PRI | NULL    | auto_increment |  
| PatientID     | int  | YES  | MUL | NULL    |                |  
| DOCID         | int  | YES  | MUL | NULL    |                |  
| ScheduledDate | date | YES  |     | NULL    |                |  
| ScheduledTime | time | YES  |     | NULL    |                |  
| Registra      | int  | YES  | MUL | NULL    |                |  
+---------------+------+------+-----+---------+----------------+  

mysql> desc tblspecialists;  
+------------------+-------------+------+-----+---------+----------------+  
| Field            | Type        | Null | Key | Default | Extra          |  
+------------------+-------------+------+-----+---------+----------------+  
| specialisationID | int         | NO   | PRI | NULL    | auto_increment |  
| specialisation   | varchar(50) | YES  |     | NULL    |                |  
+------------------+-------------+------+-----+---------+----------------+    

