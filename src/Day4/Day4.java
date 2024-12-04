package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day4 {

    final static String XMAS="XMAS";
    static String[][] crossword = new String[140][];
    static int totalCount = 0;
        public static void main(String[] args) {
        
        
                
        
                try {
                    // Create a File object to read from "input.txt"
        
                    File myObj = new File("./src/Day4/input.txt"); // correct answer is 18 instances of XMAS
                    //File myObj = new File("./src/Day4/sampleData.txt");
        
                    Scanner myReader = new Scanner(myObj);
        
                    
                    int lineCount = 0;
                    // Read each line from the file
                    while (myReader.hasNextLine()) {
                        Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
                        String line = data.nextLine();
                        crossword[lineCount++] = line.split("");
                        //System.out.println("Added " + (lineCount-1));
                        data.close();
                    }
                    myReader.close();
        
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
        
                for(int row=0; row<crossword.length; row++){
                    for(int col=0; col<crossword[row].length; col++){
                        if(crossword[row][col].equals("X")){
                            crosswordSearch(row, col);
                        }
                    }
                }

                System.out.println(totalCount);
            }
        
        
        public static void crosswordSearch(int row, int col){
        
            
            /* */
        
            //check forward
        
            String check = "X";
            
            for(int i=1; i<=3; i++){
                if(col+i>crossword[row].length-1)
                    break;
                check+= crossword[row][col+i];
            }
            if(check.equals(XMAS)){
                totalCount++;
            }
    
            //check backwards
            check = "X";
            
            for(int i=1; i<=3; i++){
                if(col-i<0)
                    break;
                check+= crossword[row][col-i];
            }
            if(check.equals(XMAS)){
                totalCount++;
            }


            //check up
            check = "X";
            
            for(int i=1; i<=3; i++){
                if(row-i<0){
                    break;
                }
                
                check+= crossword[row-i][col];
            }
            if(check.equals(XMAS)){
               totalCount++;
            }
            //check down
            check = "X";
            for(int i=1; i<=3; i++){
                if(row+i>crossword.length-1)
                    break;
                check+= crossword[row+i][col];
            }
            if(check.equals(XMAS)){
                totalCount++;
            }

            //check diagnal -> down and right
            check = "X";
            for(int i=1; i<=3; i++){
                if(row+i>crossword.length-1 || col+i>crossword[row].length-1)
                    break;
                check+= crossword[row+i][col+i];
            }
            if(check.equals(XMAS)){
                totalCount++;       
            }

            //check diagnal -> down and left
            check = "X";
            for(int i=1; i<=3; i++){
                if(row+i>crossword.length-1 || col-i<0)
                    break;
                check+= crossword[row+i][col-i];
                
            }
            if(check.equals(XMAS)){
               totalCount++; 
            }

            //check diagnal -> up and right
            check = "X";
            for(int i=1; i<=3; i++){
                if(row-i<0|| col+i>crossword[row].length-1)
                    break;
                check+= crossword[row-i][col+i];
                
            }
            if(check.equals(XMAS)){
                totalCount++;      
            }

            //check diagnal -> up and left
            check = "X"; 
            for(int i=1; i<=3; i++){
                if(row-i<0|| col-i<0)
                    break;
                check+= crossword[row-i][col-i];
                
            }
            if(check.equals(XMAS)){
                totalCount++;
                
            }

            
            
}
}
