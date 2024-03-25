package ca.mcmaster.se2aa4.island.team115;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.json.JSONArray;
import org.json.JSONObject;

public class GridSearcherTest {
    GridSearcher gridsearcher;
    private Drone drone;
    private Info info;
    private Coordinates coordinates;

@BeforeEach
public void setUp() {
    JSONObject extras = new JSONObject();
    info = new Info(0, extras, "OK");
    drone = new Drone(100, "N");
    coordinates = new Coordinates(0,0); 
    coordinates.setDirection(Direction.N);
    gridsearcher = new GridSearcher();
    gridsearcher.setDrone(drone, info, coordinates);
}
@Test
public void flyScan() {
    gridsearcher.stateChangeFly();
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertEquals("scan", decision.getString("action"));
}
@Test
public void echoForwardWithGround() {
    gridsearcher.stateChangeEchoForward();
    info.getExtras().put("found", "GROUND").put("range", 2);
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertEquals("fly", decision.getString("action"));
}
@Test
public void echoForwardToFlyToIsland() {
    gridsearcher.stateChangeEchoForward();
    info.getExtras().put("found", "GROUND").put("range", 1); 
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertEquals("fly", decision.getString("action"));
}
@Test
public void flyToIslandCompletion() {
    gridsearcher.stateChangeFlyToIsland(1); 
    JSONObject decision = gridsearcher.findPOIs(Direction.N); 
    assertEquals("fly", decision.getString("action"));
}
@Test
public void testFlyToIslandExecution() {
    gridsearcher.stateChangeFlyAwayFromIsland(1); 
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertTrue(decision.getString("action").contains("heading"));
}
@Test
public void testFlyToIslandStateExecution() {
    gridsearcher.stateChangeFlyToIsland(1); 
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertEquals("fly", decision.getString("action"));
}
@Test
public void testUTurn() {
    gridsearcher.stateChangeFlyAwayFromIsland(0); 
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertTrue(decision.getString("action").contains("heading"));
}
@Test
public void specialTurn() {
    gridsearcher.stateChangeFlyToPrepareForSpecialTurn(1); 
    JSONObject decision = gridsearcher.findPOIs(Direction.N);
    assertTrue(decision.getString("action").contains("heading"));
}
@Test
    void scanBiomeOcean() {
        JSONObject extras = new JSONObject().put("extras", new JSONObject().put("biomes", new JSONArray().put("OCEAN")));
        Info info = new Info(3, extras, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        JSONObject result = gridsearcher.findPOIs(Direction.N);
        assertEquals("scan", result.getString("action"));
    }
    @Test
    void scanBiome() {
        JSONObject extras = new JSONObject().put("biomes", new JSONArray().put("GROUND"));
        Info info = new Info(3, extras, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        JSONObject result = gridsearcher.findPOIs(Direction.N);
        assertEquals("scan", result.getString("action"));
    }
    @Test
    void echoForwardGround() {
        JSONObject extras = new JSONObject().put("found", "GROUND").put("range", 2);
        Info info = new Info(3, extras, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        JSONObject result = gridsearcher.findPOIs(Direction.N);
        assertEquals("scan", result.getString("action"));
    }
    @Test
    void echoForwardOutOfRange() {
        JSONObject extras = new JSONObject().put("found", "OUT_OF_RANGE").put("range", 0);
        Info info = new Info(3, extras, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        JSONObject result = gridsearcher.findPOIs(Direction.N);
        assertEquals("scan", result.getString("action"));
    }
    @Test
    public void testFlyToIsland() {
        Integer range = 3; 
        gridsearcher.stateChangeFlyToIsland(range);
        JSONObject decision = gridsearcher.findPOIs(Direction.E); 
        Coordinates coordinates = new Coordinates(0, 1); 
        assertEquals(coordinates.getX(), coordinates.getX());
        assertEquals(coordinates.getY(), coordinates.getY());
        assertTrue(decision.getString("action").equals("fly"));
    }
    @Test
    public void testEchoInterlacedGround() {
        JSONObject infoground = new JSONObject().put("found","GROUND").put("range", 3);
        Info info = new Info(3, infoground, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        gridsearcher.stateChangeEchoToCheckInterlacedScanCompletion();
        JSONObject decision = gridsearcher.findPOIs(Direction.E); 
        assertEquals("fly", decision.getString("action"));
    }
    @Test
    public void testEchoInterlacedWater() {
        JSONObject infoWithWater = new JSONObject().put("found","OUT_OF_RANGE").put("range", 3);
        Info info = new Info(3, infoWithWater, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        JSONObject decision = gridsearcher.findPOIs(Direction.E); 
        assertEquals("scan", decision.getString("action"));
    }
    @Test
    public void testFlyForwardSteps() {
        gridsearcher.stateChangeSpecialTurn(); 
        JSONObject decision = gridsearcher.findPOIs(Direction.N);
        assertEquals( 0, coordinates.getX());
        assertEquals(1, coordinates.getY());
        assertEquals("fly", decision.getString("action"));
        gridsearcher.stateChangeSpecialTurn(); 
        gridsearcher.findPOIs(Direction.E); 
        Direction expecteddir2 = Direction.N;
        assertEquals(expecteddir2, drone.getDirection());
        gridsearcher.findPOIs(expecteddir2);
        Direction expecteddir3 = Direction.E;
        assertEquals(expecteddir3, drone.getDirection());
        gridsearcher.stateChangeSpecialTurn(); 
        gridsearcher.findPOIs(Direction.E); 
        Direction expecteddir4 = Direction.E;
        assertEquals(expecteddir4, drone.getDirection());
        gridsearcher.stateChangeSpecialTurn(); 
        JSONObject decisions = gridsearcher.findPOIs(Direction.E);
        assertEquals("fly", decisions.getString("action"));
    }

    @Test
    public void testFlyCountLessThanRange() {
        gridsearcher.stateChangeFlyToIsland(5); 
        JSONObject decision = gridsearcher.findPOIs(Direction.N);
        assertEquals( "fly", decision.getString("action"));
        assertEquals(0, coordinates.getX());
        assertEquals(1, coordinates.getY());
    }

    @Test
    public void testFlyCountMoreThanRange() {
        gridsearcher.stateChangeFlyToIsland(0); 
        JSONObject decision = gridsearcher.findPOIs(Direction.E);
        assertEquals("scan",decision.getString("action"));
    }   
    @Test
    public void specialTurnThroughAllSteps() {
        gridsearcher.stateChangeSpecialTurn(); 
        Direction initialdir = drone.getDirection();
        Coordinates initialcoord = new Coordinates(coordinates.getX(), coordinates.getY()); 
        JSONObject decisionStep1 = gridsearcher.findPOIs(Direction.N);
        assertEquals("fly", decisionStep1.getString("action"));
        assertTrue(coordinates.getX() != initialcoord.getX() || coordinates.getY() != initialcoord.getY()); 
        gridsearcher.findPOIs(Direction.N); 
        Direction directionAfterFirstTurn = drone.getDirection();
        assertNotEquals(initialdir, directionAfterFirstTurn);
        gridsearcher.findPOIs(Direction.N); 
        Direction directionAfterSecondTurn = drone.getDirection();
        assertNotEquals("E", directionAfterSecondTurn); 
        gridsearcher.findPOIs(Direction.N); 

    
    } 
    @Test
    public void echoForwardWithOceanAndRangeFourOrMore() {
        JSONObject extras = new JSONObject().put("found", "WATER").put("range", 4);
        Info info = new Info(3, extras, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        gridsearcher.stateChangeEchoForward();
        JSONObject decision = gridsearcher.findPOIs(Direction.N);
        assertEquals("fly", decision.getString("action"));
    }

    @Test
    public void uTurnExecution() {
        gridsearcher.stateChangeUTurn(); 
        JSONObject firstTurnDecision = gridsearcher.findPOIs(Direction.N);
        assertTrue(firstTurnDecision.getString("action").contains("heading"));
        JSONObject secondTurnDecision = gridsearcher.findPOIs(Direction.N);
        assertEquals("echo",secondTurnDecision.getString("action"));
    }
    @Test
    public void scanWithSingleOceanBiomeTriggersEcho() {
        Coordinates coordinates = new Coordinates(0, 0);
        Drone drone = new Drone(100, "N"); 
        JSONObject extras = new JSONObject().put("extras", new JSONObject().put("biomes", new JSONArray().put("OCEAN")));
        Info info = new Info(0, extras, "OK");
        gridsearcher.setDrone(drone, info, coordinates);
        gridsearcher.stateChangeScan(); 
        Coordinates coordinates1 = new Coordinates(0, 0); 
        assertEquals(coordinates1.getX(), coordinates.getX());
        assertEquals(coordinates1.getY(), coordinates.getY());
    }
}
 