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
            case Direction.North:
                return "N";

            case Direction.South:
                return "S";

            case Direction.East:
                return "E";

            case Direction.West:
                return "W";
            default:
                return "A7a";
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
