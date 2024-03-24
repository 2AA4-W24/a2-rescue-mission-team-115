package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DroneTest {
    private Drone drone;
    private POIMap POIMap;


    @BeforeEach
    void setUp() {
        drone = new Drone(1000, "N");
        POIMap = new POIMap();
    }

    @Test
    void checkInitialization() {
        assertEquals(1000, drone.getBattery());
    }

    @Test
    void checkStopExploreOnceLowattery() {
        Drone lowbattdrone = new Drone(10, "N");
        JSONObject decision = lowbattdrone.beginExploration(); 
        String action = decision.getString("action");
        assertEquals("stop", action);
    }
    
    @Test
    void checkAction() {
        Info info = new Info(5, new JSONObject(), "OK");
        Coordinates newCoordinates = new Coordinates(1, 1); 
        drone.updateCoordinates(newCoordinates);
        drone.receiveResponse(info.getCost(), info);
        JSONObject decision = drone.beginExploration();
        assertTrue(decision.has("action"));
    }

    @Test
    void testRecieveResponse() {
        JSONObject response = new JSONObject().put("found", "OUT_OF_RANGE").put("range", 2);
        Info info = new Info(3, response, "OK");
        drone.receiveResponse(info.getCost(), info);
        assertEquals(997, drone.getBattery());
    }  

    @Test
    void flyOverOcean() {        
        JSONObject response = new JSONObject().put("found", "OUT_OF_RANGE").put("range", 2);
        Info info = new Info(3, response, "OK");
        drone.receiveResponse(info.getCost(), info);
        JSONObject decision = drone.beginExploration();
        assertEquals("echo", decision.getString("action"));
    }   

    @Test
    void reachGround() {
        JSONObject response = new JSONObject().put("found", "GROUND").put("range", 2);
        Info info = new Info(3, response, "OK");
        drone.receiveResponse(info.getCost(), info);
        JSONObject decision = drone.beginExploration();
        assertEquals("echo",decision.getString("action"));
    }

    @Test
    void closestCreekNullWithoutInfo() {
        String closestcreek = drone.getClosestCreekID();
        assertNull(closestcreek);
}
}
