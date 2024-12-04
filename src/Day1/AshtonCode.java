import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AshtonCode {
    public static void main(String[] args) throws Exception {
        

        try{
    File myObj = new File( "src/Day1/input.txt" ); 


            Scanner myReader = new Scanner(myObj);
            ArrayList<Integer> ray1 = new ArrayList<>();
            ArrayList<Integer> ray2 = new ArrayList<Integer>();

            // Read each line from the file
            while (myReader.hasNextLine()) {
                Scanner data = new Scanner(myReader.nextLine());  // Read the entire line
                
                ray1.add(data.nextInt());
                ray2.add(data.nextInt());



                data.close();
            }
            myReader.close();
            ray1.sort(null);
            ray2.sort(null);
            System.out.println(ray1.get(0));
            System.out.println(ray2.get(0));

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred./n/n No file found by that name.");
            
        }

    }
}