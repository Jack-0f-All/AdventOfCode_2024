package Day14;

public class bathroomDrone {

    int xPosition, yPosition, deltaX, deltaY;
    int numOfMoves = 0;
    int max_X_Value, max_Y_Value;
    String quadrant="Axis";

   public bathroomDrone(int xPos, int yPos, int dX, int dY, int xSize, int ySize){

        xPosition = xPos;
        yPosition = yPos;
        deltaX = dX;
        deltaY = dY;
        setGridSize(xSize, ySize);
        setNumberOfMoves(100);
        //move();
        findQuadrant();

        //System.out.println("Drone initial position is " + xPos +" "+ yPos);
        
   }

   public void setGridSize(int x, int y){
        max_X_Value = x;
        max_Y_Value = y;
   }

   private void move(){

        int steps = 0;

        while(steps < numOfMoves){

            xPosition += deltaX;

            if(xPosition > max_X_Value){
                xPosition= xPosition - (max_X_Value + 1); //The teleportation takes 1 step.
            }
            if(xPosition < 0){
                xPosition= xPosition + (max_X_Value + 1); //The teleportation takes 1 step.
            }


            yPosition+= deltaY;

            if(yPosition > max_Y_Value){
                yPosition= yPosition - (max_Y_Value + 1); //The teleportation takes 1 step.
            }
            if(yPosition < max_Y_Value){
                yPosition= yPosition + (max_Y_Value + 1); //The teleportation takes 1 step.
            }

            steps++;
            findQuadrant();
        }
   }
   public void moveStep(){


        xPosition += deltaX;
        //System.out.println("Drone moves to xPos ->" + xPosition);
        if(xPosition >= max_X_Value){
            xPosition= xPosition - (max_X_Value); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to xPos -> " + xPosition);
        }
        if(xPosition < 0){
            xPosition= xPosition + (max_X_Value); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to xPos -> " + xPosition);
        }


        yPosition+= deltaY;
        //System.out.println("Drone moves to yPos ->" + yPosition);
        if(yPosition >= max_Y_Value){
            yPosition= yPosition - (max_Y_Value); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to yPos -> " + yPosition);
        }
        if(yPosition < 0){
            yPosition= yPosition + (max_Y_Value); //The teleportation takes 1 step.
            //System.out.println("Drone teleports to yPos -> " + yPosition);
        }

        findQuadrant();
        
    }

   public void setNumberOfMoves(int count){
        numOfMoves = count;
   }

   private void findQuadrant(){

        //The axis sits on the opposite coordinate
        int x_Axis = max_Y_Value/2;
        int y_Axis = max_X_Value/2;

         if(xPosition < y_Axis && yPosition < x_Axis){
            quadrant = "I";
         }else if(xPosition > y_Axis && yPosition < x_Axis){
            quadrant = "II";
         }else if(xPosition < y_Axis && yPosition > x_Axis){
            quadrant = "III";
         }else if(xPosition > y_Axis && yPosition > x_Axis){
            quadrant = "IV";
         }else{
            quadrant = "Axis";
         }
   }
   public String getQuadrant(){
        return quadrant;
   }

   public String getPosition(){

        return xPosition + " " + yPosition;
   }

   public int getXPos(){
    return xPosition;
   }
   public int getYPos(){
    return yPosition;
   }



   public String toString(){
        String output = "";


        output+= getPosition()+ "\n";
        output+= getQuadrant()+ "\n";

        return output;
   }
}
