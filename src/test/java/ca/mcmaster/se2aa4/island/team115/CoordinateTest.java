package ca.mcmaster.se2aa4.island.team115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class CoordinatesTest {

    private Coordinates coordinates;


    @BeforeEach
    void setUp() {
        coordinates = new Coordinates(0, 0);
    }

    @Test
    void testInitialPosition() {
        assertEquals(0, coordinates.getX());
        assertEquals(0, coordinates.getY());
    }

    @Test
    void testSetDirection() {
        coordinates.setDirection(Direction.N);
        assertEquals(Direction.N, coordinates.getDirection());
    }

    @Test
    void testFlyForwardNorth() {
        coordinates.setDirection(Direction.N);
        coordinates.flyForward();
        assertEquals(1, coordinates.getY());
        assertEquals(0, coordinates.getX());
    }

    @Test
    void testFlyForwardSouth() {
        coordinates.setDirection(Direction.S);
        coordinates.flyForward();
        assertEquals(-1, coordinates.getY());
        assertEquals(0, coordinates.getX());
    }

    @Test
    void testFlyForwardEast() {
        coordinates.setDirection(Direction.E);
        coordinates.flyForward();
        assertEquals(0, coordinates.getY());
        assertEquals(1, coordinates.getX());
    }

    @Test
    void testFlyForwardWest() {
        coordinates.setDirection(Direction.W);
        coordinates.flyForward();
        assertEquals(0, coordinates.getY());
        assertEquals(-1, coordinates.getX());
    }

    @Test
    void testTurnLeftFromNorth() {
        coordinates.setDirection(Direction.N);
        coordinates.turnLeft();
        assertEquals(Direction.W, coordinates.getDirection()); 
        assertEquals(1, coordinates.getY());
        assertEquals(-1, coordinates.getX());
    }

    @Test
    void testTurnLeftFromEast() {
        coordinates.setDirection(Direction.E);
        coordinates.turnLeft();
        assertEquals(Direction.N, coordinates.getDirection()); 
        assertEquals(1, coordinates.getY());
        assertEquals(1, coordinates.getX());
    }
    @Test
    void testTurnLeftFromSouth() {
        coordinates.setDirection(Direction.S);
        coordinates.turnLeft();
        assertEquals(Direction.E, coordinates.getDirection()); 
        assertEquals(-1, coordinates.getY());
        assertEquals(1, coordinates.getX());
    }

    @Test
    void testTurnLeftFromWest() {
        coordinates.setDirection(Direction.W);
        coordinates.turnLeft();
        assertEquals(Direction.S, coordinates.getDirection()); 
        assertEquals(-1, coordinates.getY());
        assertEquals(-1, coordinates.getX());
    }

    @Test
    void testTurnRightFromNorth() {
        coordinates.setDirection(Direction.N);
        coordinates.turnRight();
        assertEquals(Direction.E, coordinates.getDirection()); 
        assertEquals(1, coordinates.getY());
        assertEquals(1, coordinates.getX());

    }
    @Test
    void testTurnRightFromEast() {
        coordinates.setDirection(Direction.E);
        coordinates.turnRight();
        assertEquals(Direction.S, coordinates.getDirection()); 
        assertEquals(-1, coordinates.getY());
        assertEquals(1, coordinates.getX());

    }
    @Test
    void testTurnRightFromSouth() {
        coordinates.setDirection(Direction.S);
        coordinates.turnRight();
        assertEquals(Direction.W, coordinates.getDirection()); 
        assertEquals(-1, coordinates.getY());
        assertEquals(-1, coordinates.getX());

    }
    @Test
    void testTurnRightFromWest() {
        coordinates.setDirection(Direction.W);
        coordinates.turnRight();
        assertEquals(Direction.N, coordinates.getDirection()); 
        assertEquals(1, coordinates.getY());
        assertEquals(-1, coordinates.getX());

    }
}
