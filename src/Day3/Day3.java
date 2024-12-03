package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        try {
            // Create a File object to read from "input.txt"
            File myObj = new File("./src/Day3/input.txt");
            Scanner myReader = new Scanner(myObj);

            // Read each line from the file and add it to reports
            while (myReader.hasNextLine()) {
                Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
                String line = data.nextLine();
                System.out.println(line);
                data.close();
            }
            myReader.close();


            

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    }

