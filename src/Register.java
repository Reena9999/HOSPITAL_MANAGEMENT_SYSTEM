import javax.swing.*;
import java.sql.*;
import java.util.Calendar;

public class Register extends JFrame {

    public Register() {
        JFrame registerFrame = new JFrame();
        registerFrame.setLocation(900, 300);
        registerFrame.setSize(450, 450);
        registerFrame.setTitle("Registration");
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLocation(600, 350);
        registerFrame.setLayout(null);

        // Create name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(60, 40, 200, 20);

        JTextField firstNameTextField = new JTextField(20);
        firstNameTextField.setBounds(150, 40, 200, 20);

        // Create name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(60, 65, 200, 20);

        JTextField lastNameTextField = new JTextField(20);
        lastNameTextField.setBounds(150, 65, 200, 20);


        // Create sex label and radio buttons
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setBounds(60, 100, 200, 20);

        JRadioButton maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(150, 100, 65, 20);

        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(250, 100, 200, 20);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleRadioButton);
        sexGroup.add(femaleRadioButton);


        // Create age label and text field
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(60, 130, 200, 20);

        JSpinner DOB = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(DOB, "dd/MM/yyyy");
        DOB.setEditor(editor);
        DOB.setValue(Calendar.getInstance().getTime());
        DOB.setBounds(150, 130, 200, 20);


        // Create blood group label and drop-down box , rh group drop-down box
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupLabel.setBounds(60, 160, 200, 20);

        String[] bloodGroups = { "A", "B", "AB", "O" };
        JComboBox<String> bloodGroupComboBox = new JComboBox<>(bloodGroups);
        bloodGroupComboBox.setBounds(150, 160, 50, 20);

        String[] RhGroups = { "+", "-" };
        JComboBox<String> RhGroupComboBox = new JComboBox<>(RhGroups);
        RhGroupComboBox.setBounds(200, 160, 50, 20);


        // Create phone number label and text field
        JLabel phNumberLabel = new JLabel("Ph Number:");
        phNumberLabel.setBounds(60, 200, 200, 20);

        JTextField phNumberTextField = new JTextField(20);
        phNumberTextField.setBounds(150, 200, 200, 20);


        // Create email label and text field
        JLabel emailLabel = new JLabel("eMail:");
        emailLabel.setBounds(60, 240, 200, 20);

        JTextField emailTextField = new JTextField(20);
        emailTextField.setBounds(150, 240, 200, 20);


        // Create password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 270, 200, 20);

        JTextField passwordTextField = new JTextField(20);
        passwordTextField.setBounds(150, 270, 200, 20);


        // Create register button
        JButton registerButton = new JButton("Submit");
        registerButton.setBounds(250, 300, 100, 20);


        // Adding the components to the Frame
        registerFrame.add(firstNameLabel);
        registerFrame.add(firstNameTextField);

        registerFrame.add(lastNameLabel);
        registerFrame.add(lastNameTextField);

        registerFrame.add(sexLabel);
        registerFrame.add(maleRadioButton);
        registerFrame.add(femaleRadioButton);

        registerFrame.add(ageLabel);
        registerFrame.add(DOB);

        registerFrame.add(bloodGroupLabel);
        registerFrame.add(bloodGroupComboBox);
        registerFrame.add(RhGroupComboBox);

        registerFrame.add(phNumberLabel);
        registerFrame.add(phNumberTextField);

        registerFrame.add(emailLabel);
        registerFrame.add(emailTextField);

        registerFrame.add(passwordLabel);
        registerFrame.add(passwordTextField);

        registerFrame.add(registerButton);

        registerFrame.setVisible(true);
    }

    public static void actionRegister(JFrame frame, JPanel registerPanel) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

            PreparedStatement st = (PreparedStatement) connection.prepareStatement("insert into tblPatient(FirstName,LastName,PhNumber,Email,Sex,BloodGroup,Password) values(?,?) RegistraID from tblRegistras where FirstName=?");
            // st.setString(1, userNameTextField.getText());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                // System.out.println(rs.next());
                PreparedStatement st2 = (PreparedStatement) connection.prepareStatement(
                        "Select FirstName, Password from tblRegistras where RegistraID=? and password=?");
                // st2.setString(1, userNameTextField.getText());
                // st2.setString(2, passwordTextField.getText());
                ResultSet rs2 = st.executeQuery();
                if (rs2.next()) {
                    Home home = new Home();
                    home.setVisible(true);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot Find Username");
            }

        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static void main(String args[])throws Exception{
        new Register();
    }
}