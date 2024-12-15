package Day14;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

    static ArrayList<bathroomDrone> drones = new ArrayList<>();
    static int totalSteps = 10000;
    static int colSize = 101;
    static int rowSize = 103;
    static String[][] grid = new String [rowSize][colSize];
    static boolean displayAxis = false;
    static int DELAY = 0;

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

                    drones.add(new bathroomDrone(xPos, yPos, deltaX, deltaY, rowSize, colSize));
                }
            }
            myReader.close();
            
            
        } catch (FileNotFoundException e) {
            System.out.println("No file by that name. Check that name and/or path is correct.");
        }

        
        move();
        updateGrid();
        //treeSearch();
 
        System.out.println(countQuadrants());
    }

    public static void move(){
        int count = 1;

        while(count <= totalSteps){
            stepAtATime();
            count++;
        }

    }

    public static void treeSearch(){
        int count = 1;
        int tree = 0;
        int lowestScore = Integer.MAX_VALUE;


        while(count <= totalSteps){
            stepAtATime();
            try {
                
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.out.println("The sleep was interrupted.");
            }
                int score = countQuadrants();

                //Wrote this function to find the lowest danger score out of all the drone positions.
                //Then, rewrote the function to print to file when danger score equals that lowest score.
                if(score == 112847865){ 
                    writeGridToFile(count);
                }
                
                
                count++;
            }
            System.out.println("Lowest score "+ lowestScore);

    }
    public static void stepAtATime(){


            for (bathroomDrone d : drones) {
                d.moveStep();

            }

            updateGrid();

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

        return q1 * q2 * q3 * q4; //returns the "safety score" for the current position of the drones.
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

            grid[d.getRowPos()][d.getColPos()] = "#";
        }
    }

    public static void printGrid(){
        int x_axis = displayAxis ? grid.length/2 : -1;
        int y_axis = displayAxis ? grid[0].length/2 : -1;


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
    public static void writeGridToFile(int count){
        String output = "";


        for(int r = 0; r<grid.length; r++){
            for(int c = 0; c<grid[r].length; c++){

            
                    output+= grid[r][c]+" ";
                }
                output+="\n";
            }

        String filePath = "./src/Day14/Trees/Tree_"+ count+".txt";
            
            // Create a File object
            File file = new File(filePath);
            
            // Create and write to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(output);
                
            } catch (IOException e) {
                e.printStackTrace();
            }


                
        }
    }
