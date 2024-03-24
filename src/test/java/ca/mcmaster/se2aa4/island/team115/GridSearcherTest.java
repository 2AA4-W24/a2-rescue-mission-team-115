package ca.mcmaster.se2aa4.island.team115;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.json.JSONObject;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

import org.json.JSONArray;

import org.mockito.Mockito;


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
    public void testFindPOIsWithCreekDiscovery() {
        Drone mockDrone = Mockito.mock(Drone.class);
        Info mockInfo = Mockito.mock(Info.class);
        Coordinates mockCoordinates = Mockito.mock(Coordinates.class);


        GridSearcher gridSearcher = new GridSearcher();

        JSONObject extras = new JSONObject();
        extras.put("creeks", new JSONArray().put("CREEK_ID_123"));
        when(mockInfo.getExtras()).thenReturn(extras);

        gridSearcher.setDrone(mockDrone, mockInfo, mockCoordinates);

        Direction testDirection = Direction.N;

        JSONObject decision = gridSearcher.findPOIs(testDirection);


        assertEquals("scan", decision.getString("action"));
    }

   
   @Test
   void testGridSearcherInitialization() {
       GridSearcher gridSearcher = new GridSearcher();
       assertFalse(gridSearcher.isComplete(), "GridSearcher should initialize with searching not complete.");
       // Directly asserting the initial state is "Fly" is not possible without reflection since `state` is a private field.
       // Indirectly verify the initial state through behavior.
       JSONObject decision = gridSearcher.findPOIs(Direction.N);
       assertEquals("scan", decision.getString("action"), "Initial state should be Fly, leading to a scan action.");
   }

    @Test
    void testStopExploration() {
        GridSearcher gridSearcher = new GridSearcher();
        JSONObject decision = gridSearcher.stopExploration();
        assertEquals("stop", decision.getString("action"), "Stopping exploration should result in a 'stop' action.");
    }
    @Test
    void testSetDroneInfoCoordinates() {
        Drone mockDrone = mock(Drone.class);
        Info mockInfo = mock(Info.class);
        Coordinates mockCoordinates = mock(Coordinates.class);

        GridSearcher gridSearcher = new GridSearcher();
        gridSearcher.setDrone(mockDrone, mockInfo, mockCoordinates);


        JSONObject decision = gridSearcher.findPOIs(Direction.N);
        assertNotNull(decision, "Decision should not be null if drone, info, and coordinates are set correctly.");
    }
//    @Test
//    void testPOICheckingAndLogging() {
//        Drone mockDrone = mock(Drone.class);
//        Info mockInfo = mock(Info.class);
//        Coordinates mockCoordinates = new Coordinates(0, 0);
//
//
//        JSONObject extrasWithPOI = new JSONObject();
//        extrasWithPOI.put("creeks", new JSONArray().put("CREEK_ID_123"));
//        extrasWithPOI.put("sites", new JSONArray().put("SITE_ID_456"));
//        when(mockInfo.getExtras()).thenReturn(extrasWithPOI);
//
//        GridSearcher gridSearcher = new GridSearcher();
//        gridSearcher.setDrone(mockDrone, mockInfo, mockCoordinates);
//        gridSearcher.findPOIs(Direction.N); // Trigger POI checking
//
//
//        String closestCreek = gridSearcher.getClosestCreek();
//        assertEquals(" ", closestCreek, "Creek should be logged and identifiable as the closest one.");
//    }
    @Test
    void testBiomeDataHandlingInScan() {
        Drone mockDrone = mock(Drone.class);
        Info mockInfo = mock(Info.class);
        Coordinates mockCoordinates = mock(Coordinates.class);

        // Prepare the scenario where scanning detects an ocean
        JSONObject extrasWithOcean = new JSONObject();
        extrasWithOcean.put("biomes", new JSONArray().put("OCEAN"));
        when(mockInfo.getExtras()).thenReturn(extrasWithOcean);

        GridSearcher gridSearcher = new GridSearcher();
        gridSearcher.setDrone(mockDrone, mockInfo, mockCoordinates);

        JSONObject decision = gridSearcher.findPOIs(Direction.N);
        assertEquals("scan", decision.getString("action"), "Encountering an ocean biome should lead to an scan action.");
    }

    // ##############################
    @Test
    void testBasicFlightAndCoordinateUpdate() {
        Drone mockDrone = Mockito.mock(Drone.class);
        Coordinates initialCoordinates = new Coordinates(0, 0);
        GridSearcher gridSearcher = new GridSearcher();
        gridSearcher.setDrone(mockDrone, new Info(0, new JSONObject(), ""), initialCoordinates);

        JSONObject decision = gridSearcher.findPOIs(Direction.E);
        assertEquals("scan", decision.getString("action"), "GridSearcher should initiate a 'scan' action.");

    }

    @Test
    void testUTurnExecution() {
        Drone mockDrone = Mockito.mock(Drone.class);
        Info mockInfo = Mockito.mock(Info.class);
        GridSearcher gridSearcher = new GridSearcher();
        gridSearcher.setDrone(mockDrone, mockInfo, new Coordinates(0, 0));


    }

    @Test
    void testStateTransitionsAndSpecialTurns() {
        Drone mockDrone = Mockito.mock(Drone.class);
        Info mockInfo = Mockito.mock(Info.class);
        GridSearcher gridSearcher = new GridSearcher();
        gridSearcher.setDrone(mockDrone, mockInfo, new Coordinates(0, 0));

        
    }
}
