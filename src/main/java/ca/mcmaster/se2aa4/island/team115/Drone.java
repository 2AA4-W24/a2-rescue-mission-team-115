package ca.mcmaster.se2aa4.island.team115;

import java.util.concurrent.ExecutorCompletionService;

import org.apache.xerces.impl.dv.xs.DecimalDV;
import org.json.JSONObject;


public class Drone {
    private boolean isFound = false;
    private BatteryTracker currentBattery;
    private Coordinates currentPosition;

    private Direction currentDirection;
    private Direction initialDirection;
    private IslandFinder finder = new IslandFinder();
    //private GridSearcher searcher = new GridSearcher();
    // private State state;
    private Info currentInfo;

    public Drone (Integer initialBattery, String direction){
        currentBattery = new BatteryTracker(initialBattery);
        currentDirection = Direction.valueOf(direction);
        initialDirection = currentDirection;
        currentPosition = new Coordinates(0,0);
    }

    public void updateDirection(Direction currentDirection){
        this.currentDirection = currentDirection;
    }


    public JSONObject beginExploration(Drone drone){
        /*if(finder.isComplete()){
            searcher.setDrone(drone, currentInfo);
            return searcher.findCreeks(currentDirection);
            //Searcher is NOT complete yet. I am still working on it so you might not be able to work on it.
            //If you want to work with the test cases, you might wanna adjust the method to completely remove searcher for now.
        }else{*/
            finder.setDrone(drone, currentInfo);
            return finder.locateIsland(currentDirection);
        //}
    }

    public void updateStatus(Integer cost, String status){
        currentBattery.adjustBattery(cost);
        
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
    public Direction getCurrentDirection(){
        return currentDirection;
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