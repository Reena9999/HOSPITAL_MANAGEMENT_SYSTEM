/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalmanagementsystem;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.util.Date;
import java.util.Enumeration;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.event.*;

public class patientRegistertion extends JFrame {
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(patientHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(patientHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(patientHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(patientHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new patientHome().setVisible(true);
            }
        });
    }

    public patientRegistertion() {
        JFrame registerFrame = new JFrame();
        registerFrame.setLocation(900, 300);
        registerFrame.setSize(450, 450);
        registerFrame.setTitle("Patient Registration");
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLocation(600, 350);
        registerFrame.setLayout(null);

        // Create name label and text field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(60, 40, 200, 20);

        JTextField firstNameTextField = new JTextField(20);
        firstNameTextField.setName("firstNameTextField");
        firstNameTextField.setBounds(150, 40, 200, 20);

        // Create name label and text field
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(60, 65, 200, 20);

        JTextField lastNameTextField = new JTextField(20);
        lastNameTextField.setName("lastNameTextField");
        lastNameTextField.setBounds(150, 65, 200, 20);

        // Create sex label and radio buttons
        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setBounds(60, 100, 200, 20);

        JRadioButton maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setName("maleRadioButton");
        maleRadioButton.setBounds(150, 100, 65, 20);

        JRadioButton femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setName("femaleRadioButton");
        femaleRadioButton.setBounds(250, 100, 200, 20);

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleRadioButton);
        sexGroup.add(femaleRadioButton);

        // Create age label and text field
        JLabel DOBLabel = new JLabel("DOB:");
        DOBLabel.setBounds(60, 130, 200, 20);

        SpinnerDateModel datePicker = new SpinnerDateModel(new Date(), null, null, getDefaultCloseOperation());
        JSpinner spinner = new JSpinner(datePicker);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinner, dateFormat.toPattern());
        DateFormatter dateFormatter = (DateFormatter) dateEditor.getTextField().getFormatter();
        dateFormatter.setAllowsInvalid(false);
        spinner.setEditor(dateEditor);
        spinner.setBounds(150, 130, 200, 20);

        // Create blood group label and drop-down box , rh group drop-down box
        JLabel bloodGroupLabel = new JLabel("Blood Group:");
        bloodGroupLabel.setBounds(60, 160, 200, 20);

        String[] bloodGroups = { "A", "B", "AB", "O" };
        JComboBox<String> bloodGroupComboBox = new JComboBox<>(bloodGroups);
        bloodGroupComboBox.setName("bloodGroupComboBox");
        bloodGroupComboBox.setBounds(150, 160, 50, 20);

        String[] RhGroups = { "+", "-" };
        JComboBox<String> RhGroupComboBox = new JComboBox<>(RhGroups);
        RhGroupComboBox.setName("RhGroupComboBox");
        RhGroupComboBox.setBounds(200, 160, 50, 20);

        // Create phone number label and text field
        JLabel phNumberLabel = new JLabel("Ph Number:");
        phNumberLabel.setBounds(60, 200, 200, 20);

        JTextField phNumberTextField = new JTextField(20);
        phNumberTextField.setName("phNumberTextField");
        phNumberTextField.setBounds(150, 200, 200, 20);

        // Create email label and text field
        JLabel emailLabel = new JLabel("eMail:");
        emailLabel.setBounds(60, 240, 200, 20);

        JTextField emailTextField = new JTextField(20);
        emailTextField.setName("emailTextField");
        emailTextField.setBounds(150, 240, 200, 20);

        // Create password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(60, 270, 200, 20);

        JTextField passwordTextField = new JTextField(20);
        passwordTextField.setName("passwordTextField");
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

        registerFrame.add(DOBLabel);
        registerFrame.add(spinner);

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
        actionRegister(registerFrame, firstNameTextField, lastNameTextField, sexGroup, spinner, bloodGroupComboBox,
                RhGroupComboBox, phNumberTextField, emailTextField, passwordTextField, registerButton);
        registerFrame.setVisible(true);
    }

    /**
     * @param registerFrame
     * @param firstNameTextField
     * @param lastNameTextField
     * @param sexLabel
     * @param bloodGroupComboBox
     * @param RhGroupComboBox
     * @param spinner
     * @param phNumberTextField
     * @param emailTextField
     * @param passwordTextField
     * @param registerButton
     */
    public static void actionRegister(JFrame registerFrame, JTextField firstNameTextField, JTextField lastNameTextField,
            ButtonGroup sexLabel, JSpinner spinner, JComboBox bloodGroupComboBox, JComboBox RhGroupComboBox,
            JTextField phNumberTextField, JTextField emailTextField, JTextField passwordTextField,
            JButton registerButton) {
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = (Connection) DriverManager
                            .getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystem", "root", "94807279");

                    PreparedStatement st = (PreparedStatement) connection.prepareStatement(

                            "insert into tblPatient(FirstName ,LastName,PhNumber,Email,Sex,DateOfBirth,BloodGroup,Password) values(?,?,?,?,?,?,?,?)");
                    st.setString(1, firstNameTextField.getText());
                    st.setString(2, lastNameTextField.getText());
                    st.setString(3, phNumberTextField.getText());
                    st.setString(4, emailTextField.getText());

                    JRadioButton selectedRadioButton = null;
                    Enumeration<AbstractButton> buttons = sexLabel.getElements();
                    while (buttons.hasMoreElements()) {
                        AbstractButton button = buttons.nextElement();
                        if (button.isSelected()) {
                            selectedRadioButton = (JRadioButton) button;
                            break;
                        }
                    }

                    if (selectedRadioButton.getText().equalsIgnoreCase("Male")) {
                        st.setString(5, "0");
                    } else {
                        st.setString(5, "1");
                    }

                    Date selectedDate = (Date) spinner.getValue();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = dateFormat.format(selectedDate);
                    st.setString(6, dateString);

                    st.setString(7,
                            (String) bloodGroupComboBox.getSelectedItem() + (String) RhGroupComboBox.getSelectedItem());
                    st.setString(8, passwordTextField.getText());
                    int rowsAffected = st.executeUpdate();
                    if (rowsAffected > 0) {
                        PreparedStatement st2 = (PreparedStatement) connection
                                .prepareStatement("Select PatientID from tblPatient where Email=? and Password=?");
                        st2.setString(1, emailTextField.getText());
                        st2.setString(2, passwordTextField.getText());

                        ResultSet rs2 = st2.executeQuery();
                        if (rs2.next()) {
                            registerFrame.setVisible(false);                            

                            new patientHome(rs2.getString(1)).setVisible(true);
                        }
                    } else {

                    }

                } catch (SQLException | ClassNotFoundException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });
    }
}