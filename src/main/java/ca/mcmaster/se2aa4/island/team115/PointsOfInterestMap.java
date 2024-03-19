package ca.mcmaster.se2aa4.island.team115;

import java.util.HashMap;
import java.util.Map;

public class PointsOfInterestMap{
    private Map<String, Coordinates> pois;

    public PointsOfInterestMap(){
        pois = new HashMap<>();
    }

    public void addPointOfInterest(String uid, Integer X, Integer Y){
        pois.put(uid, new Coordinates(X, Y));
    }

    public Coordinates getPOISCoordinates(String uid){
        return pois.get(uid);
    }


}