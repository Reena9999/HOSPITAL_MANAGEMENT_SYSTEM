import javax.swing.*;


public class WelcomePanel extends JFrame {
    public static void main(String[] args) {
        WelcomePanel panel=new WelcomePanel();
    }

    JLabel welcome = new JLabel("Welcome REENA \n :)");
    JPanel panel = new JPanel();

    WelcomePanel() {
        super("Welcome");
        setSize(300, 200);
        setLocation(900,300);
        panel.setLayout(null);
        welcome.setBounds(70, 50, 150, 60);
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}