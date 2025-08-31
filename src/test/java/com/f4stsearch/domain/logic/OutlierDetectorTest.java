package com.f4stsearch.domain.logic;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutlierDetectorTest {

    private final OutlierDetector detector = new OutlierDetector();

    @Test
    void testEmptyList() {
        List<Double> input = List.of();
        List<Double> result = detector.removeOutliers(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleTwoOrThreeElements() {
        List<Double> input1 = List.of(10.0);
        List<Double> result1 = detector.removeOutliers(input1);
        assertEquals(input1, result1);

        List<Double> input2 = List.of(10.0, 20.0);
        List<Double> result2 = detector.removeOutliers(input2);
        assertEquals(input2, result2);

        List<Double> input3 = List.of(10.0, 20.0, 30.0);
        List<Double> result3 = detector.removeOutliers(input3);
        assertEquals(input3, result3);
    }

    @Test
    void testNoOutliers() {
        List<Double> input = List.of(10.0, 12.0, 14.0, 15.0, 13.0);
        List<Double> result = detector.removeOutliers(input);
        assertEquals(input.size(), result.size());
        assertTrue(result.containsAll(input));
    }

    @Test
    void testWithOutliers() {
        List<Double> input = List.of(10.0, 12.0, 14.0, 150.0, 13.0);
        List<Double> result = detector.removeOutliers(input);
        assertFalse(result.contains(150.0));
        assertEquals(4, result.size());
    }

    @Test
    void testNegativeValues() {
        List<Double> input = List.of(-10.0, -5.0, 0.0, 5.0, 50.0);
        List<Double> result = detector.removeOutliers(input);
        assertFalse(result.contains(50.0));
        assertEquals(4, result.size());
    }
}
