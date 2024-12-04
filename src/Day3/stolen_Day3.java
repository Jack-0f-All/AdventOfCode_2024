package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Scanner;

public class stolen_Day3 {

    public static void main(String[] args) {
        try {
            // Create a File object to read from "input.txt"
            File myObj = new File("./src/Day3/input.txt");
            Scanner myReader = new Scanner(myObj);

            ArrayList<int[]> allCapturedMatches = new ArrayList<>();
            ArrayList<String> matchStrings = new ArrayList<>();

            regexSearch regex = new regexSearch("mul\\((\\d+),(\\d+)\\)");
            while (myReader.hasNextLine()) {
                Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
                String line = data.nextLine();
                
                String[] do_mul = line.split("do\\(\\)");

                for(String sub: do_mul){
                    String[] final_subs = sub.split("don't\\(\\)");
                    matchStrings.add(final_subs[0]);
                }

                for(String finalChecks:matchStrings){
                    for(int[] temp: regex.findCapturedMatches(finalChecks)){
                        allCapturedMatches.add(temp);
                    }
                }
                
                data.close();
            }
            myReader.close();


//Used this help from the reddit to solve the puzzle. It seems that I was on the right track with my original idea,
// but my execution was not working out correctly.


// - Given that the input is assumed to START as good, you can basically assume it starts with a do()
// - So first split the input by do()
// - Then split each of those items by don't()
// - Only search through the string that is the first item in each list

            System.out.println(calcTotalProduct(allCapturedMatches));

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    

    public static int calcTotalProduct(ArrayList<int[]> numbers){

        int totalProduct = 0;

        for(int[] pair:numbers){
            totalProduct += (pair[0] * pair[1]);
        }

        return totalProduct;

    }
}