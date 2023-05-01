import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame
{
    public static void main(String args[])
    {
        JFrame frame1 = new JFrame("Log In");
        frame1.setLocation(900,300);
        
        frame1.setSize(300,300);
        frame1.setLayout(null);

        JLabel userNameLabel=new JLabel("Unique ID");
        userNameLabel.setBounds(60,40,150,20);

        JTextField u_name = new JTextField();
        u_name.setBounds(60,60,150,20);

        JLabel passwordLabel=new JLabel("Password");
        passwordLabel.setBounds(60,90,150,20);

        JPasswordField password = new JPasswordField();
        password.setBounds(60,110,150,20);

        JButton login = new JButton("Login");
        login.setBounds(110,150,80,20);
        
        frame1.add(userNameLabel);
        frame1.add(u_name);

        frame1.add(password);
        frame1.add(passwordLabel);
        
        frame1.add(login);
       
        frame1.setVisible(true);
        actionlogin(frame1, login, u_name, password);
    }
    
    public static void actionlogin(JFrame f,JButton done,JTextField u_name,JTextField password)
    {
        done.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String name = u_name.getText();
                String pw = password.getText();
                if (name.equals("REENA") && pw.equals("6601"))
                {
                    WelcomePanel ch = new WelcomePanel();
                    ch.setVisible(true);
                    f.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
                    u_name.requestFocus();
                }
            }
        });
    }
}



