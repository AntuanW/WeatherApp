package pl.edu.agh.to2.example.weather.measures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirConditionTest {

    private AirCondition veryGoodAirCondition;
    private AirCondition goodAirCondition;
    private AirCondition passableAirCondition;
    private AirCondition okayAirCondition;
    private AirCondition unhealthyAirCondition;
    private AirCondition veryUnhealthyAirCondition;

    @BeforeEach
    void setUp() {
        veryGoodAirCondition = AirCondition.VERY_GOOD;
        goodAirCondition = AirCondition.GOOD;
        passableAirCondition = AirCondition.PASSABLE;
        okayAirCondition = AirCondition.OKAY;
        unhealthyAirCondition = AirCondition.UNHEALTHY;
        veryUnhealthyAirCondition = AirCondition.VERY_UNHEALTHY;
    }

    @Test
    void testFromPM25() {
        assertEquals(veryGoodAirCondition, AirCondition.fromPM25(11));
        assertEquals(goodAirCondition, AirCondition.fromPM25(35));
        assertEquals(okayAirCondition, AirCondition.fromPM25(59));
        assertEquals(passableAirCondition, AirCondition.fromPM25(83));
        assertEquals(unhealthyAirCondition, AirCondition.fromPM25(179));
        assertEquals(veryUnhealthyAirCondition, AirCondition.fromPM25(180));
    }
}