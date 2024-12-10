package Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Day7 {
    
    static long sumOfValidCalibrations = 0;
    
        public static void main(String[] args) {
            try {

                //3236132240
                
                // Create a File object to read from "input.txt"
    
                //File myObj = new File("./src/Day7/input.txt"); 
                File myObj = new File("./src/Day7/stepDemo.txt");
    
                Scanner myReader = new Scanner(myObj);
                int countOfLinesInFile=0;
                while(myReader.hasNextLine()) {
                    myReader.nextLine();
                    countOfLinesInFile++;
                }
                myReader.close();
                myReader = new Scanner(myObj); //resets the reader to the beginning of the file.
            
                int lineCount = 0;
                // Read each line from the file
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    sumOfValidCalibrations += checkCalibration(line.split(": "));
                }
                myReader.close();
            
        

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        System.out.println(sumOfValidCalibrations);
    }

    public static long checkCalibration(String[] line){

        long testValue = Long.parseLong(line[0]);
        boolean isValidCalibration = false;
        String values = line[1];
        
        String[] operators = "*".repeat(values.split(" ").length-1).split("");


        System.out.println(testValue);

        for (int i = 0; i < operators.length; i++) {
            // Set the current position to "*"
            operators[i] = "*";
            if(evalPreFix(String.join(" ",operators) + " " + values) == testValue){
                //System.out.println(line[0]+":"+ String.join(" ", operators)+" "+String.join(" ", values));
                return testValue;
            }
            // Iterate over subsequent positions
            for (int n = i; n < operators.length; n++) {
                // Temporarily set the current position to "+"
                operators[n] = "+";
                if(evalPreFix(String.join(" ",operators) + " " + values) == testValue){
                    System.out.println(line[0]+":"+ String.join(" ", operators)+" "+String.join(" ", values));
                    //System.out.println(testValue);
                    return testValue;
            }
        
            // Reset positions to their original state in a single step
            for (int x = i; x < operators.length; x++) {
                operators[x] = "*";
            }
        
            // Set the current position to "+"
            operators[i] = "+";
            }
        }
        return 0;
    }
    
        
    public static long evalPreFix(String equation){

        System.out.print("Checking equation " + equation + " = ");
        String[] terms = equation.split(" ");
        Stack<Long> prefixStack = new Stack<>();

        for(int i=terms.length-1; i>=0; i--){
            
            if(isNumerical(terms[i])){
                prefixStack.add(Long.parseLong(terms[i]));
            }else{
                long a = prefixStack.pop();
                long b = prefixStack.pop();

                switch(terms[i]){

                    case "+" : prefixStack.add(a + b); break;

                    case "*" : prefixStack.add(a * b); break;
                }
            }
        }

        long answer = prefixStack.pop();
        System.out.println(answer);
        return answer;

    }


    //Stolen idea. Uses a try/catch block to determine if the string is a number or a symbol.
    public static boolean isNumerical(String s){
        
        try {

            Long.parseLong(s);
            return true;
            
        } catch (NumberFormatException e) {
            return false;
        }
    }
        
}
