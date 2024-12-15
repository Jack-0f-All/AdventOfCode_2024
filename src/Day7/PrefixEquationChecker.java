package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class PrefixEquationChecker {

    public static void main(String[] args) {
        int validCount = 0; // Count of lines with valid equations

        try {
            File file = new File("/mnt/data/input.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(": ");

                if (parts.length != 2) continue; // Skip malformed lines

                long targetValue = Long.parseLong(parts[0]);
                String[] numbers = parts[1].split(" ");

                // Generate all combinations of `*` and `+` operators
                int operatorCount = numbers.length - 1;
                String[] operators = new String[operatorCount];

                if (evaluate(targetValue, operators, numbers, 0)) {
                    validCount++;
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
            e.printStackTrace();
        }

        System.out.println("Number of valid equations: " + validCount);
    }

    // Recursive function to evaluate all operator combinations
    private static boolean evaluate(long targetValue, String[] operators, String[] numbers, int index) {
        if (index == operators.length) {
            // Construct prefix expression and evaluate it
            String expression = String.join(" ", operators) + " " + String.join(" ", numbers);
            return evalPreFix(expression) == targetValue;
        }

        // Try '*' at this position
        operators[index] = "*";
        if (evaluate(targetValue, operators, numbers, index + 1)) {
            return true;
        }

        // Try '+' at this position
        operators[index] = "+";
        return evaluate(targetValue, operators, numbers, index + 1);
    }

    // Prefix evaluator function
    private static long evalPreFix(String equation) {
        String[] terms = equation.split(" ");
        Stack<Long> stack = new Stack<>();

        for (int i = terms.length - 1; i >= 0; i--) {
            if (isNumerical(terms[i])) {
                stack.push(Long.parseLong(terms[i]));
            } else {
                long a = stack.pop();
                long b = stack.pop();

                switch (terms[i]) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                }
            }
        }

        return stack.pop();
    }

    // Helper function to check if a string is a number
    private static boolean isNumerical(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
