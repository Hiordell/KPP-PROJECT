package ru.specialist.demo.service;

import ru.specialist.demo.entity.TriangleSidesInfo;

import java.util.Arrays;
import java.util.List;

public class TriangleLogic {

    public static int calculateSumOfSides(TriangleSidesInfo triangleSidesInfo) {
        List<Integer> resList = Arrays.asList(triangleSidesInfo.getA(), triangleSidesInfo.getB(), triangleSidesInfo.getC());
        return resList.stream().mapToInt(Integer::intValue).sum();
    }

    public static int findMinOfSides(TriangleSidesInfo triangleSidesInfo) {
        List<Integer> resList = Arrays.asList(triangleSidesInfo.getA(), triangleSidesInfo.getB(), triangleSidesInfo.getC());

        return resList.stream().mapToInt(Integer::intValue).min().getAsInt();
    }

    public static int findMaxOfSides(TriangleSidesInfo triangleSidesInfo) {
        List<Integer> resList = Arrays.asList(triangleSidesInfo.getA(), triangleSidesInfo.getB(), triangleSidesInfo.getC());
        return resList.stream().mapToInt(Integer::intValue).max().getAsInt();
    }
}
