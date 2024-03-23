package ca.mcmaster.se2aa4.island.team115;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PointsOfInterestMap{
    private Coordinates emergencySpot;
    public void setEmergencySpot(Coordinates coordinate){

            if (emergencySpot == null) {
                emergencySpot = coordinate;
            }

    }
    private Map<String, Coordinates> pois;

    public PointsOfInterestMap(){
        pois = new LinkedHashMap<>();
    }

    public void addPointOfInterest(String uid, Integer X, Integer Y){
        pois.put(uid, new Coordinates(X, Y));
    }

    public Coordinates getPOISCoordinates(String uid){
        return pois.get(uid);
    }

    public String calculateClosestCreek() {
        double smallestDistance = Double.MAX_VALUE;
        String closestCreekUid = null;

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