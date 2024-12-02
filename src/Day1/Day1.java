import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Day1{
    public static void main(String[] args) {
        // Declare the ArrayLists inside the main method
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        int parts[] = new int[2];

        try {
            // Create a File object to read from "input.txt"

            
            File myObj = new File("./src/Day1/input.txt");
            Scanner myReader = new Scanner(myObj);

            // Read each line from the file
            while (myReader.hasNextLine()) {
                Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
                parts[0] = data.nextInt();  // Split the line into parts based on space
                parts[1] = data.nextInt();  // Split the line into parts based on space
                

                // Convert each part to an integer and add to the respective list
                if (parts.length >= 2) {
                    list1.add(parts[0]);  // Add first integer to list1
                    list2.add(parts[1]);  // Add second integer to list2
                }
                data.close();
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        list1.sort(null);
        list2.sort(null);


       System.out.println(getDistance(list1, list2));
       System.out.println(getSimilarity(list1, list2));

    }

    public static int getDistance(ArrayList<Integer>list1, ArrayList<Integer>list2){

        int distance = 0;
        for(int i=0; i<list1.size(); i++){  
            distance += (Math.abs(list1.get(i) - list2.get(i)));
        }

        return distance;
    }

    public static int getSimilarity(ArrayList<Integer>list1, ArrayList<Integer>list2){

        int similarity = 0;

        for(int num1: list1){
            int count = 0;
            for(int num2:list2){
                if(num1 == num2){
                    count++;
                }
                if(num2 > num1){
                    break;
                }
            }
            similarity += num1 * count;
        }

        return similarity;
    }
}
