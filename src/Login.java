import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame
{
    public static void main(String args[])
    {
        JFrame f = new JFrame("BanK Login");
        JTextField u_name = new JTextField("ENTER NAME ON CARD");
        u_name.setBounds(60,50,150,20);
        JPasswordField password = new JPasswordField("ENTER PIN");
        password.setBounds(60,90,150,20);
        JButton done = new JButton("Login");
        done.setBounds(110,150,80,20);
        JPanel p = new JPanel();
        f.add(done);
        f.add(u_name);
        f.add(password);
        f.setSize(300,300);
        f.setLayout(null);
        f.setVisible(true);
        actionlogin(f, done, u_name, password);
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
                    swings2 ch = new swings2();
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

