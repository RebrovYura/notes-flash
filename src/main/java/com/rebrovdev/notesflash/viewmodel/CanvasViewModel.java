package com.rebrovdev.notesflash.viewmodel;

import com.rebrovdev.notesflash.model.Document;
import com.rebrovdev.notesflash.model.Page;
import com.rebrovdev.notesflash.model.Stroke;
import com.rebrovdev.notesflash.tools.PenTool;
import com.rebrovdev.notesflash.tools.Tool;
import com.rebrovdev.notesflash.tools.ToolType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.awt.event.MouseEvent;

public class CanvasViewModel {

    private final Document document;
    private final ObjectProperty<Page> currentPage;
    private ObjectProperty<Tool> activeTool;

    public void addStroke(Stroke stroke) {
        currentPage.get().getStrokes().add(stroke);
        currentPage.set(currentPage.get());
        System.out.println("addstroke");
    }

    public void removeStroke(Stroke stroke) {
        currentPage.get().getStrokes().remove(stroke);
        currentPage.set(currentPage.get());
        System.out.println("removestroke");
    }

    public CanvasViewModel() {
        this.document = new Document("Name");
        this.currentPage = new SimpleObjectProperty<>(document.getCurrentPage());
        this.activeTool = new SimpleObjectProperty<>();
//        this.activeTool.set(new PenTool(0x000000, 2.0, this::addStroke));
    }

//    public void onMousePresses(double x, double y) {
//        activeTool.get().onPress(x, y);
//        System.out.println("pressed");
//    }
//
//    public void onMouseDragged(double x, double y) {
//        activeTool.get().onDrag(x, y);
//        System.out.println("dragged");
//    }
//
//    public void onMouseReleased(double x, double y) {
//        activeTool.get().onRelease(x, y);
//        System.out.println("released");
//    }

    public ObjectProperty<Page> currentPageProperty() {
        return currentPage;
    }

    public ObjectProperty<Tool> activeToolProperty() {
        return activeTool;
    }

    public Tool getActiveTool() {
        return activeTool.get();
    }

    public void setActiveTool(ObjectProperty<Tool> activeTool) { this.activeTool = activeTool; }

    public Page getCurrentPage() { return currentPage.get(); }

}
