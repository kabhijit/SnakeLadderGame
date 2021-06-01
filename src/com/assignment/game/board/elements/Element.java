package com.assignment.game.board.elements;

public abstract class Element {
    private final int start;
    private final int end;

    public Element(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public boolean shouldProcessed() {
        return true;
    }
}
