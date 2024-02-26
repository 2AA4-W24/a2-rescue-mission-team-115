package ca.mcmaster.se2aa4.island.team115;

import org.apache.wml.WMLAElement;

import org.json.JSONObject;
import org.json.JSONTokener;

public class decisionFindIsland {
    ExtractInfo ext = new ExtractInfo();
    public Direction facing = ext.getInitialHeading() ;
    public int rangeNorth;
    public int rangeSouth;
    public int rangeEast;
    public int rangeWest;
    public boolean foundNorth; // the ground is equal to true
    public boolean foundSouth;
    public boolean foundEast;
    public boolean foundWest;

    public boolean target = false;


    public String echo(Direction direction){
        String strDir = Navigator.enumToString(direction);
        JSONObject decision = new JSONObject();
        decision.put("action", "echo");
        decision.put("parameters", (new JSONObject()).put("direction", strDir));
        return decision.toString();
    }
    public boolean foundResults(String foundString){
        if(foundString.equals("GROUND")){
            return true;
        }else{
            return false;
        }
    }
    public void turn (String turnDirection){
          
    }

    public boolean findIsland(){
        String strDir = Navigator.enumToString(facing);
        switch(facing){
            case Direction.North:
                echo(Direction.North);
                rangeNorth = ext.getRange();
                foundNorth = foundResults(ext.getFound());
                echo(Direction.East);
                rangeEast = ext.getRange();
                foundEast = foundResults(ext.getFound());
                echo(Direction.West);
                rangeWest = ext.getRange();
                foundWest = foundResults(ext.getFound());

                break;
            case Direction.South:
                echo(Direction.South);
                rangeSouth = ext.getRange();
                foundSouth = foundResults(ext.getFound());
                echo(Direction.East);
                rangeEast = ext.getRange();
                foundEast = foundResults(ext.getFound());
                echo(Direction.West);
                rangeWest = ext.getRange();
                foundWest = foundResults(ext.getFound());
                break;  
            case Direction.East:
                echo(Direction.East);
                rangeEast = ext.getRange();
                foundEast = foundResults(ext.getFound());
                echo(Direction.North);
                rangeNorth = ext.getRange();
                foundNorth = foundResults(ext.getFound());
                echo(Direction.South);
                rangeSouth = ext.getRange();
                foundSouth = foundResults(ext.getFound());
                break;  
            case Direction.West:
                echo(Direction.West);
                rangeWest = ext.getRange();
                foundWest = foundResults(ext.getFound());
                echo(Direction.North);
                rangeNorth = ext.getRange();
                foundNorth = foundResults(ext.getFound());
                echo(Direction.South);
                rangeSouth = ext.getRange();
                foundSouth = foundResults(ext.getFound());
                break;   
        }

        // MUST IMPLEMENT THE REST OF THE LOGIC, WHICH IS STATED BELOW
    //     // the facing and value will be updated for all three directions

        

    //     // the three founds updated
    //     if (foundF || foundFL || foundFR) {
    //         if (foundF ){
    //             // move towards that direction range
    //             target = true;
    //         } else if (foundFR) {
    //             // move towards that direction range
    //             target = true;
    //         }else {
    //             // move towards left direction range
    //             target = true;
    //         }
    //         return target;

    //     }
    //     else {
    //         if (rangeFR == 0) {
    //             turn("right");
    //             facing = "update it so in this case it is from east to North";
    //             rangeFR = rangeF;
    //             foundFR = foundF;
    //             rangeF = rangeFL;
    //             foundF = foundFL;
    //             echo("left");
    //             rangeFL = 30;
    //             foundFL = false;
    //             // IF new range is 0 then we are in a corner, nicee.

    //         } else if (rangeFL == 0) {
    //             turn("left");
    //             facing = "update it so in this case it is from east to South";
    //             rangeFL = rangeF;
    //             foundFL = foundF;
    //             rangeF = rangeFR;
    //             foundF = foundFR;
    //             echo("right");
    //             rangeFR = 30;
    //             foundFR = false;
    //             // IF new range is 0 then we are in a corner, nicee.


    //         }
    //         // ask if any of the founds is equal to true.
    //         if (rangeF >= rangeFR){
    //             if (rangeF >= rangeFL){
    //                 // move the rangeF/8 in direction F
    //             } else if (rangeFL > rangeF) {
    //                 // move the rangeFL/8 in the FL direction
    //             }
    //         } else if (rangeFR >= rangeF) {
    //             if (rangeFR >= rangeFL){
    //                 // move the rangeFR/8 in direction FR
    //             } else if (rangeFL > rangeFR) {
    //                 // move the rangeFR/8 in the FR direction
    //             }
    //         }
    //         echo("FacingLeftOf");
    //         rangeF = 50;
    //         foundF = false;
    //         if (foundF == true){
    //             // move to the iland
    //             target = true;
    //         }

    //     }

     return target;
    }
}

