package ca.mcmaster.se2aa4.island.team115;

import java.util.concurrent.ExecutorCompletionService;

import org.apache.xerces.impl.dv.xs.DecimalDV;
import org.json.JSONObject;


public class Drone {
    private boolean isFound = false;
    private BatteryTracker currentBattery;
    private Coordinates currentPosition;
    private Direction currentDirection;
    private IslandFinder finder = new IslandFinder();
    // private State state;
    private Info currentInfo;

    public Drone (Integer initialBattery, String direction){
        currentBattery = new BatteryTracker(initialBattery);
        currentDirection = Direction.valueOf(direction);
        currentPosition = new Coordinates(0,0);
    }

    public void updateDirection(Direction currentDirection){
        this.currentDirection = currentDirection;
    }


    public JSONObject beginExploration(Drone drone){
        finder.setDrone(drone, currentInfo);
        return finder.locateIsland(currentDirection);
    }

    public void updateStatus(Integer cost, String status){
        currentBattery.adjustBattery(cost);
        
    }
    public void receiveResponse(Info currentInfo){
        this.currentInfo = currentInfo;
    }

    public JSONObject extraInfo(){
        return currentInfo.getExtras();
    }
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
