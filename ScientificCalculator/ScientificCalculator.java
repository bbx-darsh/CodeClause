/*
 * Programmer :- Adarsh Mishra
 * Project :- SCientific Calculator using JAVA 
 */


import java.util.Scanner; // importing Scanner Class

public class ScientificCalculator {
    public static void main(String[] args)  // Main method for execution
    { 
        Scanner scanner = new Scanner(System.in); // Scanner Class

        System.out.println("Scientific Calculator - Calculate Now");

        while (true) // Logic starts from here
        { 
            System.out.println("Enter an expression OR type 'exit' to quit the Calculator ");              // Instructions to follow 
            System.out.println("Expressions :- sin(), cos(), tan(), (+), (-), (*), (/), (^), sqrt()"); 
            System.out.println("Enter Now :- "); 
            System.out.println(); 
            System.out.println();

            String expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("exit")) {
                break;
            }
            try {                                                           // try and catch block
                double result = evaluateExpression(expression);
                System.out.println("Result: " + result);
                System.out.println();
            } catch (Exception e) {
                System.out.println("!!!    Invalid expression     !!! \n        Please try again.");
                System.out.println();
            }
        }

        scanner.close();
        System.out.println();
        System.out.println();
        System.out.println("--------Thank you for using the Calculator-------");
    }

    private static double evaluateExpression(String expression) {                   // Function to Evaluate the Expression 
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') {
                    nextChar();
                }
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                while (true) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();                       
                while (true) {                              //    Looping
                    if (eat('*')) {
                        x *= parseFactor();
                    } else if (eat('/')) {
                        x /= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) {         //additon operator
                    return parseFactor();
                }
                if (eat('-')) {         //subtraction operator
                    return -parseFactor();
                }

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {             // Checking the characters
                    while ((ch >= '0' && ch <= '9') || ch == '.') {
                        nextChar();
                    }
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {                            //Checking alphabets
                    while (ch >= 'a' && ch <= 'z') {
                        nextChar();
                    }
                    String func = expression.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) {              //SquareRoot
                        x = Math.sqrt(x);
                    } else if (func.equals("sin")) {        //sin function
                        x = Math.sin(Math.toRadians(x));
                    } else if (func.equals("cos")) {        //cos function
                        x = Math.cos(Math.toRadians(x));
                    } else if (func.equals("tan")) {        //tan function
                        x = Math.tan(Math.toRadians(x));
                    } else {
                        throw new RuntimeException("Unknown function: " + func);        //Unknown Function
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) {
                    x = Math.pow(x, parseFactor());
                }

                return x;                       // Returning the value
            }
        }.parse();
    }
}
