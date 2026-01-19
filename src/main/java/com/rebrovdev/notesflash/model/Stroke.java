package com.rebrovdev.notesflash.model;

import java.util.ArrayList;
import java.util.List;

public class Stroke {

    // Путь из точек для отрисовки линии
    private final List<Point> points = new ArrayList<>();
    private final int color;
    private final double width;

    public Stroke(int color, double width) {
        this.color = color;
        this.width = width;
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
        System.out.println("point has been added");
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getColor() {
        return color;
    }

    public double getWidth() {
        return width;
    }

}
