package Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6 {

    static String[][] mapOfPositions = new String[130][];
    static int row = -1;
    static int col = -1;
    static int loopCount = 0;
    static final int DELAY = 100;
        public static void main(String[] args) {
            try {
                    // Create a File object to read from "input.txt"
        
                    File myObj = new File("./src/Day6/input.txt"); 
                    //File myObj = new File("./src/Day6/sampleData.txt");
        
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

            //System.out.printf("Starting position is [%d, %d]%n", row, col);

            moveGuard(-1, 0);
            // for(String[] row : mapOfPositions){
            //     System.out.println(Arrays.toString(row));
            // }
            System.out.println(countGuardPositions());
            //countGuardPositions()
        }

        public static void moveGuard(int dX, int dY){
            
            //updateMapDisplay();

            //System.out.printf("[%d, %d]%n",row,col);
            mapOfPositions[row][col] = "X";

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

        



        public static void updateMapDisplay(){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println();
            for(String[] row : mapOfPositions){
                System.out.println(Arrays.toString(row));
            }

            System.out.printf("[%d, %d]%n",row,col);


            

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
    }
