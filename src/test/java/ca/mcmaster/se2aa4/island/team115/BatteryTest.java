package ca.mcmaster.se2aa4.island.team115;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class BatteryTest {

    private BatteryTracker batteryTracker;

    @BeforeEach
    void setUp() {
        batteryTracker = new BatteryTracker(100);
    }

    @Test
    void testGetBatteryLevelInitial() {
        assertEquals(100, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryDecrease() {
        batteryTracker.adjustBattery(20);
        assertEquals(80, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryIncrease() {
        batteryTracker.adjustBattery(-20);
        assertEquals(120, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryToZero() {
        batteryTracker.adjustBattery(100);
        assertEquals(0, batteryTracker.getBatteryLevel());
    }

    @Test
    void testAdjustBatteryBelowZero() {
        batteryTracker.adjustBattery(150);
        assertEquals(-50, batteryTracker.getBatteryLevel());
    }
}
