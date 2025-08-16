import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Segoe UI", Font.BOLD, 24);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public SimpleCalculator() {
        // Frame setup
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(45, 45, 45)); // Dark background

        // Display setup
        textfield = new JTextField();
        textfield.setBounds(30, 25, 340, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        textfield.setBackground(new Color(30, 30, 30));
        textfield.setForeground(Color.WHITE);
        textfield.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Buttons
        addButton = createButton("+", new Color(255, 167, 38));
        subButton = createButton("-", new Color(255, 167, 38));
        mulButton = createButton("×", new Color(255, 167, 38));
        divButton = createButton("÷", new Color(255, 167, 38));
        decButton = createButton(".", new Color(70, 130, 180));
        equButton = createButton("=", new Color(46, 204, 113));
        delButton = createButton("Del", new Color(231, 76, 60));
        clrButton = createButton("C", new Color(231, 76, 60));
        negButton = createButton("±", new Color(70, 130, 180));

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (JButton btn : functionButtons) {
            btn.addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i), new Color(60, 60, 60));
            numberButtons[i].addActionListener(this);
        }

        // Extra buttons
        negButton.setBounds(30, 430, 100, 50);
        delButton.setBounds(140, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        // Panel for main buttons
        panel = new JPanel();
        panel.setBounds(30, 100, 340, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(45, 45, 45));

        // Layout buttons in grid
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(subButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(addButton);

        // Add components to frame
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    // Helper method to style buttons
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(myFont);
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText().concat("."));
            }
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '*';
            textfield.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 != 0) result = num1 / num2;
                    else { textfield.setText("Error"); return; }
                    break;
            }
            textfield.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
            num1 = num2 = result = 0;
        }
        if (e.getSource() == delButton) {
            String string = textfield.getText();
            if (!string.isEmpty()) {
                textfield.setText(string.substring(0, string.length() - 1));
            }
        }
        if (e.getSource() == negButton) {
            if (!textfield.getText().isEmpty()) {
                double temp = Double.parseDouble(textfield.getText());
                temp *= -1;
                textfield.setText(String.valueOf(temp));
            }
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}

