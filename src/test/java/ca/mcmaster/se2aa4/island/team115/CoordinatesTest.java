package ca.mcmaster.se2aa4.island.team115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class CoordinatesTest {
    private Coordinates coordinates;

    @BeforeEach
    void setUp() {
        // Initialize Coordinates with a known state
        coordinates = new Coordinates(5, 10);
    }

    @Test
    void testGetX() {
        assertEquals(5, coordinates.getX());
    }

    @Test
    void testGetY() {
        assertEquals(10, coordinates.getY());
    }

    @Test
    void testIncrementX() {
        coordinates.incrementX();
        assertEquals(6, coordinates.getX());
    }

    @Test
    void testIncrementY() {
        coordinates.incrementY();
        assertEquals(11, coordinates.getY());
    }

    @Test
    void testDecrementX() {
        coordinates.decrementX();
        assertEquals(4, coordinates.getX());
    }

    @Test
    void testDecrementY() {
        coordinates.decrementY();
        assertEquals(9, coordinates.getY());
    }

    @Test
    void testMultipleIncrementsAndDecrements() {
        coordinates.incrementX();
        coordinates.incrementX();
        coordinates.decrementX();
        coordinates.incrementY();
        coordinates.incrementY();
        coordinates.decrementY();
        assertEquals(6, coordinates.getX());
        assertEquals(11, coordinates.getY());
    }
}
