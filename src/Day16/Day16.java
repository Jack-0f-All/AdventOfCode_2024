package Day16;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Day16 {

    static String[][] initialMaze;
    static String[][] scoredMaze;
    static int START_ROW;
    static int START_COL;
    static int END_ROW;
    static int END_COL;
    static HashMap <String, Integer> validSeats = new HashMap<>();
    
            public static void main(String[] args) {
                
                try {
        
                    //File file = new File("./src/Day16/input.txt");
                    File file = new File("./src/Day16/sampleData.txt");
                    Scanner myScanner = new Scanner(file);
                    int lineCount = 0;
                    while(myScanner.hasNextLine()){
                        myScanner.nextLine();
        
                        lineCount++;
                    }
                    myScanner.close();
    
                    initialMaze = new String[lineCount][];
                    scoredMaze = new String[lineCount][];
    
                    myScanner = new Scanner(file);
                    int inputLine=0;
                    while(myScanner.hasNextLine()){
                        String data = myScanner.nextLine();
    
                        initialMaze[inputLine] = data.split("");
                        scoredMaze[inputLine++] = data.split("");
    
                    }
                    
                    findStartAndEnd();
                    
                    scoredMaze[START_ROW][START_COL] = Integer.toString(0);
                    
                    scoredMaze[END_ROW][END_COL] = Integer.toString(0);
                    //recursiveMazeSearch(END_ROW, END_COL, 0);
                    recursiveMazeSearchWithScoring(START_ROW, START_COL, 0, "<");
                    //countSeats(END_ROW, END_COL, 0);
                    //System.out.println(validSeats.size());
                    //printMaze(scoredMaze);
                    writeMatrixToFile(scoredMaze, "scoredMaze");
    
                    //runShortestPath();
                    //printMaze(scoredMaze);
                    System.out.println(scoredMaze[END_ROW][END_COL]);
                myScanner.close();
            } catch (FileNotFoundException e) {
               System.out.println("Could not find file...");
            }
        }
    
    
        //scores the shortest path to the End of the maze.
        public static void recursiveMazeSearch(int row, int col, int stepsAwayFromEnd){
            
            //base case

            String currentValue =  scoredMaze[row][col];
            if(currentValue.equals("S")){
                scoredMaze[row][col] = Integer.toString(stepsAwayFromEnd);
                return;
            }
            if(currentValue.equals("#")){
    
                return;
            }
    
            if(!currentValue.equals(".") && Integer.parseInt(currentValue) < stepsAwayFromEnd){
                return;
            }
    
        //--------------------------------------------------------------------------------------------------------


            //recursive case

            scoredMaze[row][col] = Integer.toString(stepsAwayFromEnd);
    
            recursiveMazeSearch(row+1, col, stepsAwayFromEnd+1);
            recursiveMazeSearch(row, col+1, stepsAwayFromEnd+1);
            recursiveMazeSearch(row-1, col, stepsAwayFromEnd+1);
            recursiveMazeSearch(row, col-1, stepsAwayFromEnd+1);
    
    
        }
    
        public static void recursiveMazeSearchWithScoring(int row, int col, int scoredStepsFromEnd, String orientation){
    
            String currentValue =  scoredMaze[row][col];
            if(currentValue.equals("E")){
                scoredMaze[row][col] = Integer.toString(scoredStepsFromEnd);
                return;
            }
            if(currentValue.equals("#")){
    
                return;
            }
    
            if(!currentValue.equals(".") && Integer.parseInt(currentValue) < scoredStepsFromEnd){
                return;
            }
    
            scoredMaze[row][col] = Integer.toString(scoredStepsFromEnd);
    
    
            switch(orientation){
                case "<" :  recursiveMazeSearchWithScoring(row+1, col, scoredStepsFromEnd+1001, "V");
                            recursiveMazeSearchWithScoring(row, col+1, scoredStepsFromEnd+1001, ">");
                            recursiveMazeSearchWithScoring(row-1, col, scoredStepsFromEnd+1001, "^");
                            recursiveMazeSearchWithScoring(row, col-1, scoredStepsFromEnd+1, "<");
                            break;
    
                case "^" :  recursiveMazeSearchWithScoring(row+1, col, scoredStepsFromEnd+1001, "V");
                            recursiveMazeSearchWithScoring(row, col+1, scoredStepsFromEnd+1001, ">");
                            recursiveMazeSearchWithScoring(row-1, col, scoredStepsFromEnd+1, "^");
                            recursiveMazeSearchWithScoring(row, col-1, scoredStepsFromEnd+1001, "<");
                            break;
                case ">" :  recursiveMazeSearchWithScoring(row+1, col, scoredStepsFromEnd+1001, "V");
                            recursiveMazeSearchWithScoring(row, col+1, scoredStepsFromEnd+1, ">");
                            recursiveMazeSearchWithScoring(row-1, col, scoredStepsFromEnd+1001, "^");
                            recursiveMazeSearchWithScoring(row, col-1, scoredStepsFromEnd+1001, "<");
                            break;
    
                case "V" :  recursiveMazeSearchWithScoring(row+1, col, scoredStepsFromEnd+1, "V");
                            recursiveMazeSearchWithScoring(row, col+1, scoredStepsFromEnd+1001, ">");
                            recursiveMazeSearchWithScoring(row-1, col, scoredStepsFromEnd+1001, "^");
                            recursiveMazeSearchWithScoring(row, col-1, scoredStepsFromEnd+1001, "<");
                            break;
            }
    
    
        }
    
        public static void runShortestPath(){
    
            String reindeer = "<";
            String nextRotation="^";
            int deltaRow = 0;
            int deltaCol = 0;
            int score = 0;
            int row = 13;
            int col = 1;
            boolean hasRotated = false;
    
            int currentValue = Integer.parseInt(scoredMaze[START_ROW][START_COL]);// change to start Position later
    
            int steps = 0;
    
            while(currentValue != 0){
                
                
                switch (reindeer) {
                    case "^": deltaRow = -1; deltaCol = 0; nextRotation = ">"; break;
                    case ">": deltaRow = 0; deltaCol = 1; nextRotation = "V"; break;
                    case "V": deltaRow = 1; deltaCol = 0; nextRotation = "<"; break;
                    case "<": deltaRow = 0; deltaCol = -1; nextRotation = "^"; break;
                }
    
                String nextValue = scoredMaze[row + deltaRow][col + deltaCol];
                if(!nextValue.equals("#") && isNumeric(nextValue) && Integer.parseInt(nextValue) < currentValue){
                        scoredMaze[row][col] = reindeer;
                        row += deltaRow;
                        col += deltaCol;
                        score += hasRotated ? 1001: 1;
                        hasRotated = false;
                        currentValue = Integer.parseInt(scoredMaze[row][col]);
                        
                }else{
                    hasRotated = true;
                    reindeer = nextRotation;
                }
    
                steps++;
                
            }
    
            System.out.println(score);
    
        }

        public static void countSeats(int row, int col, int stepsAwayFromEnd){
    
            // System.out.println(scoredMaze[row][column]);
            String currentValue =  scoredMaze[row][col];

            if(currentValue.equals("S")){
                scoredMaze[row][col] = Integer.toString(stepsAwayFromEnd);
                return;
            }
            if(currentValue.equals("#") || currentValue.equals("O")){
    
                return;
            }
    
            if(!currentValue.equals(".") && Integer.parseInt(currentValue) < stepsAwayFromEnd){
                return;
            }
    
            scoredMaze[row][col] = "O";

            if(!validSeats.containsKey(row + "," + col)){
                validSeats.put(row + "," + col, 1);
            }
    
            countSeats(row+1, col, stepsAwayFromEnd+1);
            countSeats(row, col+1, stepsAwayFromEnd+1);
            countSeats(row-1, col, stepsAwayFromEnd+1);
            countSeats(row, col-1, stepsAwayFromEnd+1);

    
        }
    
    
    
        public static void printMaze(String[][] maze){
    
            // for (int r = 0; r < maze.length; r++) {
            //     for (int c = 0; c < maze[r].length; c++) {
            //         System.out.print(maze[r][c]);
            //         System.out.print(" ");
            //     }
    
            //     System.out.println();
                
            // }
            System.out.println();
            System.out.println();
            int columnWidth = 1; // You can adjust this to your needs, based on the length of the longest element
    
            // Loop through the array and print each element with padding
            for (String[] row : maze) {
                for (int i = 0; i < row.length; i++) {
                    // Print each element with the specified width
                    System.out.printf("%" + columnWidth + "s", row[i]);
                    // Print a comma if it's not the last element in the row
                    if (i < row.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    
        public static void writeMatrixToFile(String[][] maze, String fileName){
            String output = "";
    
            int columnWidth = 5; // You can adjust this to your needs, based on the length of the longest element
    
            // Loop through the array and print each element with padding
            for (String[] row : maze) {
                for (int i = 0; i < row.length; i++) {
                    // Print each element with the specified width
                    output+= String.format("%" + columnWidth + "s", row[i]);
                    // Print a comma if it's not the last element in the row
                    if (i < row.length - 1) {
                        output+=(", ");
                    }
                }
                output+="\n";
            }
    
    
            String filePath = "./src/Day16/"+ fileName+".txt";
                
                // Create a File object
                File file = new File(filePath);
                
                // Create and write to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write(output);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    
    }
    
    public static void findStartAndEnd(){
        for (int r = 0; r < initialMaze.length; r++) {
            for (int c = 0; c < initialMaze[r].length; c++) {
                if(scoredMaze[r][c].equals("S")){
                    START_ROW = r;
                    START_COL = c;
                }   
                if(scoredMaze[r][c].equals("E")){
                    END_ROW = r;
                    END_COL = c;
                }  
            }   
        }
}

public static boolean isNumeric(String str) {
    try {
        Double.parseDouble(str);
        return true;
    } catch (NumberFormatException e) {
        return false;
    }
}
}
