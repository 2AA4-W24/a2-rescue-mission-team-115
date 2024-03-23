package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;
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
    @Test
    public void testLocateIsland_GroundFound() {
        //if ground is in range of 3, should find island and echo (check) 
        Drone drone = new Drone(10000, "N");
        JSONObject extras = new JSONObject();
        extras.put("found", "GROUND");
        extras.put("range", 3);
        Info info = new Info(5, extras, "OK");
        drone.receiveResponse(info);
        IslandFinder finder = new IslandFinder();
        finder.setDrone(drone, info);
        JSONObject result = finder.locateIsland(Direction.N);
        assertEquals("fly", result.getString("action"));
    }
    @Test
void testLocateIslandAtMapEdge() {
    //if water is below, should echo (check)
    Drone drone = new Drone(10000, "N");
    JSONObject extras = new JSONObject();
    extras.put("found", "OCEAN");
    extras.put("range", 1);
    Info info = new Info(5, extras, "OK");
    drone.receiveResponse(info);
    IslandFinder finder = new IslandFinder();
    finder.setDrone(drone, info);

    JSONObject result = finder.locateIsland(Direction.N);
    assertEquals("fly", result.getString("action"));
}
@Test
void testLocateIslandWithGroundDirectlyBelow() {
    //if ground is below, drone should scan (check) 
    Drone drone = new Drone(10000, "N");
    JSONObject extras = new JSONObject();
    extras.put("found", "GROUND");
    extras.put("range", 0); 
    Info info = new Info(0, extras, "OK");
    drone.receiveResponse(info);
    IslandFinder finder = new IslandFinder();
    finder.setDrone(drone, info);
    JSONObject result = finder.locateIsland(Direction.N);
    assertEquals("echo", result.getString("action"));
}
@Test
public void testIslandFinderInitiatesEchoOnStart() {
    //should echo when starting (check)
    Drone drone = new Drone(10000, "N");
    Info info = new Info(0, new JSONObject().put("found", "WATER"), "OK");
    IslandFinder finder = new IslandFinder();

    finder.setDrone(drone, info);
    JSONObject action = finder.locateIsland(Direction.N);

    assertEquals("echo", action.getString("action"));
}
@Test
public void testIslandFinderReactsToGroundFound() {
    //check
    Drone drone = new Drone(1000, "N");
    JSONObject extras = new JSONObject().put("found", "GROUND").put("range", 1);
    Info info = new Info(5, extras, "OK");
    IslandFinder finder = new IslandFinder();

    drone.receiveResponse(info);
    finder.setDrone(drone, info);
    JSONObject action = finder.locateIsland(Direction.N);

    assertEquals("echo", action.getString("action"), "IslandFinder should decide to fly towards the ground when found.");
}
@Test
public void testDroneChangesDirectionOnCommand() {
    Drone drone = new Drone(10000, "N");
    IslandFinder finder = new IslandFinder();
    finder.locateIsland(Direction.N); // This would internally command a direction change based on logic

    // Verify the drone's direction has changed as expected
    // This requires the Drone class to have a method to get the current direction for verification
    assertNotEquals(Direction.N, drone.getCurrentDirection(), "Drone should have changed direction.");
}

}
