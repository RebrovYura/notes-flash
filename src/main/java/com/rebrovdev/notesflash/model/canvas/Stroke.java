package com.rebrovdev.notesflash.model.canvas;

import java.util.ArrayList;
import java.util.List;

public class Stroke {

    private final List<Point> points = new ArrayList<>();

    public Stroke() {
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public List<Point> getPoints(){
        return points;
    }
}
