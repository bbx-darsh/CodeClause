/*
 * Programmer :- Adarsh Mishra
 * Project :- Temperature Converter using JAVA
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;                      // Importing essential libraries
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter - Convert Now !");                             //Title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setHorizontalAlignment(JTextField.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel(" Enter Temperature to convert:"));                   //Text Area
        inputPanel.add(inputField);
        add(inputPanel, BorderLayout.NORTH);

        resultLabel = new JLabel("Result");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));                     //Result Layout
        add(resultLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();                                                  //Button layout
        buttonPanel.setLayout(new GridLayout(1, 2));

        JButton celsiusButton = new JButton("Celsius to Fahrenheit");                   //Button1
        JButton fahrenheitButton = new JButton("Fahrenheit to Celsius");                //Button2
        JButton kelvinButton = new JButton("Kelvin to Celsius");                        //Button3

        buttonPanel.add(celsiusButton);
        buttonPanel.add(fahrenheitButton);
        buttonPanel.add(kelvinButton);

        add(buttonPanel, BorderLayout.SOUTH);

        celsiusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertCelsiusToFahrenheit();                                         //Function to convert from Celsius to Fahrenheit
            }
        });

        fahrenheitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertFahrenheitToCelsius();                                         //Function to convert from Fahrenheit to Celsius
            }
        });

        kelvinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertKelvinToCelsius();                                             //Function to convert from Kelvin to Celsius
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void convertCelsiusToFahrenheit() {                                      //Function 1
        try {
            double celsius = Double.parseDouble(inputField.getText());
            double fahrenheit = (celsius * 9 / 5) + 32;
            resultLabel.setText("Result: " + fahrenheit + " °F");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    private void convertFahrenheitToCelsius() {                                      //Function2
        try {
            double fahrenheit = Double.parseDouble(inputField.getText());
            double celsius = (fahrenheit - 32) * 5 / 9;
            resultLabel.setText("Result: " + celsius + " °C");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    private void convertKelvinToCelsius() {                                         //Function 3
        try {
            double kelvin = Double.parseDouble(inputField.getText());
            double celsius = kelvin - 273.15;
            resultLabel.setText("Result: " + celsius + " °C");
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        }
    }

    public static void main(String[] args) {                //Main Function for Execution of the project
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TemperatureConverter();
            }
        });
    }
}
