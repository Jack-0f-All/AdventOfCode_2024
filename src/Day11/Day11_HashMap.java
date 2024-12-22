package Day11;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;

public class Day11_HashMap {

    static final int MAX_BLINKS = 75;
    static HashMap<Long, Long> stones = new HashMap<>();

    public static void main(String[] args) {
        try {

            File myFile = new File("./src/Day11/input.txt");
            //File myFile = new File("./src/Day11/sampleData.txt");

            Scanner myReader = new Scanner(myFile);

            System.out.println("Loading data...\n");

            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                loadMap(data);
            }
                
            myReader. close();
                            
            } catch (FileNotFoundException e) {

                System.out.println("Can't find file by that name...");

            }finally{

                blinkStones();
                Long sum = 0L;
                for(Long s:stones.keySet()){
                    sum += stones.get(s);
                }
                System.out.println("Total number of stones after blinking: "+ sum);
                
                
            }
    }
                
    private static void loadMap(String data) {
       
        for(String num:data.split(" ")){
            Long singleStone = Long.parseLong(num);
            if(!stones.containsKey(singleStone)){
                stones.put(singleStone, 1L);
            }else{
                stones.put(singleStone, stones.get(singleStone) + 1);
            }
            
        }
    }

    private static void blinkStones(){
       
        int blinkCount = 0;
        HashMap <Long, Long> tempMap = new HashMap<>();

        while(blinkCount < MAX_BLINKS){

            for(Long key:stones.keySet()){
                

                if(Long.toString(key).length()%2 == 0){
                    String stoneAsString = Long.toString(key);

                    String firstHalf = stoneAsString.substring(0, stoneAsString.length()/2);
                    String secondHalf = stoneAsString.substring(stoneAsString.length()/2);

                    Long part1 = Long.parseLong(firstHalf);
                    Long part2 = Long.parseLong(secondHalf);


                    if(!tempMap.containsKey(part1)){
                        tempMap.put(part1, stones.get(key));
                    }else{
                        tempMap.put(part1, stones.get(key) + tempMap.get(part1));
                    }

                    if(!tempMap.containsKey(part2)){
                        tempMap.put(part2, stones.get(key));
                    }else{
                        tempMap.put(part2, stones.get(key) + tempMap.get(part2));
                    }


                }else if(key == 0){
                    tempMap.put(1L,stones.get(key));
                }else{
                    tempMap.put(key*2024, stones.get(key));
                }
            }

            stones.clear();
            for(Long key: tempMap.keySet()){
                stones.put(key, tempMap.get(key));
            }
            tempMap.clear();

            blinkCount++;

        }
    }
}