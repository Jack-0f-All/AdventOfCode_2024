package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day6 {

    static String[][] mapOfPositions; //= new String[10][];
    static int row = -1;
    static int col = -1;
    static int loopCount = 0;
    static boolean  UPDATE = false;
    static final int DELAY = 10;
    static final int LOOP_THRESHHOLD = 5;
        public static void main(String[] args) {
            try {
                    // Create a File object to read from "input.txt"
        
                    File myObj = new File("./src/Day6/input.txt"); 
                    //File myObj = new File("./src/Day6/sampleData.txt");
        
                    Scanner myReader = new Scanner(myObj);
                    int countOfLinesInFile=0;
                    while(myReader.hasNextLine()) {
                        myReader.nextLine();
                        countOfLinesInFile++;
                    }
                    myReader.close();
                    myReader = new Scanner(myObj); //resets the reader to the beginning of the file.
                    mapOfPositions = new String[countOfLinesInFile][];
                   
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

            for(int r=0; r<=mapOfPositions.length-1; r++){
                for(int c=0; c<=mapOfPositions[r].length-1; c++){
                    
                    if(mapOfPositions[r][c].equals("^")){
                        row = r;
                        col = c;
                        break;
                    }
                }

                if(row>-1){
                    break;
                }
            
            }

            

            //moveGuard(-1, 0);


            System.out.println(findLoops());
            //System.out.println(countGuardPositions());
            //countGuardPositions()
        }

        public static void moveGuard(int dX, int dY){
            
            
            //System.out.printf("[%d, %d]%n",row,col);
            mapOfPositions[row][col] = "X";
            
            if(UPDATE){
                updateMapDisplay(mapOfPositions);

            }

            //if((row+dX >= 0 && row+dX<=9) && (col+dY >= 0 && col+dY < mapOfPositions[row].length-1)){
            if(row+dX < 0 || row+dX>mapOfPositions.length -1)
            {
                //System.out.printf("Tried to step off: %d,%d%n",row,col);
                
                return;
            }
            else if(col+dY < 0 || col+dY > mapOfPositions[row+dX].length-1)
                {
                    return;
                }else if(!mapOfPositions[row+dX][col+dY].equals("#")){
                    
                    row += dX;
                    col += dY;
                    moveGuard(dX, dY);

                }else{

                    if(dX == -1 && dY == 0){//moving up
                        dX=0;
                        dY=1;
                        moveGuard(dX, dY);
                    }else if(dX == 0 && dY == 1){//moving right
                            dX=1;
                            dY=0;
                            moveGuard(dX, dY);
                    }else if(dX == 1 && dY == 0){//moving down
                        dX=0;
                        dY=-1;
                        moveGuard(dX, dY);
                    }else if(dX == 0 && dY == -1){
                        dX=-1;
                        dY=0;
                        moveGuard(dX, dY);
                    }
                }
            }

        public static void updateMapDisplay(String[][] grid){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println();
            for(String[] row : grid){
                System.out.println(Arrays.toString(row));
            }

            try {
                // Delay for 2 seconds (2000 milliseconds)
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                System.out.println("The sleep was interrupted.");
            }
        }


        public static int countGuardPositions(){
            int count = 0;
            for(int r=0; r<=mapOfPositions.length-1; r++){
                for(int c=0; c<=mapOfPositions[r].length-1; c++){
                    
                    if(mapOfPositions[r][c].equals("X")){
                        count++;
                    }
                }
            }

            return count;
        }

        public static int findLoops(){
            
            System.out.println("Finding loops...");
            loopCount=0;
            for(int r = 0; r< mapOfPositions.length; r++){
                for(int c = 0; c < mapOfPositions[r].length; c++){
                    if(mapOfPositions[r][c].equals(".")){
                        mapOfPositions[r][c]="0";
                        loopCount+=checkForLoop(mapOfPositions)?1:0;
                        //checkForLoop(mapOfPositions);
                        mapOfPositions[r][c] = ".";
                    }
                }
            }

            return loopCount;
        }

        public static boolean checkForLoop(String[][] map){

            int ROW = row;
            int COL = col;
            int dX=-1;
            int dY=0;
            String[][] obstacleIteration = deepCopy(map);
            String marker = "|";

            HashMap<String,Integer> guardLocations = new HashMap<>();

            while(true){

                String key = ROW+","+COL;
                if(!guardLocations.containsKey(key)){
                    guardLocations.put(key, 1);
                }else{
                   guardLocations.put(key, guardLocations.get(key)+1);
                   if(guardLocations.get(key)>LOOP_THRESHHOLD){
                    return true;
                   }
                }

            
                if(obstacleIteration[ROW][COL].equals("|") && marker.equals("-")){
                    obstacleIteration[ROW][COL] = "+";

                }else if(obstacleIteration[ROW][COL].equals("-") && marker.equals("|")){
                    obstacleIteration[ROW][COL] = "+";

                }else{

                    obstacleIteration[ROW][COL] = marker;
                }
                
                if(UPDATE)
                updateMapDisplay(obstacleIteration);

                if(ROW+dX < 0 || ROW+dX>obstacleIteration.length -1)
                {
                    
                    return false;
                }
                else if(COL+dY < 0 || COL+dY > obstacleIteration[ROW+dX].length-1)
                    {
                        return false;

                    }else if(!obstacleIteration[ROW+dX][COL+dY].equals("#")&&!obstacleIteration[ROW+dX][COL+dY].equals("0")){
                        
                        ROW += dX;
                        COL += dY;
                        

                    }else{

                        if(dX == -1 && dY == 0){//if moving up, change to moving right
                            dX=0;
                            dY=1;
                            marker = "-";
                            
                        }else if(dX == 0 && dY == 1){//moving right, change to down
                                dX=1;
                                dY=0;
                                marker = "|";
                                
                        }else if(dX == 1 && dY == 0){//moving down, change to left
                            dX=0;
                            dY=-1;
                            marker = "-";

                        }else if(dX == 0 && dY == -1){//moving left, change to up
                            dX=-1;
                            dY=0;
                            marker = "|";

                        }
                    }
            }
        }















        public static String[][] deepCopy(String[][] original) {
            if (original == null) {
                return null; // Handle null arrays gracefully
            }
        
            // Create a new outer array with the same length as the original
            String[][] copy = new String[original.length][];
        
            // Copy each sub-array
            for (int i = 0; i < original.length; i++) {
                if (original[i] != null) {
                    // Create a new sub-array and copy the elements
                    copy[i] = new String[original[i].length];
                    System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
                } else {
                    // Handle null sub-arrays
                    copy[i] = null;
                }
            }
        
            return copy;
        }
        
        
    }
