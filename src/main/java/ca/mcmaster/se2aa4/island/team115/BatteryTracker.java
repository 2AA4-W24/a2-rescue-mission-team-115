package ca.mcmaster.se2aa4.island.team115;

 public class BatteryTracker {
    private int currBattery;

    public BatteryTracker(int currBattery){
        this.currBattery = currBattery;
    }
    
    public int getBatteryLevel(){
        return currBattery;
    }

    public void adjustBattery(int cost) {
        currBattery = currBattery - cost;
    }

    //method for checking if battery level is low
    //Discuss with team about changing static methods to instance methods and create an object, and change extractInfo accordingly
}
