package Day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day15 {

    static String[][] maze;
        public static void main(String[] args) {
            
            try {
    
                //File file = new File("./src/Day15/input.txt");
                File file = new File("./src/Day15/sampleData.txt");
                Scanner myScanner = new Scanner(file);
                int lineCount = 0;
                while(myScanner.hasNextLine()){
                    myScanner.nextLine();
    
                    lineCount++;
                }
                myScanner.close();

                maze = new String[lineCount][];

                myScanner = new Scanner(file);
                int inputLine=0;
                while(myScanner.hasNextLine()){
                    String data = myScanner.nextLine();

                    maze[inputLine++] = data.split("");

                }

                printMaze();


            myScanner.close();
        } catch (FileNotFoundException e) {
           System.out.println("Could not find file...");
        }
    }



    public static void printMaze(){

        for (String[] row : maze) {
            System.out.println(Arrays.toString(row));
        }
    }
}
