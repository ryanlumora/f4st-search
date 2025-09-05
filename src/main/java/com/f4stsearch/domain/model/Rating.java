package com.f4stsearch.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    private Double rate;
    private Integer count;

    public Double getRate() {
        return rate;
    }

    public Integer getCount() {
        return count;
    }

    public Rating(Integer count, Double rate) {
        this.count = count;
        this.rate = rate;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Rating() {
    }
}