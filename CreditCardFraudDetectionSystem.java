import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CreditCardFraudDetectionSystem extends JFrame {
    private JTextField cardNumberField, amountField, locationField;
    private JButton submitButton;
    private JTextArea resultArea;

    public CreditCardFraudDetectionSystem() {
        setTitle("Credit Card Fraud Detection System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Create form components
        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();

        JLabel amountLabel = new JLabel("Transaction Amount:");
        amountField = new JTextField();

        JLabel locationLabel = new JLabel("Location:");
        locationField = new JTextField();

        submitButton = new JButton("Submit Transaction");
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Add components to the frame
        add(cardNumberLabel);
        add(cardNumberField);
        add(amountLabel);
        add(amountField);
        add(locationLabel);
        add(locationField);
        add(submitButton);
        add(new JScrollPane(resultArea));

        // Add action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processTransaction();
            }
        });
    }

    private void processTransaction() {
        String cardNumber = cardNumberField.getText();
        String amountStr = amountField.getText();
        String location = locationField.getText();

        // Validate input
        if (cardNumber.isEmpty() || amountStr.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform fraud detection
        String result = detectFraud(cardNumber, amount, location);
        resultArea.setText(result);
    }

    private String detectFraud(String cardNumber, double amount, String location) {
        // Basic fraud detection rules
        Random rand = new Random();
        boolean isFraudulent = false;

        // Example rules:
        if (amount > 1000) {
            isFraudulent = true; // Flag large transactions
        } 
        // Randomly flag transactions as fraudulent for demo purposes
        if (rand.nextInt(10) < 2) {
            isFraudulent = true; // 20% chance of being fraudulent
        }

        if (isFraudulent) {
            return "Transaction is flagged as fraudulent!\nCard Number: " + cardNumber + "\nAmount: " + amount + "\nLocation: " + location;
        } else {
            return "Transaction is approved.\nCard Number: " + cardNumber + "\nAmount: " + amount + "\nLocation: " + location;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CreditCardFraudDetectionSystem app = new CreditCardFraudDetectionSystem();
            app.setVisible(true);
        });
    }
}
