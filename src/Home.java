import javax.swing.*;
import java.sql.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Home extends JFrame {
    private static JTable scheduleTable;

    public Home() {
        JFrame homeFrame = new JFrame();
        homeFrame.setTitle("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(10000, 10000);
        homeFrame.setLayout(null);

        // Create specialization label and drop down
        JLabel specializationLabel = new JLabel("Specialization");
        specializationLabel.setBounds(70, 200, 80, 20);

        String[] specialization = new String[20];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

            PreparedStatement st = (PreparedStatement) connection
                    .prepareStatement("select Specialization from specialist");
            ResultSet resultSet = st.executeQuery();
            int count = 0;
            specialization = new String[20];
            while (resultSet.next()) {
                specialization[count] = resultSet.getString(1);
                count++;
            }
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        JComboBox<String> specializationComboBox = new JComboBox<>(specialization);
        specializationComboBox.setBounds(200, 200, 200, 20);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(500, 200, 30, 20);

        JSpinner datePicker = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(datePicker, "yyyy-MM-dd");
        datePicker.setEditor(editor);
        datePicker.setBounds(600, 200, 200, 20);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(850, 200, 100, 20);

        // Add components to the homeFrame
        homeFrame.add(specializationLabel);
        homeFrame.add(specializationComboBox);

        homeFrame.add(dateLabel);
        homeFrame.add(datePicker);

        homeFrame.add(searchButton);

        homeFrame.setVisible(true);

        actionSearch(homeFrame, searchButton, specializationComboBox, datePicker);
    }

    public static void actionSearch(JFrame homeFrame, JButton searchButton, JComboBox<String> specializationComboBox,
            JSpinner datePicker) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

            PreparedStatement st = (PreparedStatement) connection.prepareStatement(
                    "Select doc.FirstName,doc.LastName,schedule.availableDate,schedule.availabileStartTime,schedule.availabileEndTime from tblDoctor as doc, tbldoctoravailability as schedule where schedule.availableDate=? and schedule.DOCID in (Select DOCID from tblDoctor where Specialization in (Select SpecialistID from specialist where Specialization=?) and doc.DOCID=schedule.DOCID)");
            String date =(String) datePicker.getValue();


            st.setString(1, date);
            st.setString(2, (String) specializationComboBox.getSelectedItem());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ResultSet resultSet = st.executeQuery();
                int column = 0;
                int row = 0;
                String[][] retreivedData = new String[20][5];
                while (resultSet.next()) {
                    retreivedData[row][column] = resultSet.getString(1);
                    if (column == 4) {
                        column = 0;
                        row++;
                    } else {
                        column++;
                    }
                }

                for (int j = 0; j < retreivedData.length; j++) {
                    for (int i = 0; i < retreivedData[0].length; i++) {
                        System.out.print(retreivedData[j][i] + " ");
                    }
                    System.out.println(" ");
                }

                // Create a table model with some data
                DefaultTableModel model = new DefaultTableModel(
                        new Object[][] { retreivedData },
                        new String[] { "First Name", "Last Name", "Date", "Available From", "Available To" });

                // Create a JTable with the model
                scheduleTable = new JTable(model);
                scheduleTable.setRowSelectionAllowed(true);

                // Add a ListSelectionListener to the table to handle row clicks
                scheduleTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getValueIsAdjusting()) {
                            return;
                        }
                        int selectedRow = scheduleTable.getSelectedRow();
                        if (selectedRow >= 0) {
                            // Do something with the selected row
                            String firstName = (String) scheduleTable.getValueAt(selectedRow, 0);
                            String lastName = (String) scheduleTable.getValueAt(selectedRow, 1);
                            int age = (int) scheduleTable.getValueAt(selectedRow, 2);
                            System.out.println("Selected row: " + firstName + " " + lastName + ", " + age);
                        }
                    }
                });

                // Add the table to a JScrollPane and add the scroll pane to the frame
                JScrollPane scrollPane = new JScrollPane(scheduleTable);
                homeFrame.add(scrollPane);
            }

            // ResultSet rs2 = st.executeQuery();
            // if (rs2.next()) {
            // Home home = new Home();
            // home.setVisible(true);
            // homeFrame.dispose();
            // } else {
            // JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
            // }
            // } else {
            // JOptionPane.showMessageDialog(null, "Cannot Find Username");
            // }

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
