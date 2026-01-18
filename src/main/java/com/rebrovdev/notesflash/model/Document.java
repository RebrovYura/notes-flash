package com.rebrovdev.notesflash.model;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private final List<Page> pages = new ArrayList<>();
    private int currentPageIndex = 0;
    private String name;

    public Document() {
        pages.add(new Page());
        this.name = name;
    }

    public int getCurrentPage() {
        return this.currentPageIndex;
    }

    public List<Page> getPages() {
        return this.pages;
    }

    public String getName() {
        return this.name;
    }

    public void addPage() {
        pages.add(new Page());
        currentPageIndex = pages.size() + 1;
    }

    public void setCurrentPageIndex(int index) {
        if (index > 0 && index <= pages.size()) {
            this.currentPageIndex = index;
        }
    }

    public void setName(String name) {
        this.name = name;
    }
}
