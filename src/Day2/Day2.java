package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {

        ArrayList<String> reports = new ArrayList<>();
        try {
            // Create a File object to read from "input.txt"
            File myObj = new File("./src/Day2/input.txt");
            Scanner myReader = new Scanner(myObj);

            // Read each line from the file and add it to reports
            while (myReader.hasNextLine()) {
                Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
               
                reports.add(data.nextLine());
                data.close();
            }
            myReader.close();

            //System.out.println(reports.get(0));

            System.out.println(findSafeLevels(reports));


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int findSafeLevels(ArrayList<String> AllReports){

        int count = 0;

        for(String report: AllReports){

            String[] stringLevels = report.split(" ");
            int[] levels = new int[stringLevels.length];

            for(int i =0; i<stringLevels.length; i++){
                levels[i] = Integer.parseInt(stringLevels[i]);
            }
            
            if(checkForSafeDifference(levels)){

                if(checkIncreasing(levels) || checkDecreasing(levels)){
                    //System.out.println(Arrays.toString(levels) + " is safe!");
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean checkIncreasing(int[] numbers){
        for(int i=0; i<numbers.length-1; i++){
            if(numbers[i]>numbers[i+1]){
                return false;
            }
        }
        return true;
    }
    public static boolean checkDecreasing(int[] numbers){
        for(int i=0; i<numbers.length-1; i++){
            if(numbers[i]<numbers[i+1]){
                return false;
            }
        }
        return true;
    }

    public static boolean checkForSafeDifference(int[] numbers){
        for(int i=0; i<numbers.length-1; i++){
            int difference = Math.abs(numbers[i] - numbers[i+1]);
            if(difference < 1 || difference > 3){
                return false;
            }
        }
        return true;
    }

}
