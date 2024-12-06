package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {

    static String[][] mapOfPositions = new String[10][];
    static int row, col;
        public static void main(String[] args) {
            try {
                    // Create a File object to read from "input.txt"
        
                    //File myObj = new File("./src/Day6/input.txt"); 
                    File myObj = new File("./src/Day6/sampleData.txt");
        
                    Scanner myReader = new Scanner(myObj);
        
                    
                    int lineCount = 0;
                    // Read each line from the file
                    while (myReader.hasNextLine()) {
                        Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
                        String line = data.nextLine();
    
                        mapOfPositions[lineCount++] = line.split(""); //Splits each line into individual characters and then loads into crossword.
                    
                    data.close();
                }
                myReader.close();
    
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


    }
}