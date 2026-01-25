package com.rebrovdev.notesflash.tools;

import javafx.scene.canvas.GraphicsContext;

public class EraserTool implements Tool{

    @Override
    public void onPress(GraphicsContext gc, double x, double y) {
        gc.beginPath();
        gc.moveTo(x, y);
        gc.stroke();
    }

    @Override
    public void onDrag(GraphicsContext gc, double x, double y) {
        gc.lineTo(x, y);
        gc.stroke();
    }

    @Override
    public void onRelease(GraphicsContext gc, double x, double y) {
        gc.closePath();
    }

//    private void eraseAt(double x, double y) {
//        Page page = document.getCurrentPage();
//        List<Stroke> strokes = page.getStrokes();
//
//        Iterator<Stroke> iterator = page.getStrokes().iterator();
//
//        while (iterator.hasNext()) {
//            Stroke stroke = iterator.next();
//
//            boolean hit = stroke.getPoints().stream().anyMatch(p -> distance(p, x, y) <= radius);
//
//            if (hit) {
//                iterator.remove();
//            }
//        }
//    }
//
//    public double distance(Point p, double x, double y) {
//        double dx = p.getX() - x;
//        double dy = p.getY() - y;
//        return Math.sqrt(dx * dx + dy * dy);
//    }
}
