package ca.mcmaster.se2aa4.island.team115;


import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IslandFinderTest {
    private IslandFinder islandfinder;
    private Drone drone;
    private Info info;
    private Coordinates coordinates;
    @BeforeEach
    void setUp() {
        coordinates = new Coordinates(0, 0);
        drone = new Drone(1000, "N");
        islandfinder = new IslandFinder();
        drone.updateCoordinates(coordinates);
        coordinates.setDirection(Direction.N);
        islandfinder.setDrone(drone, info, coordinates);
    }

    @Test
    void initialEcho() {
        JSONObject result = islandfinder.locateIsland(Direction.N);
        assertEquals("echo", result.getString("action"));
        assertEquals("N", result.getJSONObject("parameters").getString("direction"));
    }               
        
    @Test
    public void initalComplete() {
        IslandFinder islandfinder = new IslandFinder();
        assertFalse(islandfinder.isComplete());
    }

    @Test
    void checkLocateIslandNotNull() {
        IslandFinder islandfinder = new IslandFinder();
        Direction initialdirection = Direction.N; 
        JSONObject result = islandfinder.locateIsland(initialdirection);
        assertNotNull(result);
    }

    @Test
    void checkcCompleteAfter() {
        IslandFinder islandFinder = new IslandFinder();
        boolean isCompleteBefore = islandFinder.isComplete();
        islandFinder.locateIsland(Direction.N); 
        boolean isCompleteAfter = islandFinder.isComplete();
        assertEquals(isCompleteBefore, isCompleteAfter);
    }

    @Test
    void echoForwardAndFoundGround() {
        JSONObject response = new JSONObject().put("found", "GROUND").put("range", 2);
        Info info = new Info(3, response, "OK");
        islandfinder.setDrone(drone, info, coordinates);
        islandfinder.stateChangeEchoForward();
        JSONObject result = islandfinder.locateIsland(Direction.N);
        assertEquals("fly", result.getString("action"));
    }
    @Test
    void echoForwardEmpty() {
        JSONObject response = new JSONObject().put("found", "OUT_OF_RANGE").put("range", 2);
        Info info = new Info(3, response, "OK");
        islandfinder.setDrone(drone, info, coordinates);
        islandfinder.stateChangeEchoForward();
        JSONObject result = islandfinder.locateIsland(Direction.N);
        assertEquals("echo", result.getString("action"));
        }
    @Test
    void echoRightThenEchoLeftEmpty() {
        JSONObject response = new JSONObject().put("found", "OUT_OF_RANGE").put("range", 2);
        Info info = new Info(3, response, "OK");
        islandfinder.setDrone(drone, info, coordinates);
        islandfinder.stateChangeEchoRight();
        JSONObject result = islandfinder.locateIsland(Direction.N);
        assertEquals("echo", result.getString("action"));
        }
    @Test
    void turnRightThenEchoForward() {
        islandfinder.stateChangeTurnRight();
        JSONObject result = islandfinder.locateIsland(Direction.N);
        assertEquals("echo", result.getString("action"));
    }
    @Test
    void turnLeftThenEchoForward() {
        islandfinder.stateChangeTurnLeft();
        JSONObject result = islandfinder.locateIsland(Direction.N);
        assertEquals("echo", result.getString("action"));
    }

    @Test
    void flyToIsland() {
        JSONObject response = new JSONObject().put("found", "GROUND").put("range", 2);
        Info info = new Info(3, response, "OK");
        islandfinder.setDrone(drone, info, coordinates);
        islandfinder.stateChangeFlyIsland(2);
        islandfinder.locateIsland(Direction.N);
        islandfinder.locateIsland(Direction.N); 
        assertTrue(islandfinder.isComplete());
    }

    @Test
    void turnRightWhenGroundFoundRight() {
        JSONObject response = new JSONObject().put("found", "GROUND").put("range", 2);
        Info info = new Info(3, response, "OK");
        islandfinder.setDrone(drone, info, coordinates);
        islandfinder.stateChangeEchoRight();
        JSONObject result = islandfinder.locateIsland(Direction.N.rightDir());
        assertEquals("heading", result.getString("action"));
    }
    @Test
    void turnLeftWhenGroundFoundLeft() {
        JSONObject response = new JSONObject().put("found", "GROUND").put("range", 2);
        Info info = new Info(3, response, "OK");
        islandfinder.setDrone(drone, info, coordinates);
        islandfinder.stateChangeEchoLeft();
        JSONObject result = islandfinder.locateIsland(Direction.N.leftDir());
        assertEquals("heading", result.getString("action"));
    }
}

