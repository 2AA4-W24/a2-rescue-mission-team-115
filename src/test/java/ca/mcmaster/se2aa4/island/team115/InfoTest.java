package ca.mcmaster.se2aa4.island.team115;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InfoTest {
    private Info info;
    private JSONObject extras;

    @BeforeEach
    void setUp() {
        extras = new JSONObject();
        extras.put("found", "Ground");
        extras.put("range", 5);
        info = new Info(10, extras, "Exploring");
    }

    @Test
    void testGetCost() {
        assertEquals(10, info.getCost());
    }

    @Test
    void testGetExtras() {
        assertEquals(extras, info.getExtras());
    }

    @Test
    void testGetStatus() {
        assertEquals("Exploring", info.getStatus());
    }

    @Test
    void testEchoFinding() {
        assertEquals("Ground", info.echoFinding());
    }

    @Test
    void testEchoRange() {
        assertEquals(5, info.echoRange());
    }

}
