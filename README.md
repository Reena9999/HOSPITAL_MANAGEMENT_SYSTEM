# General Overview

This is a scaled down medical appointment booking system.  

The system exists to allow doctors to easily post their availability, time and date, for patients to view. Based on the Doctors' availability, Patients can make booking. Registras help fecilitate the functions of both patients and doctors.

The system has three user types:    
1. DOCTORS    
2. PATIENTS    
3. REGISTRAS : acts as the administator for the entire system    

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

## Database ERD
![ERD](https://user-images.githubusercontent.com/118504536/235449554-beda071c-a617-46c1-8241-cec8a26557ce.png)

## CODE
To access the code follow the path in the repository src>hospitalmanagementsystem
