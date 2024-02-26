package ca.mcmaster.se2aa4.island.team115;

import static org.junit.Assert.assertEquals;

public class Navigator {

    private static Direction direction;

    //Should this method be static?
    public static Direction stringToEnum(String dir){
        switch(dir){
            case "N":
                direction = Direction.North;
                break;
            case "S":
                direction = Direction.South;
                break;
            case "E":
                direction = Direction.East;
                break;
            case "W":
                direction = Direction.West;
                break;
        }
        return direction;
    }

    public static String enumToString(Direction dir){
        switch(dir){
            case North:
                return "N";

            case South:
                return "S";

            case East:
                return "E";

            case West:
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
            case North:
                break;
            case East:
                break;
            case South:
                break;
            case West:
                break;
        }
    }
}
