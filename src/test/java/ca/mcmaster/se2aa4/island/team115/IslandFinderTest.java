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

}