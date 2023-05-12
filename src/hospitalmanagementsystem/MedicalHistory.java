import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
 
public class MedicalHistory extends JFrame {
 
    private JPanel mainPanel;
    private JTable doctorTable, symptomsTable, treatmentTable;
 
    public MedicalHistory() {
        setTitle("Medical History");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        mainPanel = new JPanel(new GridLayout(1, 3));
 
        DefaultTableModel doctorTableModel = new DefaultTableModel();
        doctorTableModel.addColumn("Doc ID");
        doctorTableModel.addColumn("Doctor Name");
 
        DefaultTableModel symptomsTableModel = new DefaultTableModel();
        symptomsTableModel.addColumn("Symptom");
        symptomsTableModel.addColumn("Date");
 
        DefaultTableModel treatmentTableModel = new DefaultTableModel();
        treatmentTableModel.addColumn("Treatment");
        treatmentTableModel.addColumn("Date");
 
        doctorTableModel.addRow(new Object[]{"001", "Dr. Smith"});
        doctorTableModel.addRow(new Object[]{"002", "Dr. Johnson"});
        doctorTableModel.addRow(new Object[]{"003", "Dr. Lee"});
 
        symptomsTableModel.addRow(new Object[]{"Fever", "2023-05-01"});
        symptomsTableModel.addRow(new Object[]{"Cough", "2023-05-02"});
        symptomsTableModel.addRow(new Object[]{"Headache", "2023-05-03"});
 
        treatmentTableModel.addRow(new Object[]{"Paracetamol", "2023-05-01"});
        treatmentTableModel.addRow(new Object[]{"Cough syrup", "2023-05-02"});
        treatmentTableModel.addRow(new Object[]{"Ibuprofen", "2023-05-03"});
 
        
        doctorTable = new JTable(doctorTableModel);
        symptomsTable = new JTable(symptomsTableModel);
        treatmentTable = new JTable(treatmentTableModel);
 
        JScrollPane doctorScrollPane = new JScrollPane(doctorTable);
        JScrollPane symptomsScrollPane = new JScrollPane(symptomsTable);
        JScrollPane treatmentScrollPane = new JScrollPane(treatmentTable);
 
        mainPanel.add(doctorScrollPane);
        mainPanel.add(symptomsScrollPane);
        mainPanel.add(treatmentScrollPane);
 
       
        add(mainPanel);
 
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new MedicalHistory();
    }
}

