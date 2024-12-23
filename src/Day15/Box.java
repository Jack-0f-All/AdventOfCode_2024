package Day15;

public class Box {
    
    int xPos, yPos;

    public Box(int row, int col){

        xPos = row;
        yPos = col;

    }



public boolean checkAndMove(String orientation){

    int deltaRow = 0;
    int deltaCol = 0;

    switch(orientation){

        case "^" : deltaRow = -1; deltaCol =  0; break;
        case ">" : deltaRow =  0; deltaCol =  1; break;
        case "V" : deltaRow =  1; deltaCol =  0; break;
        case "<" : deltaRow =  0; deltaCol = -1; break;
    }

    return false;


}

public void move(String orientation){



}



}
