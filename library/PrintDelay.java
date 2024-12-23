package library;

import java.util.Arrays;

public class PrintDelay {


    public static <T> void printMatrixWithDelay(T[][] matrix, int delay){

        for(T[] row : matrix){

            System.out.println(Arrays.toString(row));
        }
        System.out.println();
        System.out.println();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
           System.out.println("The delay was interupted");
        }
    }
}

