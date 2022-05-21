package ru.specialist.demo.exceptions;

public class TriangleError extends RuntimeException {

    public TriangleError() {
        super("Triangle with these sides does not exist");
    }
}
