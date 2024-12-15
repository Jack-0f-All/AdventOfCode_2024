package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day5 {

    static int pt1FinalAnswer = 0;
    static int pt2FinalAnswer = 0;
    static Map<String, String> ruleSet = new HashMap<>();
         public static void main(String[] args) {
        
        
                
        
                try {
                    // Create a File object to read from "input.txt"
        
                    File myObj = new File("./src/Day5/input.txt"); 
                    //File myObj = new File("./src/Day5/sampleData.txt");
        
                    Scanner myReader = new Scanner(myObj);
    
                    
    
                    // Read each line from the file
                    while (myReader.hasNextLine()) {
    
                        String data = myReader.nextLine();  // Read the entire line
                        
    
                        //breaks this loop when reaching the blank line in the input file
                        if(data.isEmpty()){
                            break;
                        }
                        String key = data.split("\\|")[0];
                        String newRule = data.split("\\|")[1];
                        
                        if(ruleSet.containsKey(key)){
    
                            ruleSet.put(key, ruleSet.get(key) + "|" + newRule);
                        }else{
    
                            ruleSet.put(key, newRule);
                        }  
                    }
    
                     while (myReader.hasNextLine()) {
    
                        String line = myReader.nextLine();
                        String [] relevantPages = line.split(",");
                        String ruleRegex="";
                        boolean isValidLine = true;
                        for(String page:relevantPages){
                             
                            if(ruleSet.containsKey(page)){
    
                                // Pattern example:  (47|12|70).*75
                                ruleRegex = "(" + ruleSet.get(page) + ").*" + page;
                               
                                Pattern rulePattern = Pattern.compile(ruleRegex, Pattern.CASE_INSENSITIVE);
                                
                                Matcher matcher = rulePattern.matcher(line);

                                if(matcher.find()){
                                    isValidLine = false;
                                }
                                
                            }
                        }

                        
    
                        if(isValidLine){
                            //System.out.println(line);
    
                            pt1FinalAnswer += Integer.parseInt(line.split(",")[line.split(",").length/2]);

                        }else{
                            
                            //fix the line and then find the center page
                            pt2FinalAnswer += fixAndFind(line.split(","));
                        }
                }

                myReader.close();

                //System.out.println(pt1FinalAnswer);
                System.out.println(pt2FinalAnswer);
    
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    

        }


        public static int fixAndFind(String[] badLine){

            ArrayList <String> fixedLine = new ArrayList<>(Arrays.asList(badLine));

            for(int pageIndex = 0; pageIndex<badLine.length;){
                System.out.println(Arrays.toString(badLine));
                if(ruleSet.containsKey(badLine[pageIndex])){

                    String[] individualRules = ruleSet.get(badLine[pageIndex]).split("\\|");
                    

                    for(String rule: individualRules){
                    {
    
                        // Pattern example:  47.*75
                        String ruleRegex = rule + ".*" + badLine[pageIndex];
                        Pattern rulePattern = Pattern.compile(ruleRegex, Pattern.CASE_INSENSITIVE);
                        Matcher matcher = rulePattern.matcher(fixedLine.toString());
                        //System.out.println(rulePattern);

                        if(matcher.find()){
                            System.out.println("found a bad page using rule " + rulePattern);
                            //edit the line
                            fixedLine.remove(badLine[pageIndex]);
                            System.out.println(fixedLine.toString());
                            fixedLine.add(fixedLine.indexOf(rule), badLine[pageIndex]);
                            System.out.println(fixedLine.toString());
                        }
                        
                    }

                    }
                }

                pageIndex++;

            }

            System.out.println( fixedLine.toString());
            System.out.println("\n");

            return Integer.parseInt(fixedLine.get(fixedLine.size()/2));
        }
    
    
}