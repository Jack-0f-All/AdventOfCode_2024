package Day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day10 {
    static int[][] terrainMap;
    static int totalScore;
    static int totalRating;
    static int totalSum = 0;
    static HashMap <String, Integer> trailHeadScores = new HashMap<>();
        public static void main(String[] args) {
            try {
                
                int size=0;
    
                //File myObj = new File("./src/Day10/input.txt"); 
                File myObj = new File("./src/Day10/sampleData.txt");
                //File myObj = new File("./src/Day10/solved1.txt");
    
                Scanner myReader = new Scanner(myObj);
                int countOfLinesInFile=0;
                
                while(myReader.hasNextLine()) {
                    size = myReader.nextLine().split("").length;
                    countOfLinesInFile++;
                }
                myReader.close();
                myReader = new Scanner(myObj); //resets the reader to the beginning of the file.
                terrainMap = new int[countOfLinesInFile][size];
               
                int lineCount = 0;
                // Read each line from the file
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();  // Read the entire line
                    
                    int index = 0;
                    for(String num: data.split("")){
                        
                        terrainMap[lineCount][index++] = Integer.parseInt(num); //Splits each line into individual characters and then loads into crossword.
                    }
                    lineCount++;
                    
            
            }
        myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("Can't find file by that name or in that location.");
            e.printStackTrace();
        }

        findTrailHeads();
        System.out.println("Total score for trails: "+totalScore);
        System.out.println("Total rating for trails: "+totalRating);
        System.out.println(totalSum);



       


        //System.out.println(Arrays.toString(terrainMap[0]));
    }

    public static void findTrailHeads(){

        for(int r=0; r< terrainMap.length; r++){
            for(int c=0; c< terrainMap[r].length;c++){
                if(terrainMap[r][c] == 0);
                //System.out.printf("Scoring trail %d, %d: %n", r,c);
                    trailHeadScores.clear();
                    scoreTrail(r,c,-1);
                    totalScore += trailHeadScores.size();
                    totalRating += rateTrail(r,c,-1);
                    sumMap();
                    //System.out.println(scoreTrail(r,c,0));
            }
        }
        
    }

    public static void scoreTrail(int row, int col, int previousValue){
                   
            if(row<0 || row>terrainMap.length-1){
                //off the map;
                return ;
            }
            if(col<0 || col>terrainMap[row].length-1){
                //off the map;
                return ;
            }
            int currentValue = terrainMap[row][col];

            if(previousValue+1 == currentValue){

            
                if(currentValue == 9){
                    
                    String endPosition = row +","+col;
                    if(trailHeadScores.containsKey(endPosition))
                    {
                        trailHeadScores.put(endPosition,trailHeadScores.get(endPosition)+1);
                    }else{
                        trailHeadScores.put(endPosition,1);
                    }
                    return;
                }

            
                scoreTrail(row+1, col, currentValue);
                scoreTrail(row-1, col, currentValue);
                scoreTrail(row, col+1, currentValue);
                scoreTrail(row, col-1, currentValue);
            
            }
                return ;
            
            
    }

    public static int rateTrail(int row, int col, int previousValue){
            
        int trailRating = 0;
        if(row<0 || row>terrainMap.length-1){
            //off the map;
            return 0;
        }
        if(col<0 || col>terrainMap[row].length-1){
            //off the map;
            return 0;
        }
        int currentValue = terrainMap[row][col];

        if(previousValue+1 == currentValue){

        
            if(currentValue == 9){
                return 1;
            }

        
            trailRating += rateTrail(row+1, col, currentValue);
            trailRating += rateTrail(row-1, col, currentValue);
            trailRating += rateTrail(row, col+1, currentValue);
            trailRating += rateTrail(row, col-1, currentValue);
        
        }
            return trailRating;
        
        
    }
    public static void sumMap(){
        
        
        for(String key: trailHeadScores.keySet()){

            totalSum+= trailHeadScores.get(key);
        }

        
    }
}
