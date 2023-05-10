import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Enumeration;

public class Login extends JFrame {
    public static void main(String args[]) {
        JFrame loginFrame = new JFrame("Log In");
        loginFrame.setLocation(900, 300);
        loginFrame.setSize(350, 350);
        loginFrame.setLayout(null);
      
        // Create User Name label and TextField
        JLabel userNameLabel = new JLabel("Unique ID");
        userNameLabel.setBounds(60, 40, 200, 20);

        JTextField userNameTextField = new JTextField();
        userNameTextField.setBounds(60, 70, 225, 20);

        // Create User type label and radio button
        JLabel userTypeLabel = new JLabel("User Type");
        userTypeLabel.setBounds(60, 110, 170, 20);

        JRadioButton patientRadioButton = new JRadioButton("Patient");
        patientRadioButton.setName("patientRadioButton");
        patientRadioButton.setBounds(60, 135, 65, 20);

        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        doctorRadioButton.setName("doctorRadioButton");
        doctorRadioButton.setBounds(140, 135, 65, 20);

        JRadioButton adminRadioButton = new JRadioButton("Registra");
        adminRadioButton.setName("adminRadioButton");
        adminRadioButton.setBounds(210, 135, 200, 20);

        ButtonGroup userType = new ButtonGroup();
        userType.add(patientRadioButton);
        userType.add(doctorRadioButton);
        userType.add(adminRadioButton);

        // Create password label and textField
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 170, 170, 20);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(60, 195, 225, 20);

        // Create login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(110, 230, 80, 20);

        // Create register button
        JButton registerButton = new JButton("Sign Up");
        registerButton.setBounds(200, 230, 80, 20);

        // Adding components to the Frame
        loginFrame.add(userNameLabel);
        loginFrame.add(userNameTextField);

        loginFrame.add(userTypeLabel);
        loginFrame.add(patientRadioButton);
        loginFrame.add(doctorRadioButton);
        loginFrame.add(adminRadioButton);

        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);

        loginFrame.add(loginButton);
        loginFrame.add(registerButton);

        loginFrame.setVisible(true);

        // call function to listen for button click
        actionlogin(loginFrame, loginButton, userNameTextField, userType, passwordField);
        actionSignUp(loginFrame, registerButton);
    }

    public static void actionSignUp(JFrame loginFrame, JButton register) {
        // add a ActionListener to invoke a function on registerButton click
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open the register window
                patientRegistertion signUp = new patientRegistertion();
                signUp.setVisible(true);

                // close the login window
                loginFrame.setVisible(false);
            }
        });
    }

    public static void actionlogin(JFrame loginFrame, JButton loginButton, JTextField userNameTextField,ButtonGroup userType, JTextField passwordField) {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String name = userNameTextField.getText();

                JRadioButton selectedRadioButton = null;
                Enumeration<AbstractButton> buttons = userType.getElements();
                while (buttons.hasMoreElements()) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        selectedRadioButton = (JRadioButton) button;
                        break;
                    }
                }
                String uType = selectedRadioButton.getText();

                try {
                    Connection connection = (Connection) DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");
                    String sqlCommand = "";

                    if (uType.equalsIgnoreCase("Patient")) {
                        sqlCommand = "Select PatientID from tblPatient where FirstName=?";
                    } else if (uType.equalsIgnoreCase("Doctor")) {
                        sqlCommand = "Select DOCID from tblDoctor where FirstName=?";
                    } else if (uType.equalsIgnoreCase("Registra")) {
                        sqlCommand = "Select RegistraID from tblRegistras where FirstName=?";
                    }
                    PreparedStatement st = (PreparedStatement) connection.prepareStatement(sqlCommand);
                    st.setString(1, name);

                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        String uniqueID = "";
                        String sqlCommand2 = "";
                        if (uType.equalsIgnoreCase("Patient")) {
                            uniqueID=rs.getString("PatientID");
                            sqlCommand2 = "Select FirstName, Password from tblPatient where PatientID=? and password=?";
                        } else if (uType.equalsIgnoreCase("Doctor")) {
                            uniqueID=rs.getString("DOCID");
                            sqlCommand2 = "Select FirstName, Password from tblDoctor where DOCID=? and password=?";
                        } else if (uType.equalsIgnoreCase("Registra")) {
                            uniqueID=rs.getString("RegistraID");
                            sqlCommand2 = "Select FirstName, Password from tblRegistras where RegistraID=? and password=?";
                        }

                        PreparedStatement st2 = (PreparedStatement) connection.prepareStatement(sqlCommand2);
                        st2.setString(1, uniqueID);
                        st2.setString(2, password);

                        ResultSet rs2 = st.executeQuery();
                        if (rs2.next()) {
                            loginFrame.setVisible(false);                            

                            if (uType.equalsIgnoreCase("Patient")) {
                                PatientHome Object=new PatientHome(uniqueID);
                            } else if (uType.equalsIgnoreCase("Doctor")) {
                                PatientHome Object=new PatientHome(uniqueID);
                            } else if (uType.equalsIgnoreCase("Registra")) {
                                RegistraHome Object = new RegistraHome(uniqueID);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                            userNameTextField.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot Find Username. Try signing up");
                        userNameTextField.requestFocus();
                    }

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
    }
}