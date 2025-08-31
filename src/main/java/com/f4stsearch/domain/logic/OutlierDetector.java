package com.f4stsearch.domain.logic;

import java.util.*;
import java.util.stream.Collectors;

public class OutlierDetector {

    public List<Double> removeOutliers(List<Double> values) {
        if (values.size() <= 3) return new ArrayList<>(values);

        List<Double> sorted = values.stream().sorted().collect(Collectors.toList());

        double firstQuartile = percentile(sorted, 25);
        double thirdQuartile = percentile(sorted, 75);
        double iqr = thirdQuartile - firstQuartile;

        double lowerBound = firstQuartile - 1.5 * iqr;
        double upperBound = thirdQuartile + 1.5 * iqr;

        return sorted.stream()
                .filter(v -> v >= lowerBound && v <= upperBound)
                .collect(Collectors.toList());
    }

    private double percentile(List<Double> sorted, double percent) {
        if (sorted.isEmpty()) return 0;

        int index = (int) Math.ceil(percent / 100.0 * sorted.size()) - 1;
        index = Math.max(0, Math.min(index, sorted.size() - 1));
        return sorted.get(index);
    }
}
