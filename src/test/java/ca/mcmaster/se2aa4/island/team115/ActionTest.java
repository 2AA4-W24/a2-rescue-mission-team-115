package ca.mcmaster.se2aa4.island.team115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;

public class ActionTest {
    private Action action;

    @BeforeEach
    void setUp() {
        action = new Action();
    }

    @Test
    void testEcho() {
        action.echo(Direction.N);
        assertEquals("echo", action.getDecision().getString("action"));
        assertEquals("N", action.getDecision().getJSONObject("parameters").getString("direction"));
    }

    @Test
    void testFly() {
        action.fly();
        assertEquals("fly", action.getDecision().getString("action"));
        assertFalse(action.getDecision().has("parameters")); 
    }

    @Test
    void testScan() {
        action.scan();
        assertEquals("scan", action.getDecision().getString("action"));
        assertFalse(action.getDecision().has("parameters"));
    }

    @Test
    void testHeading() {
        action.heading(Direction.E);
        assertEquals("heading", action.getDecision().getString("action"));
        assertEquals("E", action.getDecision().getJSONObject("parameters").getString("direction"));
    }

    @Test
    void testStop() {
        action.stop();
        assertEquals("stop", action.getDecision().getString("action"));
        assertFalse(action.getDecision().has("parameters")); 
    }

    @Test
    void testReset() {
        action.heading(Direction.S);
        assertEquals("S", action.getDecision().getJSONObject("parameters").getString("direction"));
        
        action.reset();
        assertFalse(action.getDecision().has("parameters"));
    }
}
