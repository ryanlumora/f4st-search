package com.f4stsearch.domain.logic;
import java.util.*;
public class EwmaCalculator {
    public List<Double> calculateEwma(List<Double> data, double alpha) {
        List<Double> ewma = new ArrayList<>();
        if (data == null || data.isEmpty()) return ewma;
        double previous = data.get(0);
        for (double value : data) {
            previous = alpha * value + (1 - alpha) * previous;
            ewma.add(previous);
        }
        return ewma;
    }
}
