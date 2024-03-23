package ca.mcmaster.se2aa4.island.team115;

import java.util.LinkedHashMap;
import java.util.Map;

public class POIMap{
    private Coordinates emergencySpot = new Coordinates(0,0);
    //if we don't find the emergencySpot, it will return the creek that is closest to the starting point.
    private Map<String, Coordinates> pois;

    public POIMap(){
        pois = new LinkedHashMap<>();
    }

    public void setEmergencySpot(Coordinates coordinate){
        emergencySpot = coordinate;
    }

    public void addPOI(String uid, Coordinates coordinates){
        pois.put(uid, coordinates);
    }

    public Coordinates getPOICoordinates(String uid){
        return pois.get(uid);
    }

    public String calculateClosestCreek() {
        double smallestDistance = Double.MAX_VALUE;
        String closestCreekUid = " ";

        for (Map.Entry<String, Coordinates> entry : pois.entrySet()) {
            String uid = entry.getKey();
            Coordinates coordinates = entry.getValue();

            double distance = calculateDistanceFromEmergencySpot(coordinates);


            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestCreekUid = uid;
            }
        }

        return closestCreekUid; 
    }


    private double calculateDistanceFromEmergencySpot(Coordinates coordinates) {

        double deltaX = coordinates.getX() - emergencySpot.getX();
        double deltaY = coordinates.getY() - emergencySpot.getY();


        double deltaXSquare = deltaX * deltaX;
        double deltaYSquare = deltaY * deltaY;

        double sumOfSquares = deltaXSquare + deltaYSquare;

        double distance = Math.sqrt(sumOfSquares);

        return distance;
    }


}