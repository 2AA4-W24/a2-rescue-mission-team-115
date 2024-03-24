package ca.mcmaster.se2aa4.island.team115;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.json.JSONObject;

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
}
