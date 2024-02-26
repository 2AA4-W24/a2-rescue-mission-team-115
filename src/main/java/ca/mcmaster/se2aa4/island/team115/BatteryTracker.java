package ca.mcmaster.se2aa4.island.team115;

 public class BatteryTracker {
    private static int currBattery;

    public static void setBatteryLevel(int Battery){
        currBattery = Battery;
    }
    
    public static int getBatteryLevel(){
        return currBattery;
    }

    public static void adjustBattery(int cost) {
        currBattery = currBattery - cost;
    }

    //method for checking if battery level is low
    //Consider changing static methods to instance methods and create an object, and change extractInfo accordingly
}
