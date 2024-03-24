package ca.mcmaster.se2aa4.island.team115;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

public class POIMapTest {

    private POIMap POIMap;
    @BeforeEach
    void setUp() {
        POIMap = new POIMap();
    }

    @Test
    void testAddPOIAndRetrieve() {
        Coordinates coordinates = new Coordinates(10, 20);
        POIMap.addPOI("creek1", coordinates);
        assertEquals(coordinates, POIMap.getPOICoordinates("creek1"));
    }

    @Test
    void testSetEmergencySpot() {
        Coordinates emergencyCoordinates = new Coordinates(5, 5);
        POIMap.setEmergencySpot(emergencyCoordinates);
        POIMap.addPOI("creek2", new Coordinates(10, 10));
        String closestCreek = POIMap.calculateClosestCreek();
        assertEquals("creek2", closestCreek);
    }

    @Test
    void testCalculateClosestCreekWithMultiplePOIs() {
        POIMap.addPOI("creek1", new Coordinates(0, 10));
        POIMap.addPOI("creek2", new Coordinates(10, 10));
        POIMap.setEmergencySpot(new Coordinates(0, 0));
        String closestCreek = POIMap.calculateClosestCreek();
        assertEquals("creek1", closestCreek);
    }

    @Test
    void testCalculateClosestCreekWhenNoPOIs() {
        String closestCreek = POIMap.calculateClosestCreek();
        assertEquals(" ", closestCreek);
    }

    @Test
    void testCalculateClosestCreekWithSameDistancePOIs() {
        POIMap.setEmergencySpot(new Coordinates(0, 0));
        POIMap.addPOI("creek1", new Coordinates(0, 10)); 
        POIMap.addPOI("creek3", new Coordinates(10, 0)); 
        String closestCreek = POIMap.calculateClosestCreek();
        assertEquals("creek1", closestCreek);
    }
}
