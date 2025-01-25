package Day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import library.PrintDelay;

public class Day18 {

    static final int NUMBER_OF_BYTES = 3450;
    static int shortest_Distance = Integer.MAX_VALUE;
    static int count = 0;
    static String[][] maze;
    static int columnWidth = 2;
    static final int[][] DIRECTIONS = {{-1,0}, {1, 0} , {0, -1}, {0, 1}};
    static int[] startPos = {0,0};
    static int[] endPos = {70,70};

    public static void main(String[] args) {
        try {
            
            //File myFile = new File("./src/Day18/sample.txt");
            File myFile = new File("./src/Day18/input.txt");

            Scanner myReader = new Scanner (myFile);

            initMaze(71);
            findStartEndPositions();
            while(myReader.hasNextLine()){
                shortest_Distance = Integer.MAX_VALUE;
                String[] line = myReader.nextLine().split(",");
                maze[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = "#";
                numberStepsToEnd();
                if(shortest_Distance == Integer.MAX_VALUE){
                    System.out.println(Arrays.toString(line) + " blocked all paths");
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
           System.out.println("Cannot find that file...");
        }
            
       
        // System.out.println("Numbering steps...");
        // numberStepsToEnd();
        // PrintDelay.printMatrixWithDelay(maze, 1);
        // System.out.println("Shortest path to finish is " + shortest_Distance);
    }
                
                
    public static void initMaze(int size){
       
        maze = new String[size][size];

        for(int r = 0; r < maze.length; r++){
            for(int c = 0; c < maze[r].length; c++){

                maze[r][c] = ".";
                
            }
        }

    }

public static void numberStepsToEnd() {
    // BFS initialization
    Queue<int[]> queue = new LinkedList<>();
    boolean[][] visited = new boolean[maze.length][maze[0].length];  // Visited cells to avoid revisiting
    int[] start = startPos;  // Start position
    queue.add(start);
    visited[start[0]][start[1]] = true;

    // Directions: up, down, left, right
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    // Variable to track steps
    int steps = 0;

    // BFS loop
    while (!queue.isEmpty()) {
        int levelSize = queue.size();  // Number of nodes at the current level (same number of steps)

        for (int i = 0; i < levelSize; i++) {
            int[] currentPos = queue.poll();
            int x = currentPos[0];
            int y = currentPos[1];

            // If we have reached the end position, return the result
            if (x == endPos[0] && y == endPos[1]) {
                shortest_Distance = steps;
                //printMaze();
                return;  // End the function early, as we found the shortest path
            }

            // Explore all 4 possible directions
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];

                // Skip if out of bounds or already visited
                if (newX < 0 || newX >= maze.length || newY < 0 || newY >= maze[0].length || visited[newX][newY]) {
                    continue;
                }

                if(maze[newX][newY].equals("#")){
                    continue;
                }

                // Mark the cell as visited and add it to the queue
                visited[newX][newY] = true;
                queue.add(new int[] {newX, newY});

                // Update the number of steps taken at this position
                maze[newX][newY] = Integer.toString(steps + 1);
            }
        }
        // Increase the step count after processing all nodes at the current level
        steps++;
    }
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
        System.out.println();
    }


    public static void shortcut(int xPos, int yPos, int numSteps) {

        // Base case: check if out of bounds or already visited with fewer steps
        if (xPos < 0 || xPos >= maze.length || yPos < 0 || yPos >= maze[0].length) {
            return;
        }
    
        // If this position already has a smaller number of steps, no need to continue
        if (maze[xPos][yPos].equals(".")  || maze[xPos][yPos].equals("#") || Integer.parseInt(maze[xPos][yPos]) <= numSteps) {
            return;
        }
    
        // If we found a shorter path to this cell, update the number of steps
        maze[xPos][yPos] = Integer.toString(numSteps);
    
        // If we've reached the end position, potentially update the shortest distance
        if (xPos == maze.length - 1 && yPos == maze[0].length - 1) {
            if (shortest_Distance > numSteps) {
                shortest_Distance = numSteps;
                maze[xPos][yPos] = Integer.toString(shortest_Distance);
            }
            printMaze();  // Optional: you may want to print once a shorter path is found
            return;
        }
    
        // Recurse in all four directions with incremented step count
        shortcut(xPos + 1, yPos, numSteps + 1);
        shortcut(xPos - 1, yPos, numSteps + 1);
        shortcut(xPos, yPos + 1, numSteps + 1);
        shortcut(xPos, yPos - 1, numSteps + 1);
    }
    
    public static void printMaze(boolean[][] maze){

        System.out.println();
        System.out.println();
         // You can adjust this to your needs, based on the length of the longest element

        // Loop through the array and print each element with padding
        for (boolean[] row : maze) {
                System.out.println(Arrays.toString(row));
               
        }
        System.out.println();
        System.out.println();
    }

    public static void findStartEndPositions(){

        for(int r=0; r < maze.length; r++){

            for(int c = 0; c < maze[r].length; c++){

                if(maze[r][c].equals("S")){
                    startPos = new int[] {r, c};
                    maze[r][c] = ".";
                }

                if(maze[r][c].equals("E")){
                    endPos = new int[] {r, c};
                    maze[r][c] = ".";
                }
            }
        }
    }
}