package Day25;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day25 {
    public static void main(String[] args) {
        

        loadFile();

    }



    public static void loadFile(){

        try {

            File myFile = new File("src/Day25/input.txt");
            Scanner file = new Scanner(file);

            String line = file.nextLine();

            while(line.length() > 0){
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("Can't find a file by that name...");
            System.out.println(e);
        }
    }
}
