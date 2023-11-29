package pl.edu.agh.to2.example.weather.measures;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirConditionTest {

    private static AirCondition veryGoodAirCondition;
    private static AirCondition goodAirCondition;
    private static AirCondition okayAirCondition;
    private static AirCondition passableAirCondition;
    private static AirCondition unhealthyAirCondition;
    private static AirCondition veryUnhealthyAirCondition;


    @BeforeAll
    static void setUp() {
        veryGoodAirCondition = AirCondition.VERY_GOOD;
        goodAirCondition = AirCondition.GOOD;
        okayAirCondition = AirCondition.OKAY;
        passableAirCondition = AirCondition.PASSABLE;
        unhealthyAirCondition = AirCondition.UNHEALTHY;
        veryUnhealthyAirCondition = AirCondition.VERY_UNHEALTHY;
    }

    @Test
    void checkIfGasMask() {
        assertFalse(veryGoodAirCondition.checkIfGasMask());
        assertFalse(goodAirCondition.checkIfGasMask());
        assertFalse(okayAirCondition.checkIfGasMask());
        assertFalse(passableAirCondition.checkIfGasMask());
        assertTrue(unhealthyAirCondition.checkIfGasMask());
        assertTrue(veryUnhealthyAirCondition.checkIfGasMask());
    }

    @Test
    void fromPM2_5() {
        assertEquals(veryGoodAirCondition, AirCondition.fromPM25(11));
        assertEquals(goodAirCondition, AirCondition.fromPM25(35));
        assertEquals(okayAirCondition, AirCondition.fromPM25(59));
        assertEquals(passableAirCondition, AirCondition.fromPM25(83));
        assertEquals(unhealthyAirCondition, AirCondition.fromPM25(179));
        assertEquals(veryUnhealthyAirCondition, AirCondition.fromPM25(180));
    }
}