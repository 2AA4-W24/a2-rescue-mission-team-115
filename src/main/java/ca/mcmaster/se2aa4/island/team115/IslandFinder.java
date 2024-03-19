package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;

public class IslandFinder {

    private boolean echoRight = true;
    private boolean changeHeading = false;
    private boolean foundGround;
    private int range;
    private Info info;
    private boolean tempFound;
    public boolean target = false;
    private Direction currentDirection;
    Action action = new Action();


    




    private void isGround(String foundString){
        if(foundString.equals("GROUND")){
            foundGround = true;
        }else{
            foundGround = false;
        }
    }
    // public String echoFindings(){

    // }
    public JSONObject locateIsland(Direction currentDir){
        currentDirection = currentDir;

        if (changeHeading){
            action.heading(currentDirection);
        }

        else if(!echoRight){
            action.echo(currentDirection.leftDir());
            if (foundGround){
                changeHeading = true;
                range = info.echoRange();

                currentDirection = currentDirection.leftDir();
            }

        }else if(echoRight){
            action.echo(currentDirection.rightDir());
            if (foundGround){
                changeHeading = true;
                range = info.echoRange();

                currentDirection = currentDirection.leftDir();
            }
        }

        System.out.println("SUCCESS");
        return action.getDecision();
    
    }

    public void updateState(Info info){
        
    }
        
    // public void moveToIsland(){
    //     action.fly();
    // }
    // private void leftTurnLoop(){

    // }

    //     public void scanIsland(){
    //        if (facing == moveDir)
    //             for(i = 0; i<range; i++){
    //                 fly;
    //                 scan until water;
    //                 turn right;
    //                 after turn, move atleast 5, if after 5 sitll no ground, stop && done = true;
    //                     move in opposite direction
    //             }
                
    //     }
}


        