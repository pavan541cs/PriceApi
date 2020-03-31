package com.target.price.model;

public class Price {
    String range;
    int min;
    int max;

    public Price() {
        super();
    }

    public Price(String range, int min, int max) {
        this.range = range;
        this.min = min;
        this.max = max;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
