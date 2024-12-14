package Day9;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;
import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;


public class Day9 {

    static int ID;
    static String initialDisk = "";
    public static void main(String[] args) {
        try{

            //File myObj = new File("./src/Day9/sampleData.txt");
            File myObj = new File("./src/Day9/input.txt");
             
            //Correct answer pt.1 -> 6299243228569
            //Correct answer pt.2 -> 6326952672104

            Scanner myReader = new Scanner(myObj);

            String data = myReader.nextLine();

            myReader.close();


            //Cannot run both parts accurately. Uncomment the desire code and run.


            // buildInitialDisk(data);
            // printToFile(initialDisk, "initialDisk");
            // String cDisk = fileCompaction();
            // printToFile(cDisk, "compactedDisk");
            // System.out.println(checkSum(fileCompaction()));


            buildInitialDisk(data);
            printToFile(initialDisk, "initialDisk");
            String wCDisk = wholeFileCompaction();
            wholeFileCompaction();
            printToFile(wCDisk, "wholeFileCompacted");
            System.out.println(checkSum(wholeFileCompaction()));



        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            System.out.println("Can't find file by that name or in that location.");
            e.printStackTrace();
        }
    }

    public static void buildInitialDisk(String data){
        System.out.println("Building initial disk...");

        ID = 0;
        boolean isID = true;
        for(String num: data.split("")){
            if(isID){
                initialDisk += (ID +" ").repeat(Integer.parseInt(num));
                isID=!isID;
                ID++;
            }else{
                initialDisk += ". ".repeat(Integer.parseInt(num));
                isID=!isID;
            }
        }

        ID--;
    
    }

    public static String fileCompaction(){

        System.out.println("Compacting disk...");

        StringBuilder compactDisk= new StringBuilder(initialDisk);

        //System.out.println(compactDisk.toString().length());
        while(compactDisk.indexOf(". ") > -1){
            

            int firstDotIndex = compactDisk.indexOf(". ");
            compactDisk.deleteCharAt(firstDotIndex);
            compactDisk.deleteCharAt(firstDotIndex);
            
            int movingFileStartIndex = compactDisk.lastIndexOf(ID+" ");
            int fileIDSize = (ID+" ").length();


            compactDisk.insert(firstDotIndex, compactDisk.substring(movingFileStartIndex, movingFileStartIndex+fileIDSize));
            
            movingFileStartIndex = compactDisk.lastIndexOf(ID+" ");
            compactDisk.replace(movingFileStartIndex,  movingFileStartIndex+fileIDSize, "");
          

            //Checks if all files of that ID have been moved to earlier position.
            if(compactDisk.lastIndexOf(ID+" ") < compactDisk.lastIndexOf((ID-1)+" "))
            {
                //Change ID to the next ID value.
                ID--;
            }
            
        }
        
        return compactDisk.toString();
    }


    public static String wholeFileCompaction(){
        int wholeFileID = ID;
        System.out.println("Compacting disk moving whole files...");

        StringBuilder compactDisk= new StringBuilder(initialDisk);
        
            while(wholeFileID>0){
                
                int IDLength = (wholeFileID+ " ").length();
                String fileGroup = compactDisk.substring(compactDisk.indexOf(wholeFileID+" "), compactDisk.lastIndexOf(wholeFileID+" ")+IDLength);
                int fileIndex = compactDisk.indexOf(wholeFileID+" ");
                
                int fileSize = fileGroup.split(" ").length;
                
                
                String dots = ". ".repeat(fileSize);
                
                int indexOfSpace = compactDisk.indexOf(dots);
                //System.out.println(indexOfSpace);
                if(indexOfSpace != -1 && indexOfSpace<fileIndex){
                    
                    compactDisk.replace(indexOfSpace, indexOfSpace + ". ".repeat(fileSize).length(), ""); //Deletes the . in the space
                    
                    

                    //Removes the file group that is moving from the string and inserts it in the space.
                    compactDisk.replace(compactDisk.indexOf(wholeFileID+" "), compactDisk.lastIndexOf(wholeFileID+" ")+IDLength, dots);
                    compactDisk.insert(indexOfSpace, fileGroup);

                   
                }


                wholeFileID--;
                printToFile(compactDisk.toString(), "midSort");
            }
            return compactDisk.toString();
    }

       
    


    public static long checkSum(String disk){

        System.out.println("Calculating checkSum...");
        int ID_Value = 0;
        long checksumValue = 0;
        for(String s: disk.split(" ")){
            if(!s.equals(" ")&& !s.equals(".")){
                checksumValue += Integer.parseInt(s) * ID_Value;
                ID_Value++;
            }else{
                ID_Value++;
            }
        }
        
        return checksumValue;
    }

    public static void printToFile(String newData, String name){
       
            String filePath = "./src/Day9/"+name+".txt";
            
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