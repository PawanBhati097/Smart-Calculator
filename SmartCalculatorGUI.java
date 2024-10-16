import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SmartCalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2;
    private char operation;

    public SmartCalculatorGUI() {
        frame = new JFrame("Smart Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {"7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"};

        for (String button : buttons) {
            JButton jButton = new JButton(button);
            jButton.addActionListener(new ButtonListener());
            panel.add(jButton);
        }

        frame.add(panel, BorderLayout.CENTER);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
        frame.add(clearButton, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                num1 = Double.parseDouble(textField.getText());
                operation = command.charAt(0);
                textField.setText("");
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                double result = 0;

                switch (operation) {
                    case '+':
                        result = add(num1, num2);
                        break;
                    case '-':
                        result = subtract(num1, num2);
                        break;
                    case '*':
                        result = multiply(num1, num2);
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = divide(num1, num2);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error: Division by zero is not allowed.");
                            result = 0;
                        }
                        break;
                }

                textField.setText(String.valueOf(result));
            } else {
                textField.setText(textField.getText() + command);
            }
        }
    }

    public static double add(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    public static double divide(double num1, double num2) {
        return num1 / num2;
    }

    public static void main(String[] args) {
        new SmartCalculatorGUI();
    }
}
