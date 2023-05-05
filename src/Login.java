import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    public static void main(String args[]) {
        JFrame loginFrame = new JFrame("Log In");
        loginFrame.setLocation(900, 300);
        loginFrame.setSize(300, 300);
        loginFrame.setLayout(null);


        //Create User Name label and TextField
        JLabel userNameLabel = new JLabel("Unique ID");
        userNameLabel.setBounds(60, 40, 200, 20);

        JTextField userNameTextField = new JTextField();
        userNameTextField.setBounds(60, 60, 170, 20);


        //Create password label and textField
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 90, 170, 20);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(60, 110, 170, 20);


        //Create login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(60, 150, 80, 20);


        //Create register button
        JButton registerButton=new JButton("Sign Up");
        registerButton.setBounds(150, 150, 80, 20);


        //Adding components to the Frame
        loginFrame.add(userNameLabel);
        loginFrame.add(userNameTextField);

        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);

        loginFrame.add(loginButton);
        loginFrame.add(registerButton);

        loginFrame.setVisible(true);


        //call function to listen for button click
        actionlogin(loginFrame, loginButton, userNameTextField, passwordField);
        actionSignUp(loginFrame,registerButton);
    }


    public static void actionSignUp(JFrame frame,JButton register){
        //add a ActionListener to invoke a function on registerButton click
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //open the register window
                Register signUp=new Register();
                signUp.setVisible(true);

                //close the login window
                frame.dispose();
            }
        });
    }


    public static void actionlogin(JFrame loginFrame, JButton loginButton, JTextField userNameTextField, JTextField passwordField) {
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String name = userNameTextField.getText();
                
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem","root", "94807279");
            
                    PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select RegistraID from tblRegistras where FirstName=?");
                    st.setString(1, name);

                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        String uniqueID = rs.getString("RegistraID");

                        PreparedStatement st2 = (PreparedStatement) connection.prepareStatement("Select FirstName, Password from tblRegistras where RegistraID=? and password=?");
                        st2.setString(1, uniqueID);
                        st2.setString(2, password);
                        
                        ResultSet rs2 = st.executeQuery();
                        if(rs2.next()){
                            Home homeObject = new Home();
                            homeObject.setVisible(true);
                            loginFrame.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                            userNameTextField.requestFocus();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Cannot Find Username");
                        userNameTextField.requestFocus();
                    }                        
                     
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
    }
}