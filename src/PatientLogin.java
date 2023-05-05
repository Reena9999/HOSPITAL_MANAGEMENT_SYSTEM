import javax.swing.*;

public class PatientLogin {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField firstNameField, lastNameField, idField;
    private JButton bookAppointmentButton, searchButton;
    private JComboBox<String> dateComboBox, timeComboBox, doctorComboBox;

    public PatientLogin() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.add(firstNameLabel);
        firstNamePanel.add(firstNameField);
        mainPanel.add(firstNamePanel);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField(20);
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(lastNameField);
        mainPanel.add(lastNamePanel);

        JLabel idLabel = new JLabel("Patient ID:");
        idField = new JTextField(10);
        idField.setEditable(false);
        JPanel idPanel = new JPanel();
        idPanel.add(idLabel);
        idPanel.add(idField);
        mainPanel.add(idPanel);

        bookAppointmentButton = new JButton("Schedule Appointment");
        bookAppointmentButton.addActionListener(e -> bookAppointment());
        mainPanel.add(bookAppointmentButton);

        JLabel schedulingLabel = new JLabel("Schedule Appointment:");
        JPanel schedulingPanel = new JPanel();
        schedulingPanel.add(schedulingLabel);

        String[] dates = {"May 10", "May 11", "May 12"};
        dateComboBox = new JComboBox<>(dates);
        schedulingPanel.add(dateComboBox);

        String[] times = {"10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM"};
        timeComboBox = new JComboBox<>(times);
        schedulingPanel.add(timeComboBox);

        String[] doctors = {"Dr. Smith", "Dr. Johnson", "Dr. Lee"};
        doctorComboBox = new JComboBox<>(doctors);
        schedulingPanel.add(doctorComboBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> search());
        schedulingPanel.add(searchButton);

        mainPanel.add(schedulingPanel);

        frame = new JFrame("Patient Booking System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void bookAppointment() {
        int result = JOptionPane.showConfirmDialog(frame, "Do you want to book an appointment?", "Confirm Appointment", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String date = (String) dateComboBox.getSelectedItem();
            String time = (String) timeComboBox.getSelectedItem();
            String doctor = (String) doctorComboBox.getSelectedItem();


        }
    }

    private void search() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PatientLogin());
    }
}
