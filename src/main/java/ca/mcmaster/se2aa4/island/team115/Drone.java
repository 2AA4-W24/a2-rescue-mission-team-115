package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;


public class Drone {
    private BatteryTracker tracker;
    private Coordinates currentPosition;
    private Direction currentDirection;
    private IslandFinder finder = new IslandFinder();
    private GridSearcher searcher = new GridSearcher();
    private Info currentInfo;
    private String closestCreekID;

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

    public String getClosestCreekID(){
        return closestCreekID;
    }

    public int getBattery(){
        return tracker.getBatteryLevel();
    }

    public JSONObject beginExploration(){
        JSONObject decision;
        if(tracker.getBatteryLevel()<35 || searcher.isComplete()){
            closestCreekID = searcher.getClosestCreek();
            searcher.setDrone(this, currentInfo, currentPosition);
            decision = searcher.stopExploration();
        }else{
            if(finder.isComplete()){
                searcher.setDrone(this, currentInfo, currentPosition);
                decision = searcher.findPOIs(currentDirection);
            }else{
                finder.setDrone(this, currentInfo, currentPosition);
                decision = finder.locateIsland(currentDirection);
            }
        }
        return decision;
    }

    public void receiveResponse(Integer cost, Info currentInfo){
        tracker.adjustBattery(cost);
        this.currentInfo = currentInfo;
    }
}