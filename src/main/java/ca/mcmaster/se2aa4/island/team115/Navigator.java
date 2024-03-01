package ca.mcmaster.se2aa4.island.team115;

import static org.junit.Assert.assertEquals;

public class Navigator {

    private static Direction direction;

    //Should this method be static?
    public static Direction stringToEnum(String dir){
        switch(dir){
            case "N":
                direction = Direction.N;
                break;
            case "S":
                direction = Direction.S;
                break;
            case "E":
                direction = Direction.E;
                break;
            case "W":
                direction = Direction.W;
                break;
        }
        return direction;
    }

    public static String enumToString(Direction dir){
        switch(dir){
            case N:
                return "N";

            case S:
                return "S";

            case E:
                return "E";

            case W:
                return "W";
            default:
                return "Impossible";
        }
    }



    public void changeDirection(){
        //if statements for changing directions
    }

    public void navigate(){
        //Navigates/moves based on direction 
        switch(direction){
            case N:
                break;
            case E:
                break;
            case S:
                break;
            case W:
                break;
        }
    }
}
