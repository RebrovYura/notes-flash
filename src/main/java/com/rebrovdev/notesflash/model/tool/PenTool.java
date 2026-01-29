package com.rebrovdev.notesflash.model.tool;

import com.rebrovdev.notesflash.model.canvas.CanvasRender;
import com.rebrovdev.notesflash.model.canvas.CanvasState;
import com.rebrovdev.notesflash.model.canvas.Point;
import com.rebrovdev.notesflash.model.canvas.Stroke;
import com.rebrovdev.notesflash.utils.Smoothing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

import java.util.ArrayList;
import java.util.List;

public class PenTool implements Tool {

    private final List<Point> points = new ArrayList<>();
    private final GraphicsContext gc;
    private final CanvasState state;
    private Stroke currentStroke;
    private Smoothing smoothing;

    public PenTool(GraphicsContext gc, Smoothing smoothing, CanvasState state) {
        this.gc = gc;
        this.state = state;
        this.smoothing = smoothing;
        setup();
    }

    @Override
    public void setup() {
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
        gc.setLineWidth(2.5);
        gc.setStroke(Color.BLACK);
    }

    @Override
    public void onPress(double x, double y) {
        currentStroke = new Stroke(smoothing);
        currentStroke.addPoint(new Point(x, y));
        state.addStroke(currentStroke);
//        CanvasRender.redraw(gc, state, smoothing);
    }

    @Override
    public void onDrag(double x, double y) {
        currentStroke.addPoint(new Point(x, y));
//        state.addStroke(currentStroke);
//        CanvasRender.redraw(gc, state);
//        points.add(new Point(x, y));
//        smoothing.redraw(gc, points);
    }

    @Override
    public void onRelease(double x, double y) {
        currentStroke = null;
    }
}
