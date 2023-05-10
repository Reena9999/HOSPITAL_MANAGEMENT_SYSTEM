
import java.util.Date;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RegistraHome extends JFrame {

    ResultSet resultSet;
    JLabel nameLabel;
    String fullName;
    static String uniqueID;
    static String specialisation;

    public RegistraHome(String uID) {
        uniqueID = uID;
        JFrame homeFrame = new JFrame();
        homeFrame.setTitle("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(10000, 10000);
        homeFrame.setLayout(null);

        // Create label that displays Registras Full Name
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("select FirstName,LastName from tblRegistras where RegistraID=?");
            st.setString(1, uniqueID);

            resultSet = st.executeQuery();
            if (resultSet.next()) {
                fullName = resultSet.getString(1) + "  " + resultSet.getString(2);
            }

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.getMessage();
        }

        nameLabel = new JLabel("Name: " + fullName);
        nameLabel.setBounds(70, 70, 300, 80);

        // Create Registra ID Label
        JLabel uniqueLabel = new JLabel("Registra ID:  " + uniqueID);
        uniqueLabel.setBounds(70, 120, 100, 80);

        // Create specialization label and drop down
        JLabel specializationLabel = new JLabel("Specialization");
        specializationLabel.setBounds(70, 200, 80, 20);

        String[] specialization = new String[20];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("select specialisation from tblspecialists");
            resultSet = st.executeQuery();
            if (resultSet.next()) {
                int row = 0;
                while (resultSet.next()) {
                    specialization[row] = resultSet.getString(1);
                    row++;
                }
            }

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.getMessage();
        }
        JComboBox<String> specializationComboBox = new JComboBox<>(specialization);
        specializationComboBox.setBounds(200, 200, 200, 20);

        // Create Date label and datepicker
        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(500, 200, 30, 20);

        SpinnerDateModel datePicker = new SpinnerDateModel(new Date(), null, null, getDefaultCloseOperation());
        JSpinner spinner = new JSpinner(datePicker);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, dateFormat.toPattern());
        DateFormatter dateFormatter = (DateFormatter) dateEditor.getTextField().getFormatter();
        dateFormatter.setAllowsInvalid(false);
        spinner.setEditor(dateEditor);
        spinner.setBounds(600, 200, 200, 20);

        // create search button
        JButton searchButton = new JButton("Search");
        searchButton.setBounds(850, 200, 100, 20);

        JTable table = new JTable();
        table.setBounds(70, 280, 1000, 1000);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setVisible(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] columnNames = {"Name", "Date", "Available From", "Available To"};
                String[][] originalData = getData(homeFrame, searchButton, specializationComboBox, datePicker);
                String[][] tableData;
                int limit = Integer.parseInt(originalData[0][0]);

                if (limit > 0) {
                    tableData = new String[limit - 1][4];
                    for (int i = 0; i < limit - 1; i++) {
                        for (int j = 0; j < 4; j++) {
                            tableData[i][j] = originalData[i + 1][j];
                        }
                    }
                } else {
                    tableData = new String[0][0];
                }
                Object[][] data = tableData;
                DefaultTableModel model = new DefaultTableModel(data, columnNames);
                table.setModel(model);

                // Add a ListSelectionListener to the table to handle row clicks
                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getValueIsAdjusting()) {
                            return;
                        }
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow >= 0) {
                            scheduleAppointment((String) table.getValueAt(selectedRow, 0),
                                    (String) table.getValueAt(selectedRow, 1),
                                    (String) table.getValueAt(selectedRow, 2),
                                    (String) table.getValueAt(selectedRow, 3));
                        }
                    }
                });
                table.setVisible(true);
            }
        });

        // Add components to the homeFrame
        homeFrame.add(nameLabel);
        homeFrame.add(uniqueLabel);

        homeFrame.add(specializationLabel);
        homeFrame.add(specializationComboBox);

        homeFrame.add(dateLabel);
        homeFrame.add(spinner);

        homeFrame.add(searchButton);
        homeFrame.add(table);
        homeFrame.add(scrollPane);
        homeFrame.setVisible(true);
    }

    public static String[][] getData(JFrame homeFrame, JButton searchButton, JComboBox<String> specializationComboBox,
            SpinnerDateModel datePicker) {

        String[][] retreivedData = new String[100][4];

        Date selectedDate = (Date) datePicker.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(selectedDate);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

            PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                    "Select doc.FirstName,doc.LastName,schedule.availableDate,schedule.availabileStartTime,schedule.availabileEndTime from tblDoctor as doc, tbldoctoravailability as schedule where schedule.availableDate=? and schedule.DOCID in (Select DOCID from tblDoctor where specialisation in (Select specialisationID from tblspecialists where specialisation=?) and doc.DOCID=schedule.DOCID)");

            st.setString(1, dateString);
            specialisation = (String) specializationComboBox.getSelectedItem();
            st.setString(2, specialisation);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int row = 1;
                do {
                    retreivedData[row][0] = rs.getString(1) + " " + rs.getString(2);
                    retreivedData[row][1] = rs.getString(3);
                    retreivedData[row][2] = rs.getString(4);
                    retreivedData[row][3] = rs.getString(5);
                    row++;
                } while (rs.next());
                retreivedData[0][0] = Integer.toString(row);

            } else {
                JOptionPane.showMessageDialog(null, "No available appoint slots!");
                retreivedData[0][0] = Integer.toString(0);
            }

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        return retreivedData;
    }

    public static void scheduleAppointment(String doctorName, String appointmentDate, String startTime,
            String endTime) {

        JFrame scheduleAppointmentFrame = new JFrame();
        scheduleAppointmentFrame.setTitle("Schedule appointment");
        scheduleAppointmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        scheduleAppointmentFrame.setLocation(900, 300);
        scheduleAppointmentFrame.setSize(350, 350);
        scheduleAppointmentFrame.setLayout(null);

        // Create an input field to read which patient to book appointments for
        JLabel patientLabel = new JLabel("<html>Booking For<br>Patient ID:&nbsp;</html>");
        patientLabel.setBounds(30, 40, 100, 50);

        JTextField patientTextField = new JTextField();
        patientTextField.setBounds(30, 90, 200, 20);

        JLabel details = new JLabel("<html>Doctor:&nbsp;&nbsp;" + doctorName + "<br>Appointment Date: &nbsp;&nbsp;" + appointmentDate
                + "<br>Time:&nbsp;&nbsp;" + startTime + "</html>");
        details.setBounds(30, 120, 200, 100);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(200, 200, 100, 20);

        scheduleAppointmentFrame.add(patientLabel);
        scheduleAppointmentFrame.add(patientTextField);
        scheduleAppointmentFrame.add(details);
        scheduleAppointmentFrame.add(confirm);

        scheduleAppointmentFrame.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patientID = patientTextField.getText();
                System.out.println(patientID);
                if (patientID.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Patient ID");
                    patientTextField.requestFocus();
                } else {
                    int result = JOptionPane.showConfirmDialog(scheduleAppointmentFrame,
                            "<html><strong>Appointment Details</strong><br><br>Patient ID: " + patientID + "<br>"
                            + details.getText(),
                            "Confirm booking", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        scheduleAppointmentFrame.dispose();
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection connection = (Connection) DriverManager
                                    .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root",
                                            "94807279");
                            PreparedStatement st1 = (PreparedStatement) connection.prepareStatement(
                                    "select doc.DOCID,sc.PatientToAttend from tblDoctor as doc,tbldoctorAvailability as sc where doc.FirstName=? and doc.LastName=? and doc.Specialisation=(Select specialisationID from tblSpecialists where specialisation=?) and doc.DOCID=sc.DOCID and sc.availableDate=? and (sc.availabileStartTime-availabileEndTime)>(15*60)");
                            String[] arr = doctorName.split(" ");
                            st1.setString(1, arr[0]);
                            st1.setString(2, arr[1]);
                            st1.setString(3, specialisation);
                            st1.setString(4, appointmentDate);
                            String DOCID = "";
                            int PatientToAttend = 0;
                            ResultSet rs = st1.executeQuery();
                            if (rs.next()) {
                                DOCID = rs.getString(1);
                                PatientToAttend = Integer.parseInt(rs.getString(2));
                            }

                            PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                                    "insert into tblscheduledbooking(PatientID,DOCID,ScheduledDate,ScheduledTime,Registra) values(?,?,?,?,?)");
                            st.setString(1, patientID);
                            st.setString(2, DOCID);
                            st.setString(3, appointmentDate);
                            st.setString(4, startTime);
                            st.setString(5, uniqueID);
                            int rowsAffected = st.executeUpdate();
                            if (rowsAffected > 0) {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                                LocalTime stime = LocalTime.parse(startTime, formatter);
                                LocalTime etime = LocalTime.parse(endTime, formatter);

                                Duration duration = Duration.between(stime, etime);
                                long hours = duration.toHours(); // Get the difference in hours
                                long minutes = duration.toMinutes() % 60; // Get the difference in minutes

                                if (hours < 0) {
                                    hours *= -1;
                                }
                                if (minutes < 0) {
                                    minutes *= -1;
                                }

                                long timePerPatient = ((hours * 60) + minutes) / PatientToAttend;
                                stime = stime.plusMinutes(timePerPatient);
                                PatientToAttend -= 1;
                                PreparedStatement st3 = (PreparedStatement) connection.prepareStatement(
                                        "update tbldoctoravailability set availabileStartTime=?, PatientToAttend=? where DOCID=? and availableDate=?");
                                st3.setString(1, (String) startTime);
                                st3.setString(2, Integer.toString(PatientToAttend));
                                st3.setString(3, DOCID);
                                st3.setString(4, (String) appointmentDate);
                                rowsAffected = st.executeUpdate();
                                if (rowsAffected > 0) {
                                    JOptionPane.showMessageDialog(scheduleAppointmentFrame, "Booking Confirmed");
                                }

                            }

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        });

    }

}
