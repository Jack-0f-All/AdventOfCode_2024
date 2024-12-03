package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day2_part2 {
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
            
            if(checkForSafeDifference(levels)&&(checkIncreasing(levels) || checkDecreasing(levels))){
                
                    count++;
        
            }else{

                if(problemDampener(levels)){
                    count++;
                }
            }
        }
        return count;
    }


    public static boolean problemDampener(int[] numbersArray){
        
        //System.out.println(Arrays.toString(numbersArray) + "failed a test. Running Problem Dampener");
        ArrayList<Integer> numbersList = new ArrayList<>();
        int numberOfFails = 0;

        for(int num:numbersArray){
            numbersList.add(num);
        }
        //System.out.println("The starting ArrayList is : " + numbersList.toString());
        for(int i=0; i<numbersArray.length; i++){
            numbersList.remove(i);

            //System.out.println("Checking " + numbersList.toString());


            int[] temp = new int[numbersList.size()];

            for (int y = 0; y < numbersList.size(); y++) {
                temp[y] = numbersList.get(y); 
            }

            if(checkForSafeDifference(temp)&&(checkDecreasing(temp)||checkIncreasing(temp))){
                    return true;
            }

            numbersList.add(i,numbersArray[i]);
        }
        return false;

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
