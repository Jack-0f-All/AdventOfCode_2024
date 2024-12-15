package Day13;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

    static boolean isPart2 = true;
    static double dx1;
    static double dy1;
    static double dx2;
    static double dy2;
    static double targetX, targetY;
    static double n1;
    static double n2;
    
    static int buttonATokens = 3;
    static int buttonBTokens = 1;
    static long totalTokens = 0;


        public static void main(String[] args) {
            
            try {
    
                File myFile = new File("./src/Day13/input.txt");
                //File myFile = new File("./src/Day13/sampleData.txt");  
                
                //40633 to high
                //39675 to low
                                                                        
    
                Scanner myReader = new Scanner(myFile);
                String lineResults = "";
                while(myReader.hasNext()){
                    
                    String dataA = myReader.nextLine();

                    Pattern buttonPattern = Pattern.compile(".*X\\+(\\d+),\\s*Y\\+(\\d+)", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = buttonPattern.matcher(dataA);

                    if (matcher.find()) {
                        dx1 = Integer.parseInt(matcher.group(1));
                        dy1 = Integer.parseInt(matcher.group(2));
                    } else {
                        System.out.println("No match found for button data: " + dataA);
                    }

                    String dataB = myReader.nextLine();
                    
                    matcher = buttonPattern.matcher(dataB);

                    if (matcher.find()) {
                        dx2 = Integer.parseInt(matcher.group(1));
                        dy2 = Integer.parseInt(matcher.group(2));
                    } else {
                        System.out.println("No match found for button data: " + dataB);
                    }

                    String dataT = myReader.nextLine();

                    Pattern targetPattern = Pattern.compile(".*X=(\\d+), Y=(\\d+)", Pattern.CASE_INSENSITIVE);
                    matcher = targetPattern.matcher(dataT);
                    if(matcher.find()){
                        targetX = Integer.parseInt(matcher.group(1)) + (isPart2 ? 10000000000000.0 : 0);
                        
                        targetY = Integer.parseInt(matcher.group(2)) + (isPart2 ? 10000000000000.0 : 0);
                    } else { 
                        System.out.println("No match found for target data: " + dataT);
                    }

                    calculateButtonPresses();

                    lineResults += String.format("n1: %f   n2: %f  %b%n", n1, n2, isPossiblePrize() );
                    
                    if(isPossiblePrize()){
                        //System.out.printf("Tokens :%n    A: %f%n    B: %f%n ", (buttonATokens * n1) , (buttonBTokens * n2));
                        totalTokens += (buttonATokens * n1) + (buttonBTokens * n2);
                    }

                    if(myReader.hasNextLine()){

                        myReader.nextLine();
                    }

                    printToFile(lineResults, "calculatedPrizes");
                    
            }

            System.out.println("Total tokens: "+ totalTokens);

            myReader.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            System.out.println("Can't find file by that name or in that location.");
            e.printStackTrace();
        }
    }


    public static void calculateButtonPresses(){

        //Credit to my sister, Tess, for helping me derive the formulas for solving this system of equations.
        //
        // The equations are:
        //  targetXPos = deltaX1 * (n1) + deltaX2 * (n2)
        //  targetYPos = deltaY1 * (n1) + deltaY2 * (n2)
        //
        // These are then rearranged and solved for n2 and n1.

        n2 = (dx1 * targetY - dy1 * targetX)/(((-dy1) * dx2) + (dy2 * dx1));

        n1 = (targetX - dx2 * n2)/(dx1);
        
    }

    public static boolean isPossiblePrize(){
        

        double n1Remainder = (n1 * 10 )%10;
        double n2Remainder = (n2 * 10 )%10;
        double epsilon = 0.000001;
        if((Math.abs(0 - n1Remainder) < epsilon && (Math.abs(0 - n2Remainder) < epsilon )))
        {
            return true;
        }
        return false;
    }

    public static void printToFile(String newData, String name){
       
            String filePath = "./src/Day13/"+name+".txt";
            
            // Create a File object
            File file = new File(filePath);
            
            // Create and write to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(newData);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }


}