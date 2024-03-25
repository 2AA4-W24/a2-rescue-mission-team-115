package ca.mcmaster.se2aa4.island.team115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class BatteryTest {

    private BatteryTracker batteryTracker;

    @BeforeEach
    void setUp() {
        batteryTracker = new BatteryTracker(1000);
    }

    @Test
    void testGetBatteryLevelInitial() {
        assertEquals(1000, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryDecrease() {
        batteryTracker.adjustBattery(200);
        assertEquals(800, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryIncrease() {
        batteryTracker.adjustBattery(-200);
        assertEquals(1200, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryToZero() {
        batteryTracker.adjustBattery(1000);
        assertEquals(0, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryBelowZero() {
        batteryTracker.adjustBattery(1500);
        assertEquals(-500, batteryTracker.getBatteryLevel());
    }
}