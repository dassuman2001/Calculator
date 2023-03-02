import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] buttons;
    private String[] buttonLabels = {
        "7", "8", "9", "/", "B",
        "4", "5", "6", "*", "C",
        "1", "2", "3", "-", " ",
        "0", ".", "=", "+", " "
    };
    private double currentNumber = 0;
    private char currentOperator;

    public Calculator() {
        // Create the text field
        textField = new JTextField();
        textField.setEditable(false);

        // Create the buttons
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
        }

        // Set the layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        // Add the text field
        c.gridwidth = GridBagConstraints.REMAINDER;
        add(textField, c);

        // Add the buttons
        for (int i = 0; i < buttons.length; i++) {
            if (i % 5 == 4) {
                c.gridwidth = GridBagConstraints.REMAINDER;
            } else {
                c.gridwidth = 1;
            }
            add(buttons[i], c);
        }

        // Set the frame properties
        setTitle("Calculator");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String label = ((JButton) e.getSource()).getText();

        if (label.equals("C")) {
            currentNumber = 0;
            currentOperator = '\0';
            textField.setText("");
        } else if (label.equals("=")) {
            double result = calculate(currentNumber, Double.parseDouble(textField.getText()), currentOperator);
            textField.setText(Double.toString(result));
            currentNumber = result;
            currentOperator = '\0';
        } else if (label.equals("+") || label.equals("-") || label.equals("*") || label.equals("/")) {
            currentNumber = Double.parseDouble(textField.getText());
            currentOperator = label.charAt(0);
            textField.setText("");
        } else if (label.equals("B")) {
            String text = textField.getText();
            if (text.length() > 0) {
                textField.setText(text.substring(0, text.length() - 1));
            }
        } else {
            textField.setText(textField.getText() + label);
        }
    }

    private double calculate(double a, double b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
    }
}
