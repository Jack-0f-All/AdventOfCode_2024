package Day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

    static ArrayList<bathroomDrone> drones = new ArrayList<>();
    static int totalSteps = 100;
    static int xSize = 101;
    static int ySize = 103;
    static String[][] grid = new String [xSize][ySize];

    public static void main(String[] args) {
        
        clearGrid();
        
        
        try {

            File fileObj = new File("./src/Day14/input.txt");
            //File fileObj = new File("./src/Day14/sampleData.txt");
            //File fileObj = new File("./src/Day14/singleDrone.txt");

            Scanner myReader = new Scanner(fileObj);

            while(myReader.hasNext()){
                String data = myReader.nextLine();
                Pattern pattern = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");
                Matcher matcher = pattern.matcher(data);

                if(matcher.find()){
                    int xPos = Integer.parseInt(matcher.group(1));
                    int yPos = Integer.parseInt(matcher.group(2));
                    int deltaX = Integer.parseInt(matcher.group(3));
                    int deltaY = Integer.parseInt(matcher.group(4));

                    drones.add(new bathroomDrone(xPos, yPos, deltaX, deltaY, xSize, ySize));
                }
            }
            myReader.close();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("No file by that name. Check that name and/or path is correct.");
        }

        // updateGrid();
        // printGrid();
        // stepAtATime();
        // printGrid();
        move();
        System.out.println(countQuadrants());
    }

    public static void move(){
        int count = 1;

        while(count <= totalSteps){
            stepAtATime();
            count++;
        }

    }
    public static void stepAtATime(){

        // while(count < totalSteps){
            for (bathroomDrone d : drones) {
                d.moveStep();

            }

            updateGrid();
            //printGrid();
        }

    

    public static int countQuadrants(){

        int q1 = 0;
        int q2 = 0; 
        int q3 = 0; 
        int q4 = 0;

        for(bathroomDrone d : drones){
           switch (d.getQuadrant()) {
            case "I": q1++; break;
            case "II": q2++; break;
            case "III": q3++; break;
            case "IV": q4++; break;
            
            default: break;

           }
        }
        // System.out.println(q1);
        // System.out.println(q2);
        // System.out.println(q3);
        // System.out.println(q4);
        return q1 * q2 * q3 * q4;
    }



    public static void clearGrid(){

        for(int r = 0; r< grid.length; r++){
            for(int c=0; c<grid[r].length; c++){
                grid[r][c] = ".";
            }
        }
    }

    public static void updateGrid(){
        clearGrid();
        for (bathroomDrone d : drones) {
            int x = d.getXPos();
            int y = d.getYPos();
            grid[x][y] = "X";
        }
    }

    public static void printGrid(){
        int x_axis = grid.length/2;
        int y_axis = grid[0].length/2;
        
        // for(String[] row : grid){
        //     System.out.println(Arrays.toString(row));
        // }

        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[r].length; c++){

                if(r == x_axis || c == y_axis){
                    System.out.print(" ");
                }else{
                    System.out.print(grid[r][c]+" ");
                }
            }

            System.out.println();
                
        }
    }
}