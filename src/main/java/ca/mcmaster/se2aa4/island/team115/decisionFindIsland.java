package ca.mcmaster.se2aa4.island.team115;

import org.apache.wml.WMLAElement;

import com.alibaba.fastjson.JSONObject;

public class decisionFindIsland {
    public String facing = "East";
    public int rangeF;
    public int rangeFL;
    public int rangeFR;
    public boolean foundF; // the ground is equal to true
    public boolean foundFR;
    public boolean foundFL;

    public boolean target = false;

    public void echo(String direction){
        JSONObject decisio
    }
    public void turn (String turnDirection){
            E S N 
            S E W
            N W E
            W N S
    }
    public boolean FindIsland(){
        echo("Facing");
        echo("FacingLeftOf");
        echo("FacingRightOf");
        // the facing and value will be updated for all three directions
        rangeF = 50;
        rangeFL = 53;
        rangeFR = 0;
        foundF = false;
        foundFL = false;
        foundFR = false;
        // the three founds updated
        if (foundF || foundFL || foundFR) {
            if (foundF ){
                // move towards that direction range
                target = true;
            } else if (foundFR) {
                // move towards that direction range
                target = true;
            }else {
                // move towards left direction range
                target = true;
            }
            return target;

        }
        else {
            if (rangeFR == 0) {
                turn("right");
                facing = "update it so in this case it is from east to North";
                rangeFR = rangeF;
                foundFR = foundF;
                rangeF = rangeFL;
                foundF = foundFL;
                echo("left");
                rangeFL = 30;
                foundFL = false;
                // IF new range is 0 then we are in a corner, nicee.

            } else if (rangeFL == 0) {
                turn("left");
                facing = "update it so in this case it is from east to South";
                rangeFL = rangeF;
                foundFL = foundF;
                rangeF = rangeFR;
                foundF = foundFR;
                echo("right");
                rangeFR = 30;
                foundFR = false;
                // IF new range is 0 then we are in a corner, nicee.


            }
            // ask if any of the founds is equal to true.
            if (rangeF >= rangeFR){
                if (rangeF >= rangeFL){
                    // move the rangeF/8 in direction F
                } else if (rangeFL > rangeF) {
                    // move the rangeFL/8 in the FL direction
                }
            } else if (rangeFR >= rangeF) {
                if (rangeFR >= rangeFL){
                    // move the rangeFR/8 in direction FR
                } else if (rangeFL > rangeFR) {
                    // move the rangeFR/8 in the FR direction
                }
            }
            echo("FacingLeftOf");
            rangeF = 50;
            foundF = false;
            if (foundF == true){
                // move to the iland
                target = true;
            }

        }

    return target;
    }
}
