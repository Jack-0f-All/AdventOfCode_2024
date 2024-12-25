package Day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import library.PrintDelay;
public class Day18 {

    static final int NUMBER_OF_BYTES = 1024;
    static int shortest_Distance = Integer.MAX_VALUE;
    static int count = 0;
    static String[][] maze;
    static int columnWidth = 2;

    public static void main(String[] args) {
        try {
            
            //File myFile = new File("./src/Day18/sample.txt");
            File myFile = new File("./src/Day18/input.txt");

            Scanner myReader = new Scanner(myFile);

            initMaze(71);
            while(count < NUMBER_OF_BYTES){
                String[] line = myReader.nextLine().split(",");
                maze[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = "#";
                count++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
           System.out.println("Cannot find that file...");
        }
        
        System.out.println("Searching maze...");
        recursiveMazeSearch(0, 0, 0);

        
        System.out.println("Shortest path to finish is " + maze[maze.length-1][maze[maze.length-1].length-1]);
    }
                
                
    public static void initMaze(int size){
       
        maze = new String[size][size];

        for(int r = 0; r < maze.length; r++){
            for(int c = 0; c < maze[r].length; c++){

                maze[r][c] = ".";
                
            }
        }

        maze[0][0] = "S";
        maze[maze.length-1][maze[maze.length-1].length-1] = "E";
    }

    public static void recursiveMazeSearch(int row, int col, int scoredStepsFromEnd){
        System.out.println(shortest_Distance);
        System.out.printf("Position: %d, %d%n", row, col);
        PrintDelay.printMatrixWithDelay(maze, 10);
        
    
        if(row < 0 || row >= maze.length || col < 0 || col >= maze[row].length){
            return;
        }

        String currentValue =  maze[row][col].equals("S") ? Integer.toString(0) : maze[row][col];

        if(scoredStepsFromEnd >= shortest_Distance){  //pruning paths that are longer than shortest path already found.
            return;
        }

        

        if(currentValue.equals("E")){
            if(scoredStepsFromEnd < shortest_Distance){
                shortest_Distance = scoredStepsFromEnd;
            }
            return;
        }
        
        if(currentValue.equals("#")){

            return;
        }

        if(!currentValue.equals(".") && Integer.parseInt(currentValue) < scoredStepsFromEnd){
            return;
        }

        maze[row][col] = Integer.toString(scoredStepsFromEnd);

            recursiveMazeSearch(row+1, col, scoredStepsFromEnd+1);
            recursiveMazeSearch(row, col+1, scoredStepsFromEnd+1);
            recursiveMazeSearch(row-1, col, scoredStepsFromEnd+1);
            recursiveMazeSearch(row, col-1, scoredStepsFromEnd+1);
                
        }

    public static void printMaze(){

        System.out.println();
        System.out.println();
         // You can adjust this to your needs, based on the length of the longest element

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
}