package com.f4stsearch.domain.logic;

import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EwmaCalculatorTest {

    private final EwmaCalculator calculator = new EwmaCalculator();

    @Test
    void testEmptyList() {
        List<Double> data = new ArrayList<>();
        List<Double> result = calculator.calculateEwma(data, 0.5);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSingleElement() {
        List<Double> data = List.of(10.0);
        List<Double> result = calculator.calculateEwma(data, 0.5);
        assertEquals(List.of(10.0), result);
    }

    @Test
    void testAlphaZero() {
        List<Double> data = List.of(10.0, 20.0, 30.0);
        List<Double> result = calculator.calculateEwma(data, 0.0);
        assertEquals(List.of(10.0, 10.0, 10.0), result);
    }

    @Test
    void testAlphaOne() {
        List<Double> data = List.of(10.0, 20.0, 30.0);
        List<Double> result = calculator.calculateEwma(data, 1.0);
        assertEquals(List.of(10.0, 20.0, 30.0), result);
    }

    @Test
    void testNormalAlpha() {
        List<Double> data = List.of(10.0, 20.0, 30.0);
        List<Double> result = calculator.calculateEwma(data, 0.5);
        List<Double> expected = new ArrayList<>();
        expected.add(10.0);
        expected.add(0.5*20 + 0.5*10);
        expected.add(0.5*30 + 0.5*15);
        assertEquals(expected, result);
    }
}
