import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JButton moduloButton;
    private JButton factorialButton;
    private JButton dotButton;

    private double num1 = 0;
    private double num2 = 0;
    private char operator;

    Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Input Field
        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        add(inputField, BorderLayout.NORTH);

        // Panel for Number Buttons
        JPanel numbersPanel = new JPanel();
        numbersPanel.setLayout(new GridLayout(4, 3));
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numbersPanel.add(numberButtons[i]);
        }
        add(numbersPanel, BorderLayout.CENTER);

        // Panel for Operation Buttons
        JPanel operationsPanel = new JPanel();
        operationsPanel.setLayout(new GridLayout(5, 1));
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        moduloButton = new JButton("%");
        factorialButton = new JButton("!");
        operationsPanel.add(operationButtons[0]);
        operationsPanel.add(operationButtons[1]);
        operationsPanel.add(operationButtons[2]);
        operationsPanel.add(operationButtons[3]);
        operationsPanel.add(moduloButton);
        operationsPanel.add(factorialButton);
        add(operationsPanel, BorderLayout.EAST);

        // Panel for Other Buttons
        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new GridLayout(2, 1));
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        otherPanel.add(equalsButton);
        otherPanel.add(clearButton);
        add(otherPanel, BorderLayout.SOUTH);

        // Add ActionListener to Operation Buttons
        for (int i = 0; i < 4; i++) {
            operationButtons[i].addActionListener(this);
        }
        moduloButton.addActionListener(this);
        factorialButton.addActionListener(this);

        // Add ActionListener to Other Buttons
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (Character.isDigit(command.charAt(0))) {
            inputField.setText(inputField.getText() + command);
        } else if (command.equals(".")) {
            inputField.setText(inputField.getText() + ".");
        } else if (command.equals("C")) {
            inputField.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(inputField.getText());
            double result = calculate(num1, num2, operator);
            inputField.setText(String.valueOf(result));
        } else if (command.equals("!")) {
            int num = Integer.parseInt(inputField.getText());
            long factorial = calculateFactorial(num);
            inputField.setText(String.valueOf(factorial));
        } else {
            num1 = Double.parseDouble(inputField.getText());
            operator = command.charAt(0);
            inputField.setText("");
        }
    }

    private double calculate(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0)
                    return num1 / num2;
                else
                    return 0;
            case '%':
                if (num2 != 0)
                    return num1 % num2;
                else
                    return 0;
            default:
                return 0;
        }
    }

    private long calculateFactorial(int num) {
        if (num == 0)
            return 1;
        long factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator();
        });
    }
}
