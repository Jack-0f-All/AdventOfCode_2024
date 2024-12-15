package Day14;

public class bathroomDrone {

    int colPos, rowPos, deltaCol, deltaRow;
    int numOfMoves = 0;
    int maxCols, maxRows;
    String quadrant="Axis";

   public bathroomDrone(int xPos, int yPos, int dX, int dY, int rowSize, int colSize){

        colPos = xPos;
        rowPos = yPos;
        deltaCol = dX;
        deltaRow = dY;
        setGridSize(rowSize, colSize);
        setNumberOfMoves(100);
        //move();
        findQuadrant();

        //System.out.println("Drone initial position is " + xPos +" "+ yPos);
        
   }

   public void setGridSize(int rows, int cols){
        maxCols = cols;
        maxRows = rows;
   }

   public void moveStep(){


        colPos += deltaCol;
        //System.out.println("Drone moves to xPos ->" + xPosition);
        if(colPos >= maxCols){
            colPos= colPos - (maxCols); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to xPos -> " + xPosition);
        }
        if(colPos < 0){
            colPos= colPos + (maxCols); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to xPos -> " + xPosition);
        }


        rowPos+= deltaRow;
        //System.out.println("Drone moves to yPos ->" + yPosition);
        if(rowPos >= maxRows){
            rowPos= rowPos - (maxRows); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to yPos -> " + yPosition);
        }
        if(rowPos < 0){
            rowPos= rowPos + (maxRows); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to yPos -> " + yPosition);
        }

        findQuadrant();
        
    }

   public void setNumberOfMoves(int count){
        numOfMoves = count;
   }

   private void findQuadrant(){

        //The axis sits on the opposite coordinate
        int x_Axis = maxRows/2;
        int y_Axis = maxCols/2;

         if(colPos < y_Axis && rowPos < x_Axis){
            quadrant = "I";
         }else if(colPos > y_Axis && rowPos < x_Axis){
            quadrant = "II";
         }else if(colPos < y_Axis && rowPos > x_Axis){
            quadrant = "III";
         }else if(colPos > y_Axis && rowPos > x_Axis){
            quadrant = "IV";
         }else{
            quadrant = "Axis";
         }
   }
   public String getQuadrant(){
        return quadrant;
   }

   public String getPosition(){

        return colPos + " " + rowPos;
   }

   public int getColPos(){
    return colPos;
   }
   public int getRowPos(){
    return rowPos;
   }



   public String toString(){
        String output = "";


        output+= getPosition()+ "\n";
        output+= getQuadrant()+ "\n";

        return output;
   }
}
