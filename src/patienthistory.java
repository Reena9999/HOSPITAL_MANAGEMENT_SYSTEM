import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
 
public class patienthistory extends JFrame {
 
    private JPanel mainPanel;
    private JTable historyTable;
 
    public patienthistory() {
        setTitle("Patient History");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
        
        mainPanel = new JPanel(new BorderLayout());
 
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Time");
        tableModel.addColumn("Doctor");
        tableModel.addColumn("Specialist");
 
        tableModel.addRow(new Object[]{"2023-05-01", "10:00 AM", "Dr. Smith", "Cardiologist"});
        tableModel.addRow(new Object[]{"2023-05-02", "2:30 PM", "Dr. Johnson", "Oncologist"});
        tableModel.addRow(new Object[]{"2023-05-03", "11:15 AM", "Dr. Lee", "Neurologist"});
 
        historyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);
 
        mainPanel.add(scrollPane, BorderLayout.CENTER);
 
        add(mainPanel);
 
        setVisible(true);
    }
 
    public static void main(String[] args) {
        new patienthistory();
    }
}

