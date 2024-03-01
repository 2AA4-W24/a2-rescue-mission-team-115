package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;

public class decisionFindIsland {
    ExtractInfo info = new ExtractInfo();
    public Direction facing = info.getInitialHeading() ;
    private int range;

    private boolean isGround; // the ground is equal to true

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
    public void turn (String newDirection){

    }

    public boolean findIsland(){
        String strDir = Navigator.enumToString(facing);
        switch(facing){
            case Direction.N:
                echo(Direction.N);
                rangeNorth = info.getRange();
                foundNorth = foundResults(info.getFound());
                echo(Direction.E);
                rangeEast = info.getRange();
                foundEast = foundResults(info.getFound());
                echo(Direction.W);
                rangeWest = info.getRange();
                foundWest = foundResults(info.getFound());

                break;
            case Direction.S:
                echo(Direction.S);
                rangeSouth = info.getRange();
                foundSouth = foundResults(info.getFound());
                echo(Direction.E);
                rangeEast = info.getRange();
                foundEast = foundResults(info.getFound());
                echo(Direction.W);
                rangeWest = info.getRange();
                foundWest = foundResults(info.getFound());
                break;  
            case Direction.E:
                echo(Direction.E);
                rangeEast = info.getRange();
                foundEast = foundResults(info.getFound());
                echo(Direction.N);
                rangeNorth = info.getRange();
                foundNorth = foundResults(info.getFound());
                echo(Direction.S);
                rangeSouth = info.getRange();
                foundSouth = foundResults(info.getFound());
                break;  
            case Direction.W:
                echo(Direction.W);
                rangeWest = info.getRange();
                foundWest = foundResults(info.getFound());
                echo(Direction.N);
                rangeNorth = info.getRange();
                foundNorth = foundResults(info.getFound());
                echo(Direction.S);
                rangeSouth = info.getRange();
                foundSouth = foundResults(info.getFound());
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
    //             // move to the island
    //             target = true;
    //         }

    //     }

     return target;
    }
}

