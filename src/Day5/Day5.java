package Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day5 {

     public static void main(String[] args) {
    
    
            
    
            try {
                // Create a File object to read from "input.txt"
    
                //File myObj = new File("./src/Day5/input.txt"); 
                File myObj = new File("./src/Day5/sampleData.txt");
    
                Scanner myReader = new Scanner(myObj);

                Map<String, String> ruleSet = new HashMap<>();

                // Read each line from the file
                while (myReader.hasNextLine()) {

                    String data = myReader.nextLine();  // Read the entire line
                    

                    //breaks this loop when reaching the blank line in the input file
                    if(data.isEmpty()){
                        System.out.println("breaking loop");
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

                System.out.println(ruleSet);

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
                        System.out.println(line);
                    }

                    

                }

                myReader.close();
    
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    

        }
    
    
}