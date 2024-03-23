package ca.mcmaster.se2aa4.island.team115;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

public class TranslatorTest {
     private Translator translator;

    @BeforeEach
    public void setUp() {
        translator = new Translator();
    }

    @Test
    public void testTranslateValidResponse() {
        JSONObject response = new JSONObject();
        response.put("cost", 100);
        JSONObject extras = new JSONObject();
        extras.put("found", "OUT_OF_RANGE");
        extras.put("range","0");
        response.put("extras",extras);
        response.put("status","OK");
        
        Info info = translator.translate(response);

        assertEquals(100, info.getCost());
        assertEquals("OK", info.getStatus());
        JSONObject extractextras = info.getExtras();
        assertNotNull(info.getExtras());
        assertEquals("OUT_OF_RANGE", extractextras.getString("found"));
        assertEquals(0, extractextras.getInt("range"));
    }

    @Test
    public void testTranslateExtrasEmpty() {
        JSONObject response = new JSONObject();
        response.put("cost", 100);
        response.put("extras", new JSONObject()); 
        response.put("status", "OK");
    
        Info info = translator.translate(response);

        assertEquals(100, info.getCost());
        assertEquals("OK", info.getStatus());
        JSONObject extras = info.getExtras();
        assertNotNull(extras); 
        assertTrue(extras.isEmpty()); 
    }
}