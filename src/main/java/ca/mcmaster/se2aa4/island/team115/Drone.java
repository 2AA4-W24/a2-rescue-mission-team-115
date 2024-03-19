package ca.mcmaster.se2aa4.island.team115;

import org.apache.xerces.impl.dv.xs.DecimalDV;
import org.json.JSONObject;


public class Drone {
    private BatteryTracker currentBattery;
    private Coordinates currentPosition;
    private Direction currentDirection;
    private IslandFinder finder;
    private Info currentInfo;
    
    public Drone (Integer initialBattery, String direction){
        currentBattery = new BatteryTracker(initialBattery);
        currentDirection = Direction.valueOf(direction);
        currentPosition = new Coordinates(0,0);
    }


    public JSONObject beginExploration(){
        finder = new IslandFinder();
        return finder.locateIsland(currentDirection);
    }
    public void updateStatus(Integer cost, String status){

    }
    public void receiveResponse(Info info){
        currentInfo = info;
        finder.updateState(info);
    }
        
    // private void setDroneDirection(Direction direction){
        
    // }
    // private void setInitialBattery(){
    // }
}
