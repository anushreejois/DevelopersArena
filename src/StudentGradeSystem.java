import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeSystem extends JFrame {
    private JTextField nameField;
    private JTextField marksField;
    private JTextArea resultArea;

    public StudentGradeSystem() {
        // Set up the window
        setTitle("Student Grade System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel nameLabel = new JLabel("Student Name:");
        nameField = new JTextField(15);

        JLabel marksLabel = new JLabel("Marks (0-100):");
        marksField = new JTextField(10);

        JButton addButton = new JButton("Add Grade");
        JButton clearButton = new JButton("Clear");

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Layout - top panel for inputs
        JPanel topPanel = new JPanel();
        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(marksLabel);
        topPanel.add(marksField);
        topPanel.add(addButton);
        topPanel.add(clearButton);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultArea.setText("");
                nameField.setText("");
                marksField.setText("");
            }
        });

        setVisible(true);
    }

    // Method to add student and calculate grade
    private void addStudent() {
        String name = nameField.getText().trim();
        String marksText = marksField.getText().trim();

        if (name.isEmpty() || marksText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and marks!");
            return;
        }

        try {
            int marks = Integer.parseInt(marksText);

            if (marks < 0 || marks > 100) {
                JOptionPane.showMessageDialog(this, "Marks must be between 0 and 100!");
                return;
            }

            String grade = calculateGrade(marks);
            String result = name + " - Marks: " + marks + " - Grade: " + grade + "\n";
            resultArea.append(result);

            // Clear input fields
            nameField.setText("");
            marksField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for marks!");
        }
    }

    // Method to calculate grade
    private String calculateGrade(int marks) {
        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else if (marks >= 50) return "D";
        else return "F";
    }

    // Main method
    public static void main(String[] args) {
        new StudentGradeSystem();
    }
}
