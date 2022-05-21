package ru.specialist.demo.entity;

import java.util.Objects;

public class TriangleSidesInfo {

    private int a;
    private int b;
    private int c;

    public TriangleSidesInfo(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public TriangleSidesInfo() {
    }

    public int getA() {return a;}
    public int getB() {return b;}
    public int getC() {return c;}

    public void setA(int a) {this.a = a;}
    public void setB(int b) {this.b = b;}
    public void setC(int c) {this.c = c;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriangleSidesInfo that = (TriangleSidesInfo) o;
        return a == that.a && b == that.b && c == that.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
