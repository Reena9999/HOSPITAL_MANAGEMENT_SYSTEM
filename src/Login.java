import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    public static void main(String args[]) {
        JFrame frame1 = new JFrame("Log In");
        frame1.setLocation(900, 300);

        frame1.setSize(300, 300);
        frame1.setLayout(null);

        JLabel userNameLabel = new JLabel("Unique ID");
        userNameLabel.setBounds(60, 40, 150, 20);

        JTextField u_name = new JTextField();
        u_name.setBounds(60, 60, 150, 20);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(60, 90, 150, 20);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(60, 110, 150, 20);

        JButton login = new JButton("Login");
        login.setBounds(110, 150, 80, 20);

        frame1.add(userNameLabel);
        frame1.add(u_name);

        frame1.add(passwordLabel);
        frame1.add(passwordField);


        frame1.add(login);

        frame1.setVisible(true);
        actionlogin(frame1, login, u_name, passwordField);
    }

    public static void actionlogin(JFrame f, JButton done, JTextField u_name, JTextField passwordField) {
        done.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText();
                String name = u_name.getText();
                
                try {
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem","root", "94807279");
            
                    PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select RegistraID from tblRegistras where FirstName=?");
                    st.setString(1, name);

                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        // System.out.println(rs.next());
                        PreparedStatement st2 = (PreparedStatement) connection.prepareStatement("Select FirstName, Password from tblRegistras where RegistraID=? and password=?");
                        st2.setString(1, name);
                        st2.setString(2, password);
                        ResultSet rs2 = st.executeQuery();
                        if(rs2.next()){
                            WelcomePanel ch = new WelcomePanel();
                            ch.setVisible(true);
                            f.dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                            u_name.requestFocus();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Cannot Find Username");
                        u_name.requestFocus();
                    }                        
                     
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
    }
}