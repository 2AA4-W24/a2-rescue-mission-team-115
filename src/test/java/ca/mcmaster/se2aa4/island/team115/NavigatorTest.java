package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NavigatorTest {
    Navigator navigator = new Navigator();
    @Test
    public void testStringToEnum() {
        assertEquals(Direction.N, navigator.stringToEnum("N"));
        assertEquals(Direction.S, navigator.stringToEnum("S"));
        assertEquals(Direction.E, navigator.stringToEnum("E"));
        assertEquals(Direction.W, navigator.stringToEnum("W"));
    }

    @Test
    public void testFlyForwardNorth() {
        navigator.stringToEnum("N"); 
        navigator.flyForward();
        assertEquals(0,navigator.getX());
        assertEquals(1,navigator.getY());
        
    }
    @Test
    public void testFlyForwardSouth() {
        navigator.stringToEnum("S"); 
        navigator.flyForward();
        assertEquals(0,navigator.getX());
        assertEquals(-1,navigator.getY());
        
    }
    @Test
    public void testFlyForwardEast() {
        navigator.stringToEnum("E"); 
        navigator.flyForward();
        assertEquals(1,navigator.getX());
        assertEquals(0,navigator.getY());
        
    }
    @Test
    public void testFlyForwardWest() {
        navigator.stringToEnum("W"); 
        navigator.flyForward();
        assertEquals(-1,navigator.getX());
        assertEquals(0,navigator.getY());
        
    }

    @Test
    public void testGetXGetYInitialPosition() {
        assertEquals(0, navigator.getX());
        assertEquals(0, navigator.getY());
    }



    
}
