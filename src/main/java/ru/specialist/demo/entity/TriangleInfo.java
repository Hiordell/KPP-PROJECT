package ru.specialist.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TriangleInfo {

    private int perimeter;
    private double square;
    @JsonProperty
    Integer sumOfSides;
    @JsonProperty
    Integer maxSide;
    @JsonProperty
    Integer minSide;

    public TriangleInfo() {
    }

    public TriangleInfo(int perimeter, double square) {
        this.perimeter = perimeter;
        this.square = square;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public void setSumOfSides(Integer sumOfSides) {
        this.sumOfSides = sumOfSides;
    }

    public void setMaxSide(Integer maxSide) {
        this.maxSide = maxSide;
    }

    public void setMinSide(Integer minSide) {
        this.minSide = minSide;
    }
}
