package com.rebrovdev.notesflash.model.canvas;

import java.util.ArrayList;
import java.util.List;

public class Stroke {

    // Путь из точек для отрисовки линии
    private final List<Point> points = new ArrayList<>();
    private int color;
    private double width;
    private double opacity;

    public Stroke(int color, double width) {
        this.color = color;
        this.width = width;
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
        System.out.println("point has been added at x: " + x + " y: " + y);
    }

    public List<Point> getPoints(){
        return points;
    }

    public int getColor() {
        return color;
    }

    public double getWidth() {
        return width;
    }

//    public double getOpacity() {
//        return opacity;
//    }

//    public void setOpacity(double opacity) {
//        this.opacity = opacity;
//    }

}
