package ca.mcmaster.se2aa4.island.team115;

import java.util.concurrent.ExecutorCompletionService;

import org.apache.xerces.impl.dv.xs.DecimalDV;
import org.json.JSONObject;


public class Drone {
    private boolean isFound = false;
    private BatteryTracker tracker;
    private Coordinates currentPosition;
    private Direction currentDirection;
    private Direction initialDirection;
    private IslandFinder finder = new IslandFinder();
    private GridSearcher searcher = new GridSearcher();
    // private State state;
    private Info currentInfo;

    public Drone (Integer initialBattery, String direction){
        tracker = new BatteryTracker(initialBattery);
        currentDirection = Direction.valueOf(direction);
        currentPosition = new Coordinates(0,0);
        currentPosition.setDirection(currentDirection);
    }

    public void updateDirection(Direction currentDirection){
        this.currentDirection = currentDirection;
    }
    public void updateCoordinates(Coordinates currentPosition){
        this.currentPosition = currentPosition;
    }

    public JSONObject beginExploration(Drone drone){
        if(tracker.getBatteryLevel()<25){
            finder.setDrone(drone, currentInfo, currentPosition);
            return finder.stopExploration();
        }else{
            if(finder.isComplete()){
                searcher.setDrone(drone, currentInfo, currentPosition);
                return searcher.findPOIs(currentDirection);
            }else{
                finder.setDrone(drone, currentInfo, currentPosition);
                return finder.locateIsland(currentDirection);
            }
        }
    }

    public void updateStatus(Integer cost, String status){
        tracker.adjustBattery(cost);
        
    }
    public void receiveResponse(Info currentInfo){
        this.currentInfo = currentInfo;
    }
    //I might merge updateStatus and receiveResponse into one method.

    public Direction getInitialDirection(){
        return initialDirection;
    }
    //Might delete this ^ method too

    public JSONObject extraInfo(){
        return currentInfo.getExtras();
    }
    //Also might delete this method


    // public void changeState(State state){
    //     this.state = state;
    // }
    // public boolean foundIsland(){
    //     return isFound;
    // }

    // public void setFound(boolean isFound){
    //     this.isFound = isFound;
    // }
    // private void setDroneDirection(Direction direction){
        
    // }
    // private void setInitialBattery(){
    // }
}
