import javax.swing.*;
import java.sql.*;
import java.util.Calendar;

public class Home extends JFrame{
    public Home() {
        JFrame homeFrame = new JFrame();
        homeFrame.setTitle("Home");
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setSize(10000, 10000);

        //Create specialization label and drop down
        JLabel specializationLabel = new JLabel("Specialization");
        specializationLabel.setBounds(70, 200, 60, 20);

        String[] specialization = new String[20];
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

            PreparedStatement st = (PreparedStatement) connection.prepareStatement("select Specialization from tblSpecialists");
            ResultSet resultSet = st.executeQuery();
            int count = 0;
            specialization = new String[20];
            while (resultSet.next()) {
                specialization[count] = resultSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
        JComboBox<String> specializationComboBox = new JComboBox<>(specialization);
        specializationComboBox.setBounds(100, 200, 60, 20);


        JLabel dateLabel= new JLabel("Date");
        dateLabel.setBounds(130, 200, 60, 20);

        JSpinner datePicker = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(datePicker, "dd/MM/yyyy");
        datePicker.setEditor(editor);
        datePicker.setValue(Calendar.getInstance().getTime());
        datePicker.setBounds(150, 200, 60, 20);


//        JButton searchButton = new JButton("Search");
//        searchButton.setBounds(200, 200, 60, 20);

        //Add components to the homeFrame
        homeFrame.add(specializationLabel);
        homeFrame.add(specializationComboBox);

        homeFrame.add(dateLabel);
        homeFrame.add(datePicker);

//        homeFrame.add(searchButton);
//
        homeFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Home();
    }
}

