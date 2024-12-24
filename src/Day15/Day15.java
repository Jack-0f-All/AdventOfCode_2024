package Day15;

import library.PrintDelay;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Day15 {

    static String[][] matrix;
    static String[][] wideMatrix;
    static Queue<String> movements = new LinkedList<>();
    static int bot_Row=-1;
    static int bot_Col=-1;
    static int count = 0;
    static boolean SLOW_PRINT = true;

    public static void main(String[] args) {
            
        try {

            //File file = new File("./src/Day15/input.txt"); //1533489 is to high   1487196 is to low.
            File file = new File("./src/Day15/sample8.txt");
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
            wideMatrix = new String[lineCount][];

            myScanner = new Scanner(file);
            int inputLine=0;
            while(myScanner.hasNextLine()){
                String data = myScanner.nextLine();

                if(data.equals("")){
                    break;
                }

                matrix[inputLine++] = data.split(""); 
            }

            createWideMatrix();

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

            
            
            findStartPosition(wideMatrix);
            printMatrix(wideMatrix);
            while (!movements.isEmpty()) {

                String direction = movements.poll();
                //checkAndMove(bot_Row, bot_Col, direction);
                checkAndMoveWide(bot_Row, bot_Col, direction);

                if(SLOW_PRINT){ 
                    PrintDelay.printMatrixWithDelay(wideMatrix, 1500);
                    System.out.println();
                }
            }
            printMatrix(wideMatrix);
            //System.out.println("\nTotal GPS score: " + calcGPS(matrix, "O") + "\n");
            System.out.println("\nTotal GPS score: " + calcGPS(wideMatrix, "[") + "\n");
        }
    }
                
                
                
    public static boolean checkAndMove(int rowPos, int colPos, String orientation){

        
        int deltaRow = 0;
        int deltaCol = 0;
        String token = matrix[rowPos][colPos];
        switch(orientation){
    
            case "^" : deltaRow = -1; deltaCol =  0; break;
            case ">" : deltaRow =  0; deltaCol =  1; break;
            case "v" : deltaRow =  1; deltaCol =  0; break;
            case "<" : deltaRow =  0; deltaCol = -1; break;
            default : throw new IllegalArgumentException("Invalid orientatinon " + orientation);
        }

        
        if(token.equals("#")){
            return false;
        }
        if(token.equals(".")){
            return true;
        }

        if(token.equals("O") || token.equals("@")){
            

            if(checkAndMove(rowPos + deltaRow, colPos + deltaCol, orientation)){
                move(rowPos, colPos, deltaRow, deltaCol);
                return true;
            }
        }            


        return false;
    }

    public static boolean checkAndMoveWide(int rowPos, int colPos, String orientation){

        int newRow = 0;
        int newCol = 0;
        
        switch(orientation){
    
            case "^" : newRow = rowPos + -1; newCol =  colPos + 0; break;
            case ">" : newRow =  rowPos + 0; newCol =  colPos + 1; break;
            case "v" : newRow =  rowPos + 1; newCol =  colPos + 0; break;
            case "<" : newRow =  rowPos + 0; newCol = colPos + -1; break;
            default : throw new IllegalArgumentException("Invalid orientatinon " + orientation);
        }

        String token = wideMatrix[rowPos][colPos];

        if(token.equals("#")){
            return false;
        }

        if(token.equals(".")){
            return true;
        }

        if(token.equals("@")){
            if(checkAndMoveWide(newRow, newCol, orientation)){
                moveWide(rowPos, colPos, newRow, newCol);
                return true;
            }
        }

        if(token.equals("[")){

            if(orientation.equals("v") || orientation.equals("^")){

                if(checkAndMoveWide(newRow, newCol+1, orientation)){
                    if(checkAndMoveWide(newRow, newCol, orientation)){

                        moveWide(rowPos, colPos, newRow, newCol);
                        moveWide(rowPos, colPos + 1, newRow, newCol+1);
                        return true;
                    }
                    
                }

            } else{
                if(checkAndMoveWide(newRow, newCol, orientation)){
                    moveWide(rowPos, colPos, newRow, newCol);
                    return true;
                }
            }
        }

        if(token.equals("]")){

            if(orientation.equals("v") || orientation.equals("^")){

                if(checkAndMoveWide(newRow, newCol-1, orientation)){

                    if(checkAndMoveWide(newRow, newCol, orientation)){

                        moveWide(rowPos, colPos, newRow, newCol);
                        moveWide(rowPos, colPos - 1, newRow, newCol-1);
                        return true;
                    }
                    
                }
            } else{
                if(checkAndMoveWide(newRow, newCol, orientation)){
                    moveWide(rowPos, colPos, newRow, newCol);
                    return true;
                }
            }
        }

        return false;

    }

    public static void moveWide(int rowPos, int colPos, int newRow, int newCol){

        String token = wideMatrix[rowPos][colPos];

        if(!token.equals("#")){

            wideMatrix[rowPos][colPos] = ".";
            wideMatrix[newRow][newCol] = token;
        }

        if(token.equals("@")){
            bot_Row = newRow;
            bot_Col = newCol;
        }

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


    public static long calcGPS(String[][] m, String token){

        long totalGPS_score = 0;
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[r].length; c++) {
                if(m[r][c].equals(token)){
                    totalGPS_score += (100 * r) + c;
                }
            }
        }

        return totalGPS_score;
    }



    public static void findStartPosition(String[][] m){

        for(int r = 0; r<m.length; r++){
            for(int c = 0; c<m[r].length; c++){
                if(m[r][c].equals("@")){
                    bot_Row = r;
                    bot_Col = c;
                    return;
                }
            }

        }
    }

    public static void createWideMatrix(){
        
            
        for (int r = 0; r < matrix.length; r++) {
            
            ArrayList <String> wideRows = new ArrayList<>();
            for(String s : matrix[r]){

                if(s.equals("@")){
                    wideRows.add(s);
                    wideRows.add(".");
                }else if(s.equals("O")){
                    wideRows.add("[");
                    wideRows.add("]");
                }else{
                    wideRows.add(s);
                    wideRows.add(s);
                }
            }

            wideMatrix[r] = wideRows.toArray(new String[0]);
        }
    }

    public static void printMatrix(String[][] m){

        for (String[] row : m) {
            System.out.println(Arrays.toString(row));
        }
    }
}
