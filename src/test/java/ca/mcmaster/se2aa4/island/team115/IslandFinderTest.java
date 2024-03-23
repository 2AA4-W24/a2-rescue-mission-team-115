package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IslandFinderTest {


    @Test
    public void initalTestNotComplete() {
        IslandFinder finder = new IslandFinder();
        assertFalse(finder.isComplete());
    }
    void testIslandFinderInitialization() {
        IslandFinder islandFinder = new IslandFinder();
        assertNotNull(islandFinder);
    }

    @Test
    void testLocateIslandWithGenericResponse() {
        IslandFinder islandFinder = new IslandFinder();
        Direction initialDirection = Direction.N; 
        JSONObject result = islandFinder.locateIsland(initialDirection);
        assertNotNull(result);
    }

    @Test
    void testIsCompleteAfterOperation() {
        IslandFinder islandFinder = new IslandFinder();
        boolean isCompleteBefore = islandFinder.isComplete();
        islandFinder.locateIsland(Direction.N); 
        boolean isCompleteAfter = islandFinder.isComplete();
        assertEquals(isCompleteBefore, isCompleteAfter);
    }
    private IslandFinder islandFinder;
    private Drone drone;
    private Info info;
    private Coordinates coordinates;
    private Direction direction;


    @Test
void testEchoAndFlySequence() {
     coordinates = new Coordinates(0, 0);
     direction = Direction.N; 
     info = new Info(0, new JSONObject().put("found", "OCEAN"), "OK");
     drone = new Drone(100, "N"); 
     islandFinder = new IslandFinder();
     islandFinder.setDrone(drone, info, coordinates);
    coordinates.setDirection(Direction.N);
    JSONObject initialExtras = new JSONObject().put("found", "OCEAN");
    Info initialInfo = new Info(0, initialExtras, "OK");
    islandFinder.setDrone(drone, initialInfo, coordinates);
    
    JSONObject echoResult = islandFinder.locateIsland(Direction.N);
    assertEquals("echo", echoResult.getString("action"));
    JSONObject groundInfo = new JSONObject().put("found", "GROUND").put("range", 3);
    Info updatedInfo = new Info(5, groundInfo, "OK");
    drone.receiveResponse(updatedInfo); 



}



}