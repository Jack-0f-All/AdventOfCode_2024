package Day15;

import library.PrintDelay;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Day15 {

    static String[][] matrix;
    static Queue<String> movements = new LinkedList<>();
    static int bot_Row=-1;
    static int bot_Col=-1;

    public static void main(String[] args) {
            
        try {

            //File file = new File("./src/Day15/input.txt");
            File file = new File("./src/Day15/sample.txt");
            Scanner myScanner = new Scanner(file);
            int lineCount = 0;
            while(myScanner.hasNextLine()){

                String line = myScanner.nextLine();
                
                if(line.equals("")){
                    break;
                }
                lineCount++;
            }
            myScanner.close();

            matrix = new String[lineCount][];

            myScanner = new Scanner(file);
            int inputLine=0;
            while(myScanner.hasNextLine()){
                String data = myScanner.nextLine();

                if(data.equals("")){
                    break;
                }

                matrix[inputLine++] = data.split(""); 
            }

            while (myScanner.hasNextLine()) {
                String data = myScanner.nextLine();

                for(String s : data.split("")){
                    movements.add(s);
                }
            }

            


            myScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file...");
        }finally{

            
            
            findStartPosition();
            int loops = 2;
            while (loops > 0) {

                
                checkAndMove(bot_Row, bot_Col, movements.poll());
                //PrintDelay.printMatrixWithDelay(matrix, 500);

                loops--;
            }
            printMatrix();
        }
    }
                
                
                
    public static boolean checkAndMove(int rowPos, int colPos, String orientation){

        int deltaRow = 0;
        int deltaCol = 0;
        String token = matrix[rowPos][colPos];
        switch(orientation){
    
            case "^" : deltaRow = -1; deltaCol =  0; break;
            case ">" : deltaRow =  0; deltaCol =  1; break;
            case "V" : deltaRow =  1; deltaCol =  0; break;
            case "<" : deltaRow =  0; deltaCol = -1; break;
        }

        System.out.printf("Checking %s moving %s",token, orientation);
        if(token.equals("#")){
            return false;
        }
        if(token.equals(".")){
            return true;
        }

        if(token.equals("O") || token.equals("@")){
            
            System.out.println("Checking if can move...");
            //if(checkAndMove(rowPos + deltaRow, colPos + deltaCol, orientation)){
                //move(rowPos, colPos, deltaRow, deltaCol);
                //System.out.println("Can move" + orientation);
                //return true;
            //}
        }
                
                
        return false;
                    
                    
    }
                    
    public static void move(int rowPos, int colPos, int dRow, int dCol){
    
        
        String token = matrix[rowPos][colPos];

        

        if(!token.equals("#")){

            matrix[rowPos][colPos] = ".";

            matrix[rowPos+dRow][colPos+dCol] = token;
        }

        if(token.equals("@")){
            bot_Row = rowPos+dRow;
            bot_Col = colPos+dCol;
        }
    
    }

    public static void findStartPosition(){

        for(int r = 0; r<matrix.length; r++){
            for(int c = 0; c<matrix.length; c++){
                if(matrix[r][c].equals("@")){
                    bot_Row = r;
                    bot_Col = c;
                    return;
                }
            }

        }
    }



    public static void printMatrix(){

        for (String[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }


    public static void loadObjects(){


        for(int row=0; row < matrix.length; row++){

            for(int col=0; col < matrix[row].length; col++){

                switch(matrix[row][col]){
                    
                    
                    case "." :

                    case "#" : break;
                    
                    case "O" : new Box(row, col); break;
                    
                    case "@" : new Robot(row, col); break;
                }
            }
        }

    }

}
