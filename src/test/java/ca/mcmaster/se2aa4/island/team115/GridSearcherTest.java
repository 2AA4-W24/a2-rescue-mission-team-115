package ca.mcmaster.se2aa4.island.team115;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.json.JSONObject;

import org.json.JSONArray;






public class GridSearcherTest {
    private GridSearcher gridSearcher;
    private Drone drone;
    private Info info;
    private Coordinates coordinates;
    private Action action;

    @BeforeEach
    void setUp() {
        gridSearcher = new GridSearcher();
        drone = mock(Drone.class);
        info = mock(Info.class);
        coordinates = mock(Coordinates.class);
        action = new Action();

        gridSearcher.setDrone(drone, info, coordinates);
    }

    @Test
    void testInitialState() {
        assertFalse(gridSearcher.isComplete());
    }
    @Test
    void testFlyToScanTransition() {
        when(info.getExtras()).thenReturn(new JSONObject("biome","OCEAN"));
        JSONObject decision = gridSearcher.findPOIs(Direction.N);
        assertEquals("scan", decision.getString("action"));
    }
    @Test
    void testFindPOIsInitiatesCorrectAction() {

        Info scanInfo = new Info(1, new JSONObject().put("biomes", new JSONArray("[\"OCEAN\"]")), "OK");
        Coordinates startingCoordinates = new Coordinates(0, 0);
        Drone drone = new Drone(100, "N");
        drone.updateCoordinates(startingCoordinates);
        drone.receiveResponse(1, scanInfo);

        JSONObject action = drone.beginExploration(drone);

        assertEquals("scan", action.getString("action"));
    }

    @Test
    void testStopExplorationResultsInStopAction() {

        Drone drone = new Drone(100, "N");

        // trigger the start of it
        drone.beginExploration(drone); // Simulate beginning of exploration
        JSONObject stopAction = drone.beginExploration(drone); // triggers the stop condition

        // Assert that the action after conditions are met is "stop"
        assertEquals("stop", stopAction.getString("action"));
    }
    @Test
    void testIsCompleteAfterStopExploration() {
        // kinda simple test not that effective but good to have
        gridSearcher.stopExploration();

        boolean isComplete = gridSearcher.isComplete();

        assertTrue(isComplete, "Exploration should be marked as complete after stopping.");
    }
    @Test

    void testDroneMovementBasedOnEnvironment() {

        JSONObject extrasWithObstacle = new JSONObject("{\"found\": \"GROUND\", \"range\": 2}");
        when(info.getExtras()).thenReturn(extrasWithObstacle);

        // Simulate calling findPOIs which should move the drone because of obstacle detection
        JSONObject actionResult = gridSearcher.findPOIs(Direction.N);

        // should do a fly command, expected response but checking if it will take place.
        assertEquals("fly", actionResult.getString("action"), "The drone should decide to fly to avoid the obstacle.");
    }





}
