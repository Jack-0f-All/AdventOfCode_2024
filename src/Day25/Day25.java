package Day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Day25 {
    
    final static String[] BYTE_LOCK_CONVERSION = {"00000", "00001", "00011", "00111", "01111", "11111"};
    final static String[] BYTE_KEY_CONVERSION =  {"00000", "10000", "11000", "11100", "11110", "11111"};

    static HashSet<String> allLocks = new HashSet<>();
    static HashSet<String> allKeys = new HashSet<>();
    static ArrayList<String> lockList = new ArrayList<>(250);
    static ArrayList<String> keyList = new ArrayList<>(250);
    static int numOfMatches = 0;
    public static void main(String[] args) {
        

        loadFile("input.txt");
        

        for(String lock: lockList){
            for(String key: keyList){
                        
                Integer mask = Integer.parseUnsignedInt(lock,2) & Integer.parseUnsignedInt(key,2);

                if(mask == 0){
                    numOfMatches++;
                    System.out.println(lock);
                    System.out.println(key);
                    System.out.println();
                    
                }
            }
        }

        System.out.println(numOfMatches);


    }



    public static void loadFile(String fileName){
        
        try {

            File myFile = new File("src/Day25/" + fileName);
            Scanner file = new Scanner(myFile);

            String line;

            while(file.hasNextLine()){
                line = file.next();
                
                int[] keyOrLockValues = {0,0,0,0,0};
                boolean isLock =  line.equals("#####") ? true : false;

                file.nextLine();
                
                int lineCount = 0;
                while(lineCount < 5){
                    line = file.nextLine();
                    
                    String[] row = line.split("");
                    
                    for(int i=0; i<row.length; i++){
                        if(row[i].equals("#")){
                            
                             keyOrLockValues[i] += 1;
                            
                            
                        }
                    }

                    lineCount++;

                }
            
            String keyOrLockString = "";
            for(int i:keyOrLockValues){
                keyOrLockString += isLock ? BYTE_LOCK_CONVERSION[i] : BYTE_KEY_CONVERSION[i];
            }

            

            if(isLock){
                allLocks.add(keyOrLockString);
                //lockList.add(Integer.parseUnsignedInt(keyOrLockString, 2));
                lockList.add(keyOrLockString);

            }else{

                allKeys.add(keyOrLockString);
                //keyList.add(Integer.parseUnsignedInt(keyOrLockString, 2));
                keyList.add(keyOrLockString);
            }


            line = file.nextLine();
           
            }
            
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find a file by that name...");
            System.out.println(e);
        }

    }
}
