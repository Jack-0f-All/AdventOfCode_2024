package Day11;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;

public class Day11 {

    static ArrayList <Long> stones = new ArrayList<>(8*75);
    static ArrayList <Long> final_stones = new ArrayList<>();
    static final int NUMBER_OF_BLINKS = 25;
    static HashMap <Long, Long> stoneEndCount = new HashMap<>();
    static long recursiveCalls=0;
    public static void main(String[] args) {
        try {

            File myFile = new File("./src/Day11/input.txt");
            //File myFile = new File("./src/Day11/sample2.txt");

            Scanner myReader = new Scanner(myFile);

            System.out.println("Loading data...\n");

            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                loadList(data);
            }
                
            myReader. close();
                            
            } catch (FileNotFoundException e) {
                System.out.println("Can't find file by that name...");
            }finally{


                //otherBlink();
                blinkStones();
                //System.out.println(stones.toString());
                System.out.println("Total number of stones after blinking: "+stones.size());
                //System.out.println("Total number of stones after blinking: "+final_stones.size());
                
            }
    }
                
    private static void loadList(String data) {
       
        for(String num:data.split(" ")){
            stones.add(Long.parseLong(num));
        }
    }

    private static void blinkStones(){
        int blink = 1;
        while(blink <= NUMBER_OF_BLINKS){
            System.out.println("Blink " + blink);
            for(int i = 0; i < stones.size(); i++){
                long individualStone = stones.get(i);

                //System.out.println("\tChecking stone: " + individualStone);

                if(individualStone == 0){
                    stones.set(i, 1l);
                }else if(Long.toString(individualStone).length()%2 == 0){
                    //split stone
                    String stringStone = Long.toString(individualStone);
                    String firstHalf = stringStone.substring(0, stringStone.length()/2);
                    String secondHalf = stringStone.substring(stringStone.length()/2);

                    stones.set(i, Long.parseLong(secondHalf));
                    stones.add(i, Long.parseLong(firstHalf));

                    i++;
                }else{

                    stones.set(i, stones.get(i) * 2024);
                }
            }

            blink++;
        }
    }

    
}
